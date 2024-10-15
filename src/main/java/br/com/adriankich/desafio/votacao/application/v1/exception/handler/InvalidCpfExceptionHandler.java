package br.com.adriankich.desafio.votacao.application.v1.exception.handler;

import br.com.adriankich.desafio.votacao.domain.exception.InvalidCpfException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidCpfExceptionHandler implements ResourceExceptionHandler<InvalidCpfException> {

    /**
     * handle
     *
     * @param ex AlreadyExistsException
     * @param request HttpServletRequest
     * @return ResponseEntity<StandardError>
     */
    @Override
    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<?> handle(InvalidCpfException ex, HttpServletRequest request) {
        return response(ex, request, HttpStatus.BAD_REQUEST);
    }
}
