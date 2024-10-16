package br.com.adriankich.desafio.votacao.domain.service.impl;

import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoRequestDTO;
import br.com.adriankich.desafio.votacao.domain.exception.DocumentAlreadyUsedException;
import br.com.adriankich.desafio.votacao.domain.exception.NotFoundException;
import br.com.adriankich.desafio.votacao.domain.model.Associado;
import br.com.adriankich.desafio.votacao.domain.service.AssociadoService;
import br.com.adriankich.desafio.votacao.infrastructure.respository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    /**
     * getAssociados
     *
     * @return List<Associado>
     */
    @Override
    public List<Associado> getAssociados() {
        return associadoRepository.findAll();
    }

    /**
     * getAssociadoById
     *
     * @param id Long
     * @return Associado
     */
    @Override
    public Associado getAssociadoById(Long id) {
        return associadoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado um associado com o id: #" + id));
    }

    /**
     * getAssociadoByDocument
     *
     * @param document String
     * @return Associado
     */
    @Override
    public Associado getAssociadoByDocument(String document) {
        return associadoRepository.findByDocument(document)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado um associado com este documento: " + document));
    }

    /**
     * createAssociado
     *
     * @param associadoDTO AssociadoRequestDTO
     * @return Associado
     */
    @Override
    public Associado createAssociado(AssociadoRequestDTO associadoDTO) {
        Associado associado = Associado.builder()
                                        .name(associadoDTO.getName())
                                        .document(associadoDTO.getDocument())
                                        .build();

        return addAssociado(associado);
    }

    /**
     * addAssociado
     *
     * @param associado Associado
     * @return Associado
     */
    @Override
    public Associado addAssociado(Associado associado) {
        if(associadoRepository.existsByDocument(associado.getDocument())) {
            throw new DocumentAlreadyUsedException("Já existe um associado cadastrado com este documento: " + associado.getDocument());
        }

        return associadoRepository.save(associado);
    }
}
