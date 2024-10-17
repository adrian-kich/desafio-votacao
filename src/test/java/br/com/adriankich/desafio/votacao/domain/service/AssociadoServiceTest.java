package br.com.adriankich.desafio.votacao.domain.service;

import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoRequestDTO;
import br.com.adriankich.desafio.votacao.domain.exception.DocumentAlreadyUsedException;
import br.com.adriankich.desafio.votacao.domain.exception.NotFoundException;
import br.com.adriankich.desafio.votacao.domain.model.Associado;
import br.com.adriankich.desafio.votacao.domain.service.impl.AssociadoServiceImpl;
import br.com.adriankich.desafio.votacao.infrastructure.respository.AssociadoRepository;
import br.com.adriankich.desafio.votacao.models.AssociadoStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class AssociadoServiceTest {

    public static final long ID = 1L;
    public static final String DOCUMENT = "03116037000";

    @Mock
    private AssociadoRepository associadoRepository;

    @InjectMocks
    private AssociadoServiceImpl associadoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAssociados_ReturnsListOfAssociado() {
        Associado associado = AssociadoStub.createAssociadoWithoutId();
        when(associadoRepository.findAll()).thenReturn(List.of(associado));

        List<Associado> associados = associadoService.getAssociados();

        assertNotNull(associados);
        assertEquals(1, associados.size());
        assertEquals(Associado.class, associados.get(0).getClass());
    }

    @Test
    void testGetAssociadoById_ReturnsAssociado() {
        Associado associado = AssociadoStub.createAssociadoWithId(ID);
        when(associadoRepository.findById(ID)).thenReturn(Optional.of(associado));

        Associado result = associadoService.getAssociadoById(ID);

        assertNotNull(result);
        assertEquals(Associado.class, result.getClass());
        assertEquals(ID, result.getId());
    }

    @Test
    void testGetAssociadoById_ThrowsNotFoundException() {
        when(associadoRepository.findById(ID)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            associadoService.getAssociadoById(ID);
        });
        assertEquals("Não foi encontrado um associado com o id: #" + ID, exception.getMessage());
    }

    @Test
    void testGetAssociadoByDocument_ReturnsAssociado() {
        Associado associado = AssociadoStub.createAssociadoWithoutId();
        when(associadoRepository.findByDocument(DOCUMENT)).thenReturn(Optional.of(associado));

        Associado result = associadoService.getAssociadoByDocument(DOCUMENT);

        assertNotNull(result);
        assertEquals(DOCUMENT, result.getDocument());
    }

    @Test
    void testGetAssociadoByDocument_ThrowsNotFoundException() {
        when(associadoRepository.findByDocument(DOCUMENT)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            associadoService.getAssociadoByDocument(DOCUMENT);
        });
        assertEquals("Não foi encontrado um associado com este documento: " + DOCUMENT, exception.getMessage());
    }

    @Test
    void testCreateAssociado_ReturnsSuccess() {
        AssociadoRequestDTO associadoRequestDTO = AssociadoStub.createAssociadoRequestDTO();
        Associado associado = AssociadoStub.createAssociadoWithoutId();

        when(associadoRepository.existsByDocument(associadoRequestDTO.getDocument())).thenReturn(false);
        when(associadoRepository.save(any(Associado.class))).thenReturn(associado);

        Associado result = associadoService.createAssociado(associadoRequestDTO);

        assertNotNull(result);
        assertEquals(Associado.class, result.getClass());
        assertEquals(associado.getDocument(), result.getDocument());
    }

    @Test
    void testCreateAssociado_ThrowsDocumentAlreadyUsedException() {
        AssociadoRequestDTO associadoRequestDTO = AssociadoStub.createAssociadoRequestDTO();
        Associado associado = AssociadoStub.createAssociadoWithoutId();

        when(associadoRepository.existsByDocument(DOCUMENT)).thenReturn(true);

        DocumentAlreadyUsedException exception = assertThrows(DocumentAlreadyUsedException.class, () -> {
            associadoService.createAssociado(associadoRequestDTO);
        });
        assertEquals("Já existe um associado cadastrado com este documento: " + DOCUMENT, exception.getMessage());
    }
}
