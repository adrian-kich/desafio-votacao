package br.com.adriankich.desafio.votacao.application.v1.dto;

import br.com.adriankich.desafio.votacao.domain.enums.PautaEnum;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PautaResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String status;
    private Long sessaoVotacaoId;
    private LocalDateTime creationDate;
}
