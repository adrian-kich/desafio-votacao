package br.com.adriankich.desafio.votacao.application.v1.exception.handler;

import br.com.adriankich.desafio.votacao.application.v1.exception.StandardError;
import br.com.adriankich.desafio.votacao.domain.exception.AlreadyExistsException;
import br.com.adriankich.desafio.votacao.domain.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AlreadyExistsExceptionHandler implements ResourceExceptionHandler<AlreadyExistsException> {

    @Override
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<StandardError> handle(AlreadyExistsException ex, HttpServletRequest request) {
        return response(ex, request, HttpStatus.BAD_REQUEST);
    }
}
