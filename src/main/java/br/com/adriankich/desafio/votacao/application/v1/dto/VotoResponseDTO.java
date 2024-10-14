package br.com.adriankich.desafio.votacao.application.v1.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class VotoResponseDTO {

    private Long id;
    private Long associadoId;
    private Long sessaoVotacaoId;
    private String voto;
}
