package br.com.adriankich.desafio.votacao.domain.service;

import br.com.adriankich.desafio.votacao.application.v1.dto.PautaRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.ResultResponseDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.SessaoVotacaoRequestDTO;
import br.com.adriankich.desafio.votacao.domain.model.Pauta;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;

import java.util.List;

public interface PautaService {

    List<Pauta> getPautas();

    Pauta getPautaById(Long id);

    Pauta createPauta(PautaRequestDTO pautaDTO);

    Pauta addPauta(Pauta pauta);

    Pauta updatePauta(Pauta pauta);

    SessaoVotacao startSessaoVotacao(Long pautaId, SessaoVotacaoRequestDTO sessaoDTO);

    ResultResponseDTO getResult(Long pautaId);
}
