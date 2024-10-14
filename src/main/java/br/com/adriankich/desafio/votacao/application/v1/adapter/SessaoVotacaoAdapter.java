package br.com.adriankich.desafio.votacao.application.v1.adapter;

import br.com.adriankich.desafio.votacao.application.v1.dto.SessaoVotacaoResponseDTO;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;

public class SessaoVotacaoAdapter {

    public static SessaoVotacaoResponseDTO entityToDto(SessaoVotacao sessaoVotacao) {
        return SessaoVotacaoResponseDTO.builder()
                .id(sessaoVotacao.getId())
                .pautaId(sessaoVotacao.getPauta().getId())
                .startTime(sessaoVotacao.getStartTime())
                .endTime(sessaoVotacao.getEndTime())
                .status(sessaoVotacao.getStatus().getValue())
                .result(sessaoVotacao.getResult().getValue())
                .approved(sessaoVotacao.getTotalApproved())
                .reproved(sessaoVotacao.getTotalReproved())
                .total(((long) sessaoVotacao.getVotos().size()))
                .build();
    }
}
