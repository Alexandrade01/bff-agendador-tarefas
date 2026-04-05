package com.alexandre.bffagendador_tarefas.business.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTOResponse {

    private Long id;
    private String rua;
    private Long numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
