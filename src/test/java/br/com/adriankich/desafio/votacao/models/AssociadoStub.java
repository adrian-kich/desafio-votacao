package br.com.adriankich.desafio.votacao.models;

import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoResponseDTO;
import br.com.adriankich.desafio.votacao.domain.model.Associado;

public class AssociadoStub {

    public static Associado createAssociadoWithoutId() {
        return Associado.builder()
                .name("Adrian Kich")
                .document("03116037000")
                .build();
    }

    public static Associado createAssociadoWithId(Long id) {
        return Associado.builder()
                .id(id)
                .name("Adrian Kich")
                .document("03116037000")
                .build();
    }

    public static AssociadoRequestDTO createAssociadoRequestDTO() {
        return AssociadoRequestDTO.builder()
                .name("Adrian Kich")
                .document("03116037000")
                .build();
    }

    public static AssociadoResponseDTO createAssociadoResponseDTO() {
        return AssociadoResponseDTO.builder()
                .id(1L)
                .document("03116037000")
                .name("Adrian Kich")
                .build();
    }
}
