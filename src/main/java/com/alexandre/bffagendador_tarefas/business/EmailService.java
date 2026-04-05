package com.alexandre.bffagendador_tarefas.business;

import com.alexandre.bffagendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.alexandre.bffagendador_tarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviaEmail(TarefasDTOResponse dto){

        emailClient.enviarEmail(dto);
    }


}
