package br.com.adriankich.desafio.votacao.domain.service.impl;

import br.com.adriankich.desafio.votacao.domain.enums.ValidatorEnum;
import br.com.adriankich.desafio.votacao.domain.exception.InvalidCpfException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CpfValidatorServiceImpl {

    private final Random random = new Random();

    public ValidatorEnum validateCpf(String cpf) {
        if (!isValidCpf(cpf)) {
            throw new InvalidCpfException("CPF inválido.");
        }
        return random.nextBoolean() ? ValidatorEnum.ABLE_TO_VOTE : ValidatorEnum.UNABLE_TO_VOTE;
    }

    private boolean isValidCpf(String cpf) {
        // Simulação: Considera CPF válido se tiver 11 dígitos numéricos.
        return cpf != null && cpf.matches("\\d{11}");
    }
}
