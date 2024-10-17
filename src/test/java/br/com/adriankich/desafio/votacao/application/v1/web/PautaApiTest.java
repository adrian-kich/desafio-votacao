package br.com.adriankich.desafio.votacao.application.v1.web;

import br.com.adriankich.desafio.votacao.application.v1.dto.*;
import br.com.adriankich.desafio.votacao.application.v1.web.pauta.PautaApi;
import br.com.adriankich.desafio.votacao.application.v1.web.pauta.PautaController;
import br.com.adriankich.desafio.votacao.domain.model.Pauta;
import br.com.adriankich.desafio.votacao.domain.model.SessaoVotacao;
import br.com.adriankich.desafio.votacao.domain.service.impl.PautaServiceImpl;
import br.com.adriankich.desafio.votacao.models.PautaStub;
import br.com.adriankich.desafio.votacao.models.SessaoVotacaoStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PautaApi.class)
class PautaApiTest {

    public static final String PATH = "/api/v1/pautas";
    public static final long ID = 1l;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PautaServiceImpl pautaService;

    @MockBean
    private PautaController pautaController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        Mockito.reset(pautaService);
    }

    @Test
    void testGetPautas_ReturnsSuccess() throws Exception {
        Pauta pauta1 = PautaStub.createPautaWithoutId();
        pauta1.setId(ID);
        Pauta pauta2 = PautaStub.createPautaWithoutId();
        pauta2.setId(2l);

        List<Pauta> pautas = List.of(pauta1, pauta2);

        PautaResponseDTO responseDTO = PautaStub.createPautaResponseDTO();

        List<PautaResponseDTO> responses = List.of(responseDTO, responseDTO);

        when(pautaService.getPautas()).thenReturn(pautas);
        when(pautaController.getPautas()).thenReturn(ResponseEntity.ok(responses));

        mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is(responseDTO.getTitle())))
                .andExpect(jsonPath("$[0].description", is(responseDTO.getDescription())))
                .andExpect(jsonPath("$[0].sessaoVotacaoId", is(responseDTO.getSessaoVotacaoId().intValue())));
    }

    @Test
    void testGetPautaById_ReturnsSuccess() throws Exception {
        Pauta pauta = PautaStub.createPautaWithId(ID);
        PautaResponseDTO responseDTO = PautaStub.createPautaResponseDTO();

        when(pautaService.getPautaById(ID)).thenReturn(pauta);
        when(pautaController.getPautaById(ArgumentMatchers.any())).thenReturn(ResponseEntity.ok(responseDTO));

        mockMvc.perform(get(PATH + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(responseDTO.getTitle())))
                .andExpect(jsonPath("$.description", is(responseDTO.getDescription())))
                .andExpect(jsonPath("$.sessaoVotacaoId", is(responseDTO.getSessaoVotacaoId().intValue())));
    }

    @Test
    void testCreatePauta_ReturnsSuccess() throws Exception {
        PautaRequestDTO requestDTO = PautaStub.createPautaRequestDTO();
        Pauta pauta = PautaStub.createPautaWithId(ID);
        PautaResponseDTO responseDTO = PautaStub.createPautaResponseDTO();

        when(pautaService.getPautaById(ID)).thenReturn(pauta);
        when(pautaController.createPauta(ArgumentMatchers.any()))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(responseDTO));

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(responseDTO.getTitle())))
                .andExpect(jsonPath("$.description", is(responseDTO.getDescription())))
                .andExpect(jsonPath("$.sessaoVotacaoId", is(responseDTO.getSessaoVotacaoId().intValue())));
    }

    @Test
    void testStartSessaoVotacao_ReturnsSuccess() throws Exception {
        SessaoVotacaoRequestDTO sessaoDTO = SessaoVotacaoStub.createSessaoVotacaoRequestDTO();
        Pauta pauta = PautaStub.createPautaWithId(ID);
        SessaoVotacao sessao = SessaoVotacaoStub.createSessaoVotacaoWithId(ID);

        SessaoVotacaoResponseDTO responseDTO = SessaoVotacaoStub.createSessaoVotacaoResponseDTO();

        when(pautaService.getPautaById(ID)).thenReturn(pauta);
        when(pautaController.startSessaoVotacao(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(responseDTO));

        mockMvc.perform(post(PATH + "/1/sessao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sessaoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.pautaId", is(responseDTO.getPautaId().intValue())))
                .andExpect(jsonPath("$.status", is(responseDTO.getStatus())))
                .andExpect(jsonPath("$.result", is(responseDTO.getResult())))
                .andExpect(jsonPath("$.approved", is(responseDTO.getApproved().intValue())))
                .andExpect(jsonPath("$.reproved", is(responseDTO.getReproved().intValue())))
                .andExpect(jsonPath("$.total", is(responseDTO.getTotal().intValue())));
    }

    @Test
    void testGetResult_ReturnsSuccess() throws Exception {
        ResultResponseDTO resultDTO = PautaStub.createResultResponseDTO();

        when(pautaService.getResult(ID)).thenReturn(resultDTO);
        when(pautaController.getResult(ArgumentMatchers.any()))
                .thenReturn(ResponseEntity.ok(resultDTO));

        mockMvc.perform(get(PATH + "/1/resultado")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(resultDTO.getTitle())))
                .andExpect(jsonPath("$.description", is(resultDTO.getDescription())))
                .andExpect(jsonPath("$.result", is(resultDTO.getResult())))
                .andExpect(jsonPath("$.approved", is(resultDTO.getApproved().intValue())))
                .andExpect(jsonPath("$.reproved", is(resultDTO.getReproved().intValue())));
    }
}
