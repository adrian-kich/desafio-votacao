package br.com.adriankich.desafio.votacao.application.v1.web.pauta;

import br.com.adriankich.desafio.votacao.application.v1.adapter.PautaAdapter;
import br.com.adriankich.desafio.votacao.application.v1.adapter.SessaoVotacaoAdapter;
import br.com.adriankich.desafio.votacao.application.v1.dto.*;
import br.com.adriankich.desafio.votacao.domain.model.Pauta;
import br.com.adriankich.desafio.votacao.domain.service.impl.PautaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PautaController implements PautaApi{

    @Autowired
    private PautaServiceImpl pautaService;

    @Override
    public ResponseEntity<List<PautaResponseDTO>> getPautas() {
        return ResponseEntity.ok(pautaService.getPautas().stream().map(PautaAdapter::entityToDto).toList());
    }

    @Override
    public ResponseEntity<PautaResponseDTO> getPautaById(Long id) {
        return ResponseEntity.ok(PautaAdapter.entityToDto(pautaService.getPautaById(id)));
    }

    @Override
    public ResponseEntity<PautaResponseDTO> createPauta(PautaRequestDTO pautaDTO) {
        PautaResponseDTO response = PautaAdapter.entityToDto(pautaService.createPauta(pautaDTO));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<SessaoVotacaoResponseDTO> startSessaoVotacao(Long id, SessaoVotacaoRequestDTO sessaoDTO) {
        if(sessaoDTO == null) {
            sessaoDTO = SessaoVotacaoRequestDTO.builder().minutes(Pauta.DEFAULT_SESSAO_TIME).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SessaoVotacaoAdapter.entityToDto(pautaService.startSessaoVotacao(id, sessaoDTO)));
    }

    @Override
    public ResponseEntity<ResultResponseDTO> getResult(Long id) {
        return ResponseEntity.ok(pautaService.getResult(id));
    }
}
