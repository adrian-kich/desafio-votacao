package br.com.adriankich.desafio.votacao.application.v1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AssociadoRequestDTO {

    private String name;

    private String document;
}
