package com.alexandre.bffagendador_tarefas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
@RequiredArgsConstructor
public class TesteController {

    @GetMapping
    public ResponseEntity<String> teste(){

        return ResponseEntity.ok("TESTE REALIZADO COM SUCESSO");

    }
}
