package com.alexandre.bffagendador_tarefas.infrastructure.client;


import com.alexandre.bffagendador_tarefas.business.dto.EnderecoDTO;
import com.alexandre.bffagendador_tarefas.business.dto.TelefoneDTO;
import com.alexandre.bffagendador_tarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @PostMapping
    UsuarioDTO postUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("login")
    String login(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("/endereco")
    EnderecoDTO cadastraEndereco(@RequestBody EnderecoDTO dto,
                                                 @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTO cadastraTelefone(@RequestBody TelefoneDTO dto,
                                                 @RequestHeader("Authorization") String token);

    @GetMapping("getByEmail")
    UsuarioDTO getUsuarioPorEmail(@RequestParam String email,
                                                  @RequestHeader("Authorization") String token);

    @GetMapping("findAll")
    List<UsuarioDTO> findAllUsuario(@RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTO atualizaDadosUsuario(@RequestBody UsuarioDTO dto,
                                                    @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTO atualizaEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                 @RequestParam("id") Long id,
                                                 @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTO atualizaTelefone(@RequestBody TelefoneDTO telefoneDto,
                                                 @RequestParam("id") Long id,
                                                 @RequestHeader("Authorization") String token);

    @DeleteMapping("deleteByEmail/{email}")
    void deleteusuarioPorEmail(@PathVariable String email,
                                                 @RequestHeader("Authorization") String token);

    @DeleteMapping("deleteByEndereco/{enderecoId}")
    void exclusaoDeEndereco(@PathVariable Long enderecoId,
                                              @RequestHeader("Authorization") String token);

    @DeleteMapping("deleteByTelefone/{telefoneId}")
    void exclusaoDeTelefone(@PathVariable Long telefoneId,
                                              @RequestHeader("Authorization") String token);
}
