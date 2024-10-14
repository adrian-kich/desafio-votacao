package br.com.adriankich.desafio.votacao.application.v1.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ValidationError extends StandardError {
    private String fields;
    private String fieldsMessage;
}
