package br.com.adriankich.desafio.votacao.application.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResultResponseDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startTimeVoting;
    private LocalDateTime endTimeVoting;
    private String result;
    private Long approved;
    private Long reproved;
    private Long total;

}
