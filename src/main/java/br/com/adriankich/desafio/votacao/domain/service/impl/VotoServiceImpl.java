package br.com.adriankich.desafio.votacao.domain.service.impl;

import br.com.adriankich.desafio.votacao.domain.enums.VotoEnum;
import br.com.adriankich.desafio.votacao.domain.model.Associado;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;
import br.com.adriankich.desafio.votacao.domain.model.Voto;
import br.com.adriankich.desafio.votacao.domain.service.VotoService;
import br.com.adriankich.desafio.votacao.infrastructure.respository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoServiceImpl implements VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Override
    public Voto getVoto(Long id) {
        return votoRepository.findById(id).orElseThrow();
    }

    @Override
    public Voto createVoto(SessaoVotacao sessaoVotacao, Associado associado, VotoEnum votoOption) {
        Voto voto = Voto.builder()
                .sessao(sessaoVotacao)
                .associado(associado)
                .voto(votoOption)
                .build();

        return votoRepository.save(voto);
    }
}
