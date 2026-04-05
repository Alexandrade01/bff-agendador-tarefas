package com.alexandre.bffagendador_tarefas.business.dto.out;

//import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTOResponse {

    private Long id;

//    @Column(name = "numero",length = 10)
    private String numero;

//    @Column(name = "ddd",length = 3)
    private String ddd;
}
