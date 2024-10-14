package br.com.adriankich.desafio.votacao.application.v1.web.sessaoVotacao;

import br.com.adriankich.desafio.votacao.application.v1.adapter.SessaoVotacaoAdapter;
import br.com.adriankich.desafio.votacao.application.v1.adapter.VotoAdapter;
import br.com.adriankich.desafio.votacao.application.v1.dto.SessaoVotacaoResponseDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.VotoRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.VotoResponseDTO;
import br.com.adriankich.desafio.votacao.domain.service.impl.SessaoVotacaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessaoController implements SessaoApi {

    @Autowired
    private SessaoVotacaoServiceImpl sessaoVotacaoService;

    @Override
    public ResponseEntity<SessaoVotacaoResponseDTO> getSessaoById(Long id) {
        return ResponseEntity.ok(SessaoVotacaoAdapter.entityToDto(sessaoVotacaoService.getSessaoVotacao(id)));
    }

    @Override
    public ResponseEntity<VotoResponseDTO> addVoto(Long id, VotoRequestDTO votoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(VotoAdapter.entityToDto(sessaoVotacaoService.addVoto(id, votoDTO)));
    }
}
