package br.com.adriankich.desafio.votacao.application.v1.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotoRequestDTO {

    private Long associadoId;
    private String voto;
}
