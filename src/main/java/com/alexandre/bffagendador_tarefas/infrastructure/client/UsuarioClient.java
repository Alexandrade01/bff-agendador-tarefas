package com.alexandre.bffagendador_tarefas.infrastructure.client;


import com.alexandre.bffagendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.alexandre.bffagendador_tarefas.business.dto.in.LoginRequestDTO;
import com.alexandre.bffagendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.alexandre.bffagendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.alexandre.bffagendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.alexandre.bffagendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.alexandre.bffagendador_tarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @PostMapping
    UsuarioDTOResponse postUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("login")
    String login(@RequestBody LoginRequestDTO usuarioDTO);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader("Authorization") String token);

    @GetMapping("getByEmail")
    UsuarioDTOResponse getUsuarioPorEmail(@RequestParam String email,
                                          @RequestHeader("Authorization") String token);

    @GetMapping("findAll")
    List<UsuarioDTOResponse> findAllUsuario(@RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @DeleteMapping("/deleteByEmail/{email}")
    void deleteusuarioPorEmail(@PathVariable String email,
                                                 @RequestHeader("Authorization") String token);

    @DeleteMapping("/deleteByEndereco/{enderecoId}")
    void exclusaoDeEndereco(@PathVariable Long enderecoId,
                                              @RequestHeader("Authorization") String token);

    @DeleteMapping("/deleteByTelefone/{telefoneId}")
    void exclusaoDeTelefone(@PathVariable Long telefoneId,
                                              @RequestHeader("Authorization") String token);
}
