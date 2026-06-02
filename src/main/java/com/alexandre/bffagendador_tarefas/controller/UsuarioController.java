package com.alexandre.bffagendador_tarefas.controller;


import com.alexandre.bffagendador_tarefas.business.UsuarioService;
import com.alexandre.bffagendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.alexandre.bffagendador_tarefas.business.dto.in.LoginRequestDTO;
import com.alexandre.bffagendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.alexandre.bffagendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.alexandre.bffagendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.alexandre.bffagendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.alexandre.bffagendador_tarefas.business.dto.out.UsuarioDTOResponse;
import com.alexandre.bffagendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
//Tag - identificação no swagger, caso tenha mais controllers
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    //Operation - descrição da função do nosso metodo
    @Operation(summary = "Salvar usuarios", description = "Cria um novo usuario")
    //Api Response - identifica os status code
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {

        return ResponseEntity.ok(usuarioService.salvaUsuarioDTO(usuarioDTO));

    }

    @PostMapping("/login")
    //Operation - descrição da função do nosso metodo
    @Operation(summary = "Login usuarios", description = "Login de um usuario")
    //Api Response - identifica os status code
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO) {

        return usuarioService.loginUsuario(usuarioDTO);

    }

    @PostMapping("/endereco")
    //Operation - descrição da função do nosso metodo
    @Operation(summary = "Cadastro usuario", description = "Cadastra endereço de usuario")
    //Api Response - identifica os status code
    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name = "Authorization",required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastroDeEndereco(dto, token));
    }

    @PostMapping("/telefone")
    //Operation - descrição da função do nosso metodo
    @Operation(summary = "Cadastro telefone", description = "Cadastra telefone de usuario")
    //Api Response - identifica os status code
    @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name = "Authorization",required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastroDeTelefone(token, dto));
    }

    @GetMapping("getByEmail")
    //Operation - descrição da função do nosso metodo
    @Operation(summary = "Buscar dados de usuario por email", description = "Buscar dados de um usuario")
    //Api Response - identifica os status code
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> getUsuarioPorEmail(@RequestParam String email,
                                                                 @RequestHeader(name = "Authorization",required = false) String token) {

        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email, token));

    }

    @GetMapping("findAll")
    //Operation - descrição da função do nosso metodo
    @Operation(summary = "Buscar todos os usuarios", description = "Buscar todos os usuarios")
    //Api Response - identifica os status code
    @ApiResponse(responseCode = "200", description = "Usuários cadastrados")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<UsuarioDTOResponse>> findAllUsuario(@RequestHeader(name = "Authorization",required = false) String token) {

        return ResponseEntity.ok(usuarioService.findAllUsuarios(token));
    }

    @PutMapping
    //Operation - descrição da função do nosso metodo
    @Operation(summary = "Atualizar usuario", description = "Atualiza dados de um usuario")
    //Api Response - identifica os status code
    @ApiResponse(responseCode = "200", description = "Atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                   @RequestHeader(name = "Authorization",required = false) String token) {

        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(dto, token));

    }

    @PutMapping("/endereco")
    //Operation - descrição da função do nosso metodo
    @Operation(summary = "Atualizar endereço", description = "Atualiza endereço de um usuario")
    //Api Response - identifica os status code
    @ApiResponse(responseCode = "200", description = "Atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization",required = false) String token) {

        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoDTO, token));

    }

    @PutMapping("/telefone")
    //Operation - descrição da função do nosso metodo
    @Operation(summary = "Atualizar telefone", description = "Atualiza telefone de um usuario")
    //Api Response - identifica os status code
    @ApiResponse(responseCode = "200", description = "Atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization",required = false) String token) {

        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDto, token));

    }

    @DeleteMapping("deleteByEmail/{email}")
    //Operation - descrição da função do nosso metodo
    @Operation(summary = "Deleta dados de usuario por email", description = "Deleta dados de um usuario")
    //Api Response - identifica os status code
    @ApiResponse(responseCode = "200", description = "Deletado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<String> deleteusuarioPorEmail(@PathVariable String email,
                                                        @RequestHeader(name = "Authorization",required = false) String token) {

        usuarioService.deleteByEmail(email, token);

        return ResponseEntity.ok().body("Usuario com email " + email + " deletado !");

    }

    @DeleteMapping("deleteByEndereco/{enderecoId}")
    //Operation - descrição da função do nosso metodo
    @Operation(summary = "Deleta endereço", description = "Deleta endereço pelo id do endereço")
    //Api Response - identifica os status code
    @ApiResponse(responseCode = "200", description = "Deletado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Endereço não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<String> exclusaoDeEndereco(@PathVariable Long enderecoId,
                                                     @RequestHeader(name = "Authorization",required = false) String token) {


        usuarioService.deleteByEndereco(enderecoId, token);

        return ResponseEntity.ok().body("Endereço deletado !");
    }

    @DeleteMapping("deleteByTelefone/{telefoneId}")
    //Operation - descrição da função do nosso metodo
    @Operation(summary = "Deleta telefone", description = "Deleta telefone pelo id do telefone")
    //Api Response - identifica os status code
    @ApiResponse(responseCode = "200", description = "Deletado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "403", description = "Telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<String> exclusaoDeTelefone(@PathVariable Long telefoneId,
                                                     @RequestHeader(name = "Authorization",required = false) String token) {


        usuarioService.deleteByTelefone(telefoneId, token);

        return ResponseEntity.ok().body("Telefone deletado !");
    }

}
