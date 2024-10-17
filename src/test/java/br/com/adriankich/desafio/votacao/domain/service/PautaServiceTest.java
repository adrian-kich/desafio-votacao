package br.com.adriankich.desafio.votacao.domain.service;

import br.com.adriankich.desafio.votacao.application.v1.dto.PautaRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.ResultResponseDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.SessaoVotacaoRequestDTO;
import br.com.adriankich.desafio.votacao.domain.exception.AlreadyExistsException;
import br.com.adriankich.desafio.votacao.domain.exception.NotFoundException;
import br.com.adriankich.desafio.votacao.domain.model.Pauta;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;
import br.com.adriankich.desafio.votacao.domain.service.impl.PautaServiceImpl;
import br.com.adriankich.desafio.votacao.domain.service.impl.SessaoVotacaoServiceImpl;
import br.com.adriankich.desafio.votacao.infrastructure.respository.PautaRepository;
import br.com.adriankich.desafio.votacao.models.PautaStub;
import br.com.adriankich.desafio.votacao.models.SessaoVotacaoStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PautaServiceTest {

    public static final long ID = 1L;
    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private SessaoVotacaoServiceImpl sessaoVotacaoService;

    @InjectMocks
    private PautaServiceImpl pautaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPautas_ReturnsListOfPauta() {
        Pauta pauta = PautaStub.createPautaWithoutId();

        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);
        when(pautaRepository.findAll()).thenReturn(List.of(pauta));
        when(pautaService.updatePauta(pauta)).thenReturn(pauta);

        List<Pauta> pautas = pautaService.getPautas();

        assertNotNull(pautas);
        assertEquals(1, pautas.size());
        assertEquals(Pauta.class, pautas.get(0).getClass());
    }

    @Test
    void testGetPautaById_ReturnsPauta() {
        Pauta pauta = PautaStub.createPautaWithId(ID);

        when(pautaRepository.findById(ID)).thenReturn(Optional.of(pauta));

        Pauta result = pautaService.getPautaById(ID);

        assertNotNull(result);
        assertEquals(Pauta.class, result.getClass());
        assertEquals(ID, result.getId());
    }

    @Test
    void testGetPautaById_ThrowsNotFoundException() {
        when(pautaRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            pautaService.getPautaById(ID);
        });
        assertEquals("Não foi encontrado uma pauta com o id: #" + ID, exception.getMessage());
    }

    @Test
    void testCreatePauta_ReturnsSuccess() {
        PautaRequestDTO pautaRequestDTO = PautaStub.createPautaRequestDTO();
        Pauta pauta = PautaStub.createPautaWithoutId();

        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        Pauta result = pautaService.createPauta(pautaRequestDTO);

        assertNotNull(result);
        assertEquals(pautaRequestDTO.getTitle(), result.getTitle());
        verify(pautaRepository, times(1)).save(any(Pauta.class));
    }

    @Test
    void testStartSessaoVotacao_ReturnsSuccess() {
        SessaoVotacaoRequestDTO sessaoDTO = SessaoVotacaoStub.createSessaoVotacaoRequestDTO();
        SessaoVotacao sessaoVotacao = SessaoVotacaoStub.createSessaoVotacaoWithId(ID);

        Pauta pauta = PautaStub.createPautaWithId(ID);

        when(pautaRepository.findById(ID)).thenReturn(Optional.of(pauta));
        when(sessaoVotacaoService.createSessao(any(Pauta.class), anyLong())).thenReturn(sessaoVotacao);

        SessaoVotacao result = pautaService.startSessaoVotacao(ID, sessaoDTO);

        assertNotNull(result);
        assertEquals(sessaoVotacao.getId(), result.getId());
        assertEquals(SessaoVotacao.class, result.getClass());
        verify(pautaRepository, times(2)).save(any(Pauta.class));
    }

    @Test
    void testStartSessaoVotacao_ThrowsAlreadyExists() {
        SessaoVotacao sessaoVotacao = SessaoVotacaoStub.createSessaoVotacaoWithId(1l);
        Pauta pauta = PautaStub.createPautaWithSessaoVotacao(ID, sessaoVotacao);

        when(pautaRepository.findById(anyLong())).thenReturn(Optional.of(pauta));

        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class, () -> {
            pautaService.startSessaoVotacao(ID, new SessaoVotacaoRequestDTO());
        });
        assertEquals("Já existe uma sessão de votação para esta pauta. Id da sessão: #" + ID, exception.getMessage());
    }

    @Test
    void testGetResult_ReturnsResult() {
        SessaoVotacao sessaoVotacao = SessaoVotacaoStub.createSessaoVotacaoWithId(ID);
        Pauta pauta = PautaStub.createPautaWithSessaoVotacao(ID, sessaoVotacao);

        when(pautaRepository.findById(ID)).thenReturn(Optional.of(pauta));

        ResultResponseDTO result = pautaService.getResult(ID);

        assertNotNull(result);
        assertEquals(ID, result.getId());
        assertEquals(pauta.getTitle(), result.getTitle());
        assertEquals(ResultResponseDTO.class, result.getClass());
    }

    @Test
    void testGetResult_SessaoNotStarted() {
        Pauta pauta = PautaStub.createPautaWithoutId();

        when(pautaRepository.findById(anyLong())).thenReturn(Optional.of(pauta));

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            pautaService.getResult(ID);
        });
        assertEquals("Não foi iniciada uma sessão de votação para esta pauta", exception.getMessage());
    }
}
