package br.com.adriankich.desafio.votacao.models;

import br.com.adriankich.desafio.votacao.application.v1.exception.StandardError;
import org.springframework.http.HttpStatus;

public class ErrorStub {

    public static StandardError createNotFoundError() {
        return StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .build();
    }

    public static StandardError createDocumentAlreadyUsedError(String message) {
        return StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(message)
                .build();
    }
}
