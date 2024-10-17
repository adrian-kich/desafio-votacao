package br.com.adriankich.desafio.votacao.application.v1.web;

import br.com.adriankich.desafio.votacao.application.v1.dto.SessaoVotacaoResponseDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.VotoRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.VotoResponseDTO;
import br.com.adriankich.desafio.votacao.application.v1.web.sessaoVotacao.SessaoApi;
import br.com.adriankich.desafio.votacao.application.v1.web.sessaoVotacao.SessaoController;
import br.com.adriankich.desafio.votacao.domain.model.Pauta;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;
import br.com.adriankich.desafio.votacao.domain.model.Voto;
import br.com.adriankich.desafio.votacao.domain.service.impl.SessaoVotacaoServiceImpl;
import br.com.adriankich.desafio.votacao.models.PautaStub;
import br.com.adriankich.desafio.votacao.models.SessaoVotacaoStub;
import br.com.adriankich.desafio.votacao.models.VotoStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = SessaoApi.class)
class SessaoApiTest {

    public static final long ID = 1L;
    public static final String PATH = "/api/v1/sessoes";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessaoVotacaoServiceImpl sessaoVotacaoService;

    @MockBean
    private SessaoController sessaoController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        Mockito.reset(sessaoVotacaoService);
    }

    @Test
    void testGetSessaoById_ReturnsSuccess() throws Exception {
        Pauta pauta = PautaStub.createPautaWithId(ID);

        SessaoVotacao sessao = SessaoVotacaoStub.createSessaoVotacaoWithId(ID);

        SessaoVotacaoResponseDTO responseDTO = SessaoVotacaoStub.createSessaoVotacaoResponseDTO();

        when(sessaoVotacaoService.getSessaoVotacao(ID)).thenReturn(sessao);
        when(sessaoController.getSessaoById(any())).thenReturn(ResponseEntity.ok(responseDTO));

        mockMvc.perform(get(PATH + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.pautaId", is(responseDTO.getPautaId().intValue())))
                .andExpect(jsonPath("$.status", is(responseDTO.getStatus())))
                .andExpect(jsonPath("$.result", is(responseDTO.getResult())))
                .andExpect(jsonPath("$.approved", is(responseDTO.getApproved().intValue())))
                .andExpect(jsonPath("$.reproved", is(responseDTO.getReproved().intValue())))
                .andExpect(jsonPath("$.total", is(responseDTO.getTotal().intValue())));
    }

    @Test
    void testAddVoto_RetunsSuccess() throws Exception {
        VotoRequestDTO votoRequest = VotoStub.createVotoRequestDTO();
        VotoResponseDTO votoResponse = VotoStub.createVotoResponseDTO();

        Voto voto = VotoStub.createVotoWithoutId();

        when(sessaoVotacaoService.addVoto(ID, votoRequest)).thenReturn(voto);
        when(sessaoController.addVoto(any(), any())).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(votoResponse));

        mockMvc.perform(post(PATH + "/1/voto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(votoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(votoResponse.getId().intValue())))
                .andExpect(jsonPath("$.associadoId", is(votoRequest.getAssociadoId().intValue())))
                .andExpect(jsonPath("$.sessaoVotacaoId", is(votoRequest.getAssociadoId().intValue())))
                .andExpect(jsonPath("$.voto", is(votoResponse.getVoto())));
    }
}