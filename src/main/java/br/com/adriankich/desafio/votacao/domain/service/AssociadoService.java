package br.com.adriankich.desafio.votacao.domain.service;

import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoRequestDTO;
import br.com.adriankich.desafio.votacao.domain.model.Associado;

import java.util.List;

public interface AssociadoService {

    List<Associado> getAssociados();
    Associado getAssociadoById(Long id);
    Associado getAssociadoByDocument(String document);
    Associado createAssociado(AssociadoRequestDTO associadoDTO);
    Associado addAssociado(Associado associado);
}
