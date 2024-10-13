package br.com.adriankich.desafio.votacao.application.v1.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AssociadoResponseDTO {

    private Long id;
    private String name;
    private String document;
}
