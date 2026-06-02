package com.alexandre.bffagendador_tarefas.controller;


import com.alexandre.bffagendador_tarefas.business.TarefasService;
import com.alexandre.bffagendador_tarefas.business.dto.in.TarefasDTORequest;
import com.alexandre.bffagendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.alexandre.bffagendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.alexandre.bffagendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas",description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar tarefas de usuarios", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "401", description = "Token de autenticação inválido ou ausente")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefa(@RequestBody TarefasDTORequest dto,
                                                           @RequestHeader(name = "Authorization",required = false) String token) {

        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));

    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca lista de tarefas por Periodo", description = "Busca tarefas cadastradas por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "401", description = "Token de autenticação inválido ou ausente")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasAgendadasPorPeriodo(

            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization",required = false) String token

    ) {
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal,token));
    }

    @GetMapping
    @Operation(summary = "Busca todas as tarefas do usuário", description = "Retorna a lista completa de tarefas associadas ao e-mail do usuário autenticado")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso")
    @ApiResponse(responseCode = "401", description = "Token de autenticação inválido ou ausente")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    @ApiResponse(responseCode = "403",description = "Email não encontrado")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization",required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta uma tarefa por ID", description = "Remove permanentemente uma tarefa identificada pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
//    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada para o ID informado")
    @ApiResponse(responseCode = "401", description = "Token de autenticação inválido ou ausente")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    @ApiResponse(responseCode = "403",description = "Tarefa id não encontrado")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id, @RequestHeader(name = "Authorization",required = false) String token) {

        tarefasService.deletaTarefaPorId(id,token);

        return ResponseEntity.ok().build();

    }

    @PatchMapping
    @Operation(summary = "Altera o status de notificação de uma tarefa", description = "Atualiza o status de notificação de uma tarefa específica identificada pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Status de notificação atualizado com sucesso")
//    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada para o ID informado")
    @ApiResponse(responseCode = "401", description = "Token de autenticação inválido ou ausente")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    @ApiResponse(responseCode = "403",description = "Tarefa id não encontrado")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(name = "Authorization",required = false) String token){

        return ResponseEntity.ok(tarefasService.alteraStatus(status,id,token));
    }

    @PutMapping
    @Operation(summary = "Atualiza os dados de uma tarefa", description = "Substitui integralmente os dados de uma tarefa existente identificada pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
//    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada para o ID informado")
    @ApiResponse(responseCode = "401", description = "Token de autenticação inválido ou ausente")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    @ApiResponse(responseCode = "403",description = "Tarefa id não encontrado")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization",required = false) String token){

        return ResponseEntity.ok(tarefasService.updateTarefas(dto,id,token));

    }


}
