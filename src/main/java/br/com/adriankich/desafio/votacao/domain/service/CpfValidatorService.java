package br.com.adriankich.desafio.votacao.domain.service;

import br.com.adriankich.desafio.votacao.domain.enums.ValidatorEnum;

public interface CpfValidatorService {

    ValidatorEnum validateCpf(String cpf);
}
