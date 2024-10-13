package br.com.adriankich.desafio.votacao.application.v1.dto;

import br.com.adriankich.desafio.votacao.domain.enums.SessaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SessaoVotacaoResponseDTO {

    private Long id;
    private Long pautaId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String result;
    private Long approved;
    private Long reproved;
    private Long total;
}
