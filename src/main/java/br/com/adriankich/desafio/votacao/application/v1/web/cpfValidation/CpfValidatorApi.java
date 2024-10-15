package br.com.adriankich.desafio.votacao.application.v1.web.cpfValidation;

import br.com.adriankich.desafio.votacao.application.v1.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = ApplicationContext.VERSION + "validador", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CpfValidatorApi {

    @GetMapping("/{cpf}")
    ResponseEntity validator(@PathVariable String cpf);
}
