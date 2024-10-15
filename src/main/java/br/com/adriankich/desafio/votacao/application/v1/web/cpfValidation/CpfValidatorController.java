package br.com.adriankich.desafio.votacao.application.v1.web.cpfValidation;

import br.com.adriankich.desafio.votacao.domain.enums.ValidatorEnum;
import br.com.adriankich.desafio.votacao.domain.service.impl.CpfValidatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CpfValidatorController implements CpfValidatorApi {

    @Autowired
    private CpfValidatorServiceImpl cpfValidatorService;

    /**
     * validator
     *
     * @param cpf
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity validator(String cpf) {
        ValidatorEnum status = cpfValidatorService.validateCpf(cpf);

        HashMap<String, ValidatorEnum> body = new HashMap<>();
        body.put("status", status);

        if(status.equals(ValidatorEnum.UNABLE_TO_VOTE)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        } else {
            return ResponseEntity.ok(body);
        }
    }
}
