package br.com.adriankich.desafio.votacao.domain.service;

import br.com.adriankich.desafio.votacao.application.v1.dto.VotoRequestDTO;
import br.com.adriankich.desafio.votacao.domain.model.Pauta;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;
import br.com.adriankich.desafio.votacao.domain.model.Voto;

public interface SessaoVotacaoService {

    SessaoVotacao getSessaoVotacao(Long id);
    SessaoVotacao createSessao(Pauta pauta, Long minutes);
    SessaoVotacao updateSessaoVotacao(SessaoVotacao sessaoVotacao);
    Voto addVoto(Long sessaoVotacaoId, VotoRequestDTO votoDTO);
}
