package br.com.adriankich.desafio.votacao.models;

import br.com.adriankich.desafio.votacao.application.v1.dto.PautaRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.PautaResponseDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.ResultResponseDTO;
import br.com.adriankich.desafio.votacao.domain.enums.PautaEnum;
import br.com.adriankich.desafio.votacao.domain.model.Pauta;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;

import java.time.LocalDateTime;

public class PautaStub {

    public static Pauta createPautaWithoutId() {
        return Pauta.builder()
                .title("Pauta exemplo")
                .description("Descrição exemplo")
                .creationDate(LocalDateTime.now())
                .status(PautaEnum.VOTACAO_NAO_INICIADA)
                .build();
    }

    public static Pauta createPautaWithId(Long id) {
        return Pauta.builder()
                .id(id)
                .title("Pauta exemplo")
                .description("Descrição exemplo")
                .creationDate(LocalDateTime.now())
                .status(PautaEnum.VOTACAO_NAO_INICIADA)
                .build();
    }

    public static Pauta createPautaWithSessaoVotacao(Long id, SessaoVotacao sessaoVotacao) {
        return Pauta.builder()
                .id(id)
                .title("Pauta exemplo")
                .description("Descrição exemplo")
                .creationDate(LocalDateTime.now())
                .sessaoVotacao(sessaoVotacao)
                .status(PautaEnum.EM_VOTACAO)
                .build();
    }

    public static PautaRequestDTO createPautaRequestDTO() {
        return PautaRequestDTO.builder()
                .title("Pauta exemplo")
                .description("Descrição exemplo")
                .build();
    }

    public static PautaResponseDTO createPautaResponseDTO() {
        return PautaResponseDTO.builder()
                .id(1l)
                .title("Pauta exemplo")
                .description("Descrição exemplo")
                .sessaoVotacaoId(1l)
                .creationDate(LocalDateTime.now())
                .status(PautaEnum.EM_VOTACAO.getValue())
                .build();
    }

    public static ResultResponseDTO createResultResponseDTO() {
        return ResultResponseDTO.builder()
                .id(1l)
                .title("Pauta exemplo")
                .description("Descrição exemplo")
                .result(PautaEnum.EMPATADA.getValue())
                .approved(0l)
                .reproved(0l)
                .total(0l)
                .build();
    }
}
