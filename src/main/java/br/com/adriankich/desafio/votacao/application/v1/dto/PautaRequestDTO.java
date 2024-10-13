package br.com.adriankich.desafio.votacao.application.v1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PautaRequestDTO {

    private String title;
    private String description;
}
