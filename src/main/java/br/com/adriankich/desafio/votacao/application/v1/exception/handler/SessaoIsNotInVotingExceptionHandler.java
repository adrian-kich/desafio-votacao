package br.com.adriankich.desafio.votacao.application.v1.exception.handler;

import br.com.adriankich.desafio.votacao.application.v1.exception.StandardError;
import br.com.adriankich.desafio.votacao.domain.exception.SessaoIsNotInVotingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SessaoIsNotInVotingExceptionHandler implements ResourceExceptionHandler<SessaoIsNotInVotingException> {

    /**
     * handle
     *
     * @param ex AlreadyExistsException
     * @param request HttpServletRequest
     * @return ResponseEntity<StandardError>
     */
    @Override
    @ExceptionHandler(SessaoIsNotInVotingException.class)
    public ResponseEntity<StandardError> handle(SessaoIsNotInVotingException ex, HttpServletRequest request) {
        return response(ex, request, HttpStatus.BAD_REQUEST);
    }
}
