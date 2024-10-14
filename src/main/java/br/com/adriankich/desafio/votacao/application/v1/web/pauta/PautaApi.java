package br.com.adriankich.desafio.votacao.application.v1.web.pauta;

import br.com.adriankich.desafio.votacao.application.v1.context.ApplicationContext;
import br.com.adriankich.desafio.votacao.application.v1.dto.PautaRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.PautaResponseDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.SessaoVotacaoRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.SessaoVotacaoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = ApplicationContext.VERSION + "pautas", produces = MediaType.APPLICATION_JSON_VALUE)
public interface PautaApi {

    @GetMapping
    ResponseEntity<List<PautaResponseDTO>> getPautas();

    @GetMapping("/{id}")
    ResponseEntity<PautaResponseDTO> getPautaById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<PautaResponseDTO> createPauta(@RequestBody @Valid PautaRequestDTO pautaDTO);

    @PostMapping("/{id}/sessao")
    ResponseEntity<SessaoVotacaoResponseDTO> startSessaoVotacao(@PathVariable Long id,
                                                                @RequestBody(required = false) SessaoVotacaoRequestDTO sessaoDTO);
}
