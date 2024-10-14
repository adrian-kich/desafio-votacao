package br.com.adriankich.desafio.votacao.application.v1.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PautaRequestDTO {

    @NotEmpty(message = "O título da pauta é obrigatório")
    private String title;

    @NotEmpty(message = "A descrição da pauta é obrigatório")
    private String description;
}
