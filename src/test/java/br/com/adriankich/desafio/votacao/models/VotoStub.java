package br.com.adriankich.desafio.votacao.models;

import br.com.adriankich.desafio.votacao.application.v1.dto.VotoRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.VotoResponseDTO;
import br.com.adriankich.desafio.votacao.domain.enums.VotoEnum;
import br.com.adriankich.desafio.votacao.domain.model.Associado;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;
import br.com.adriankich.desafio.votacao.domain.model.Voto;

public class VotoStub {

    public static Voto createVotoWithoutId() {
        return Voto.builder()
                .voto(VotoEnum.SIM)
                .associado(AssociadoStub.createAssociadoWithId(1L))
                .sessao(SessaoVotacaoStub.createSessaoVotacaoWithId(1L))
                .build();
    }

    public static Voto createVotoWithoutId(Associado associado, SessaoVotacao sessaoVotacao, VotoEnum votoEnum) {
        return Voto.builder()
                .voto(votoEnum)
                .associado(associado)
                .sessao(sessaoVotacao)
                .build();
    }

    public static VotoRequestDTO createVotoRequestDTO() {
        return VotoRequestDTO.builder()
                .voto("SIM")
                .associadoId(1L)
                .build();
    }

    public static VotoResponseDTO createVotoResponseDTO() {
        return VotoResponseDTO.builder()
                .id(1l)
                .associadoId(1l)
                .sessaoVotacaoId(1l)
                .voto(VotoEnum.SIM.getValue())
                .build();
    }
}
