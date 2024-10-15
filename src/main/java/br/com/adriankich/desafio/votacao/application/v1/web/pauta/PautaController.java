package br.com.adriankich.desafio.votacao.application.v1.web.pauta;

import br.com.adriankich.desafio.votacao.application.v1.adapter.PautaAdapter;
import br.com.adriankich.desafio.votacao.application.v1.adapter.SessaoVotacaoAdapter;
import br.com.adriankich.desafio.votacao.application.v1.dto.*;
import br.com.adriankich.desafio.votacao.application.v1.swagger.PautaSwagger;
import br.com.adriankich.desafio.votacao.domain.model.Pauta;
import br.com.adriankich.desafio.votacao.domain.service.impl.PautaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PautaController implements PautaApi, PautaSwagger {

    @Autowired
    private PautaServiceImpl pautaService;

    /**
     * getPautas
     *
     * @return ResponseEntity<List<PautaResponseDTO>>
     */
    @Override
    public ResponseEntity<List<PautaResponseDTO>> getPautas() {
        return ResponseEntity.ok(pautaService.getPautas().stream().map(PautaAdapter::entityToDto).toList());
    }

    /**
     * getPautaById
     *
     * @param id
     * @return ResponseEntity<PautaResponseDTO>
     */
    @Override
    public ResponseEntity<PautaResponseDTO> getPautaById(Long id) {
        return ResponseEntity.ok(PautaAdapter.entityToDto(pautaService.getPautaById(id)));
    }

    /**
     * createPauta
     *
     * @param pautaDTO
     * @return ResponseEntity<PautaResponseDTO>
     */
    @Override
    public ResponseEntity<PautaResponseDTO> createPauta(PautaRequestDTO pautaDTO) {
        PautaResponseDTO response = PautaAdapter.entityToDto(pautaService.createPauta(pautaDTO));
        return ResponseEntity.ok(response);
    }

    /**
     * startSessaoVotacao
     *
     * @param id
     * @param sessaoDTO
     * @return ResponseEntity<SessaoVotacaoResponseDTO>
     */
    @Override
    public ResponseEntity<SessaoVotacaoResponseDTO> startSessaoVotacao(Long id, SessaoVotacaoRequestDTO sessaoDTO) {
        if(sessaoDTO == null) {
            sessaoDTO = SessaoVotacaoRequestDTO.builder().minutes(Pauta.DEFAULT_SESSAO_TIME).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SessaoVotacaoAdapter.entityToDto(pautaService.startSessaoVotacao(id, sessaoDTO)));
    }

    /**
     * getResult
     *
     * @param id
     * @return ResponseEntity<ResultResponseDTO>
     */
    @Override
    public ResponseEntity<ResultResponseDTO> getResult(Long id) {
        return ResponseEntity.ok(pautaService.getResult(id));
    }
}
