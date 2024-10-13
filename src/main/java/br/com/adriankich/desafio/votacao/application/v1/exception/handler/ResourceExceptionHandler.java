package br.com.adriankich.desafio.votacao.application.v1.exception.handler;

import br.com.adriankich.desafio.votacao.application.v1.exception.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public interface ResourceExceptionHandler<T extends Exception> {

    public ResponseEntity<StandardError> handle(T ex, HttpServletRequest request );

    public default ResponseEntity<StandardError> response( Exception ex, HttpServletRequest request, HttpStatus status )
    {
        StandardError error = StandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(error);
    }
}
