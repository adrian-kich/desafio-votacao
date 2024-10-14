package br.com.adriankich.desafio.votacao.domain.service;

import br.com.adriankich.desafio.votacao.domain.enums.VotoEnum;
import br.com.adriankich.desafio.votacao.domain.model.Associado;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;
import br.com.adriankich.desafio.votacao.domain.model.Voto;
import br.com.adriankich.desafio.votacao.infrastructure.respository.VotoRepository;

public interface VotoService {

    Voto getVoto(Long id);

    Voto createVoto(SessaoVotacao sessaoVotacao, Associado associado, VotoEnum votoOption);
}
