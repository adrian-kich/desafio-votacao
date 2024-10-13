package br.com.adriankich.desafio.votacao.application.v1.adapter;

import br.com.adriankich.desafio.votacao.application.v1.dto.PautaResponseDTO;
import br.com.adriankich.desafio.votacao.domain.model.Pauta;

public class PautaAdapter {

    public static PautaResponseDTO entityToDto(Pauta pauta) {
        return PautaResponseDTO.builder()
                .id(pauta.getId())
                .title(pauta.getTitle())
                .description(pauta.getDescription())
                .status(pauta.getStatus().getValue())
                .sessaoVotacaoId(pauta.getSessaoVotacao() != null ? pauta.getSessaoVotacao().getId() : 0)
                .creationDate(pauta.getCreationDate())
                .build();
    }
}
