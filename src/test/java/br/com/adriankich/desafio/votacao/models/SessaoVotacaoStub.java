package br.com.adriankich.desafio.votacao.models;

import br.com.adriankich.desafio.votacao.application.v1.dto.SessaoVotacaoRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.SessaoVotacaoResponseDTO;
import br.com.adriankich.desafio.votacao.domain.enums.SessaoEnum;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;
import br.com.adriankich.desafio.votacao.domain.model.Voto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessaoVotacaoStub {

    public static SessaoVotacao createSessaoVotacaoWithoutId() {
        return SessaoVotacao.builder()
                .pauta(PautaStub.createPautaWithId(1l))
                .votos(new ArrayList<>())
                .totalApproved(0L)
                .totalReproved(0L)
                .status(SessaoEnum.ABERTA)
                .result(SessaoEnum.EM_VOTACAO)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(2L))
                .build();
    }

    public static SessaoVotacao createSessaoVotacaoWithId(Long id) {
        return SessaoVotacao.builder()
                .id(id)
                .pauta(PautaStub.createPautaWithId(1l))
                .votos(List.of(new Voto()))
                .totalApproved(0L)
                .totalReproved(0L)
                .status(SessaoEnum.ABERTA)
                .result(SessaoEnum.EM_VOTACAO)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(2L))
                .build();
    }

    public static SessaoVotacao createSessaoVotacaoFinished(Long id) {
        return SessaoVotacao.builder()
                .id(id)
                .pauta(PautaStub.createPautaWithId(1l))
                .votos(List.of(VotoStub.createVotoWithoutId()))
                .totalApproved(0L)
                .totalReproved(0L)
                .status(SessaoEnum.ENCERRADA)
                .result(SessaoEnum.EMPATADA)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(2L))
                .build();
    }

    public static SessaoVotacaoRequestDTO createSessaoVotacaoRequestDTO() {
        return SessaoVotacaoRequestDTO.builder()
                .minutes(1L)
                .build();
    }

    public static SessaoVotacaoResponseDTO createSessaoVotacaoResponseDTO() {
        return SessaoVotacaoResponseDTO.builder()
                .pautaId(1l)
                .id(1l)
                .status(SessaoEnum.EM_VOTACAO.getValue())
                .approved(0l)
                .reproved(0l)
                .total(0l)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(1l))
                .result(SessaoEnum.EMPATADA.getValue())
                .build();
    }
}
