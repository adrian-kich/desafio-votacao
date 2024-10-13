package br.com.adriankich.desafio.votacao.application.v1.web.associado;

import br.com.adriankich.desafio.votacao.application.v1.adapter.AssociadoAdapter;
import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoResponseDTO;
import br.com.adriankich.desafio.votacao.domain.service.impl.AssociadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class AssociadoController implements AssociadoApi {

    @Autowired
    private AssociadoServiceImpl associadoService;

    @Override
    public ResponseEntity<List<AssociadoResponseDTO>> getAssociados() {
        return ResponseEntity.ok(associadoService.getAssociados()
                .stream()
                .map(AssociadoAdapter::entityToDto)
                .toList());
    }

    @Override
    public ResponseEntity<AssociadoResponseDTO> getAssociadoById(Long id) {
        return ResponseEntity.ok(AssociadoAdapter.entityToDto(associadoService.getAssociadoById(id)));
    }

    @Override
    public ResponseEntity<AssociadoResponseDTO> getAssociadoByDocument(String document) {
        return ResponseEntity.ok(AssociadoAdapter.entityToDto(associadoService.getAssociadoByDocument(document)));
    }

    @Override
    public ResponseEntity<AssociadoResponseDTO> createAssociado(AssociadoRequestDTO associadoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AssociadoAdapter.entityToDto(associadoService.createAssociado(associadoDTO)));
    }
}
