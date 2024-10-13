package br.com.adriankich.desafio.votacao.application.v1.adapter;

import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoResponseDTO;
import br.com.adriankich.desafio.votacao.domain.model.Associado;

public class AssociadoAdapter {

    public static AssociadoResponseDTO entityToDto(Associado associado) {
        return AssociadoResponseDTO.builder()
                .id(associado.getId())
                .name(associado.getName())
                .document(associado.getDocument())
                .build();
    }
}
