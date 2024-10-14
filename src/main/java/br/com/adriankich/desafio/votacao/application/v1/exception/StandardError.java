package br.com.adriankich.desafio.votacao.application.v1.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class StandardError {

    protected LocalDateTime timestamp;
    protected Integer status;
    protected String error;
    protected String path;
}
