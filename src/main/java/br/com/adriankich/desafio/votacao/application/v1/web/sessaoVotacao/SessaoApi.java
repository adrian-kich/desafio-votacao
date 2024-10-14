package br.com.adriankich.desafio.votacao.application.v1.web.sessaoVotacao;

import br.com.adriankich.desafio.votacao.application.v1.context.ApplicationContext;
import br.com.adriankich.desafio.votacao.application.v1.dto.SessaoVotacaoResponseDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.VotoRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.VotoResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = ApplicationContext.VERSION + "sessoes", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SessaoApi {

    @GetMapping("/{id}")
    ResponseEntity<SessaoVotacaoResponseDTO> getSessaoById(@PathVariable Long id);

    @PostMapping("/{id}/voto")
    ResponseEntity<VotoResponseDTO> addVoto(@PathVariable Long id, @RequestBody VotoRequestDTO votoDTO);
}
