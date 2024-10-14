package br.com.adriankich.desafio.votacao.application.v1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotoRequestDTO {

    @NotNull(message = "O ID do associado é obrigatório")
    private Long associadoId;

    @NotNull(message = "O voto é obrigatório")
    @Pattern(
            regexp = "SIM|NAO",
            message = "O voto deve ser 'SIM' ou 'NAO'"
    )
    private String voto;
}
