package com.alexandre.bffagendador_tarefas.infrastructure.client;

import com.alexandre.bffagendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.alexandre.bffagendador_tarefas.business.dto.in.LoginRequestDTO;
import com.alexandre.bffagendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.alexandre.bffagendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.alexandre.bffagendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.alexandre.bffagendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.alexandre.bffagendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.alexandre.bffagendador_tarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface EmailClient {

    //Passamos o response porque esse dto possui o email, campo que necessitamos
    void enviarEmail(@RequestBody TarefasDTOResponse dto);
}
