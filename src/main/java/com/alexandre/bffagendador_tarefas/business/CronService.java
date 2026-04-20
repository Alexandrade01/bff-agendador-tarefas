package com.alexandre.bffagendador_tarefas.business;

import com.alexandre.bffagendador_tarefas.business.dto.in.LoginRequestDTO;
import com.alexandre.bffagendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.alexandre.bffagendador_tarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
//Função do CronService é rodar um scheduler que a cada 5 minutos veja as tarefas que serão executadas na proxima 1 hora
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    //https://crontab.cronhub.io/
    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){

        String token = login(criarObjetoRequestDTO());

        String tokenUser = "Bearer".concat(token.substring(7));

        log.info("Iniciada a busca de tarefas : "+LocalDateTime.now());

        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(2);

        //Vai buscar todas as tarefas de determinada hora futura
        //se agora é 22h - vai buscar entre 23h e 23h05
        List<TarefasDTOResponse> listaTarefas
                = tarefasService.buscaTarefasAgendadasPorPeriodo(horaFutura,horaFuturaMaisCinco,tokenUser);

        log.info("Tarefas encontradas " + listaTarefas);

        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(),tokenUser);
        });
    }

    //forma mais dificil, criando um token pro usuario proprio do sistema
    public String login(LoginRequestDTO dto){

        return usuarioService.loginUsuario(dto);

    }

    public LoginRequestDTO criarObjetoRequestDTO(){

        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }


}
