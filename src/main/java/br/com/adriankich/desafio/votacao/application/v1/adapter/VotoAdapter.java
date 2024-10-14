package br.com.adriankich.desafio.votacao.application.v1.adapter;

import br.com.adriankich.desafio.votacao.application.v1.dto.VotoResponseDTO;
import br.com.adriankich.desafio.votacao.domain.model.Voto;

public class VotoAdapter {

    public static VotoResponseDTO entityToDto(Voto voto) {
        return VotoResponseDTO.builder()
                .id(voto.getId())
                .associadoId(voto.getAssociado().getId())
                .sessaoVotacaoId(voto.getSessao().getId())
                .voto(voto.getVoto().getValor())
                .build();
    }
}
