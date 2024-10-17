package br.com.adriankich.desafio.votacao.domain.service;

import br.com.adriankich.desafio.votacao.application.v1.dto.VotoRequestDTO;
import br.com.adriankich.desafio.votacao.domain.enums.VotoEnum;
import br.com.adriankich.desafio.votacao.domain.exception.AlreadyVotedException;
import br.com.adriankich.desafio.votacao.domain.exception.NotFoundException;
import br.com.adriankich.desafio.votacao.domain.exception.SessaoIsNotInVotingException;
import br.com.adriankich.desafio.votacao.domain.model.Associado;
import br.com.adriankich.desafio.votacao.domain.model.Pauta;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;
import br.com.adriankich.desafio.votacao.domain.model.Voto;
import br.com.adriankich.desafio.votacao.domain.service.impl.AssociadoServiceImpl;
import br.com.adriankich.desafio.votacao.domain.service.impl.SessaoVotacaoServiceImpl;
import br.com.adriankich.desafio.votacao.domain.service.impl.VotoServiceImpl;
import br.com.adriankich.desafio.votacao.infrastructure.respository.SessaoVotacaoRepository;
import br.com.adriankich.desafio.votacao.models.AssociadoStub;
import br.com.adriankich.desafio.votacao.models.PautaStub;
import br.com.adriankich.desafio.votacao.models.SessaoVotacaoStub;
import br.com.adriankich.desafio.votacao.models.VotoStub;
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

class SessaoVotacaoServiceTest {

    public static final long ID = 1L;
    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private AssociadoServiceImpl associadoService;

    @Mock
    private VotoServiceImpl votoService;

    @InjectMocks
    private SessaoVotacaoServiceImpl sessaoVotacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSessaoVotacao_ReturnsSessaoVotacao() {
        SessaoVotacao sessaoVotacao = SessaoVotacaoStub.createSessaoVotacaoWithId(ID);

        when(sessaoVotacaoRepository.findById(ID)).thenReturn(Optional.of(sessaoVotacao));

        SessaoVotacao result = sessaoVotacaoService.getSessaoVotacao(ID);

        assertNotNull(result);
        assertEquals(SessaoVotacao.class, result.getClass());
        assertEquals(sessaoVotacao.getId(), result.getId());
    }

    @Test
    void testGetSessaoVotacao_ThrowsNotFoundException() {
        when(sessaoVotacaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            sessaoVotacaoService.getSessaoVotacao(ID);
        });
        assertEquals("Não foi encontrado uma sessão com o id: #" + ID, exception.getMessage());
    }

    @Test
    void testCreateSessao_ReturnsSuccess() {
        Pauta pauta = PautaStub.createPautaWithoutId();
        SessaoVotacao sessaoVotacao = SessaoVotacaoStub.createSessaoVotacaoWithoutId();

        when(sessaoVotacaoRepository.save(any(SessaoVotacao.class))).thenReturn(sessaoVotacao);

        SessaoVotacao result = sessaoVotacaoService.createSessao(pauta, 10L);

        assertNotNull(result);
        assertEquals(SessaoVotacao.class, result.getClass());
        verify(sessaoVotacaoRepository, times(1)).save(any(SessaoVotacao.class));
    }

    @Test
    void testAddVoto_ReturnsSuccess() {
        SessaoVotacao sessaoVotacao = SessaoVotacaoStub.createSessaoVotacaoWithoutId();
        Associado associado = AssociadoStub.createAssociadoWithId(ID);
        Voto voto = VotoStub.createVotoWithoutId(associado, sessaoVotacao, VotoEnum.SIM);
        VotoRequestDTO votoRequestDTO = VotoStub.createVotoRequestDTO();

        when(sessaoVotacaoRepository.findById(ID)).thenReturn(Optional.of(sessaoVotacao));
        when(associadoService.getAssociadoById(ID)).thenReturn(associado);
        when(votoService.createVoto(any(SessaoVotacao.class), any(Associado.class), any(VotoEnum.class))).thenReturn(voto);

        Voto result = sessaoVotacaoService.addVoto(ID, votoRequestDTO);

        assertNotNull(result);
        assertEquals(Voto.class, result.getClass());
        verify(sessaoVotacaoRepository, times(2)).save(sessaoVotacao);
    }

    @Test
    void testAddVoto_ThrowsAlreadyVotedException() {
        SessaoVotacao sessaoVotacao = SessaoVotacaoStub.createSessaoVotacaoWithId(ID);
        Associado associado = AssociadoStub.createAssociadoWithId(ID);
        Voto voto = VotoStub.createVotoWithoutId(associado, sessaoVotacao, VotoEnum.SIM);

        sessaoVotacao.setVotos(List.of(voto));

        VotoRequestDTO votoDTO = VotoStub.createVotoRequestDTO();

        when(sessaoVotacaoRepository.findById(ID)).thenReturn(Optional.of(sessaoVotacao));

        AlreadyVotedException exception = assertThrows(AlreadyVotedException.class, () -> {
            sessaoVotacaoService.addVoto(ID, votoDTO);
        });
        assertEquals("Associado já votou nessa sessão de votação", exception.getMessage());
    }

    @Test
    void testAddVoto_SessaoNotInVoting() {
        SessaoVotacao sessaoVotacao = SessaoVotacaoStub.createSessaoVotacaoFinished(ID);

        VotoRequestDTO votoDTO = VotoStub.createVotoRequestDTO();

        when(sessaoVotacaoRepository.findById(ID)).thenReturn(Optional.of(sessaoVotacao));

        SessaoIsNotInVotingException exception = assertThrows(SessaoIsNotInVotingException.class, () -> {
            sessaoVotacaoService.addVoto(ID, votoDTO);
        });
        assertEquals("Essa sessão não está aberta para votação", exception.getMessage());
    }
}
