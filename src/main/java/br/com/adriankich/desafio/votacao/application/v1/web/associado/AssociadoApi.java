package br.com.adriankich.desafio.votacao.application.v1.web.associado;

import br.com.adriankich.desafio.votacao.application.v1.context.ApplicationContext;
import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = ApplicationContext.VERSION + "associados", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AssociadoApi {

    @GetMapping
    ResponseEntity<List<AssociadoResponseDTO>> getAssociados();

    @GetMapping("/{id}")
    ResponseEntity<AssociadoResponseDTO> getAssociadoById(@PathVariable Long id);

    @GetMapping("/documento/{document}")
    ResponseEntity<AssociadoResponseDTO> getAssociadoByDocument(@PathVariable String document);

    @PostMapping
    ResponseEntity<AssociadoResponseDTO> createAssociado(@RequestBody @Valid AssociadoRequestDTO associadoDTO);
}
