package br.com.adriankich.desafio.votacao.domain.service;

import br.com.adriankich.desafio.votacao.domain.enums.VotoEnum;
import br.com.adriankich.desafio.votacao.domain.exception.NotFoundException;
import br.com.adriankich.desafio.votacao.domain.model.Associado;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;
import br.com.adriankich.desafio.votacao.domain.model.Voto;
import br.com.adriankich.desafio.votacao.domain.service.impl.VotoServiceImpl;
import br.com.adriankich.desafio.votacao.infrastructure.respository.VotoRepository;
import br.com.adriankich.desafio.votacao.models.AssociadoStub;
import br.com.adriankich.desafio.votacao.models.SessaoVotacaoStub;
import br.com.adriankich.desafio.votacao.models.VotoStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VotoServiceTest {

    public static final long ID = 1L;
    @Mock
    private VotoRepository votoRepository;

    @InjectMocks
    private VotoServiceImpl votoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetVoto_ReturnsVoto() {
        Voto voto = VotoStub.createVotoWithoutId();
        when(votoRepository.findById(ID)).thenReturn(Optional.of(voto));

        Voto result = votoService.getVoto(ID);

        assertNotNull(result);
        assertEquals(Voto.class, result.getClass());
    }

    @Test
    void testGetVoto_ThrowsNotFoundException() {
        when(votoRepository.findById(ID)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            votoService.getVoto(ID);
        });
        assertEquals("Não foi encontrado um voto com o id: #" + ID, exception.getMessage());
    }

    @Test
    void testCreateVoto_Success() {
        SessaoVotacao sessaoVotacao = SessaoVotacaoStub.createSessaoVotacaoWithId(ID);
        Associado associado = AssociadoStub.createAssociadoWithId(ID);

        Voto voto = VotoStub.createVotoWithoutId(associado, sessaoVotacao, VotoEnum.SIM);

        when(votoRepository.save(any(Voto.class))).thenReturn(voto);

        Voto result = votoService.createVoto(sessaoVotacao, associado, VotoEnum.SIM);

        assertNotNull(result);
        assertEquals(sessaoVotacao, result.getSessao());
        assertEquals(associado, result.getAssociado());
        assertEquals(VotoEnum.SIM, result.getVoto());
        verify(votoRepository, times(1)).save(any(Voto.class));
    }
}
