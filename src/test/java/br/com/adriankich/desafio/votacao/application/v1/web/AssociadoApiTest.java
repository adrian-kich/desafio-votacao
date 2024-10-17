package br.com.adriankich.desafio.votacao.application.v1.web;

import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoResponseDTO;
import br.com.adriankich.desafio.votacao.application.v1.web.associado.AssociadoApi;
import br.com.adriankich.desafio.votacao.application.v1.web.associado.AssociadoController;
import br.com.adriankich.desafio.votacao.domain.model.Associado;
import br.com.adriankich.desafio.votacao.domain.service.impl.AssociadoServiceImpl;
import br.com.adriankich.desafio.votacao.models.AssociadoStub;
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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AssociadoApi.class)
class AssociadoApiTest {

    public static final String PATH = "/api/v1/associados";
    public static final long ID = 1L;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociadoServiceImpl associadoService;

    @MockBean
    private AssociadoController associadoController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        Mockito.reset(associadoService);
    }

    @Test
    void testGetAssociados_ReturnsListOfAssociado() throws Exception {
        AssociadoResponseDTO associadoResponseDTO = AssociadoStub.createAssociadoResponseDTO();
        Associado associado = AssociadoStub.createAssociadoWithId(ID);

        when(associadoService.getAssociados()).thenReturn(List.of(associado));
        when(associadoController.getAssociados()).thenReturn(ResponseEntity.ok(List.of(associadoResponseDTO)));

        mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    void testGetAssociadoById_ReturnsSuccess() throws Exception {
        Associado associado = AssociadoStub.createAssociadoWithId(ID);
        AssociadoResponseDTO responseDTO = AssociadoStub.createAssociadoResponseDTO();

        when(associadoService.getAssociadoById(ID)).thenReturn(associado);
        when(associadoController.getAssociadoById(any())).thenReturn(ResponseEntity.ok(responseDTO));

        mockMvc.perform(get(PATH + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(associado.getName())))
                .andExpect(jsonPath("$.document", is(associado.getDocument())));
    }

    @Test
    void testGetAssociadoById_ReturnsNotFound() throws Exception {
        when(associadoController.getAssociadoById(any())).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(get(PATH + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAssociadoByDocument_ReturnsSuccess() throws Exception {
        Associado associado = AssociadoStub.createAssociadoWithId(ID);
        AssociadoResponseDTO responseDTO = AssociadoStub.createAssociadoResponseDTO();

        when(associadoService.getAssociadoByDocument(associado.getDocument())).thenReturn(associado);
        when(associadoController.getAssociadoByDocument(any())).thenReturn(ResponseEntity.ok(responseDTO));

        mockMvc.perform(get(PATH + "/documento/03116037000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(associado.getName())))
                .andExpect(jsonPath("$.document", is(associado.getDocument())));
    }

    @Test
    void testGetAssociadoByDocument_ReturnsNotFound() throws Exception {
        when(associadoController.getAssociadoByDocument(any())).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(get(PATH + "/documento/03116037000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateAssociado_ReturnsSuccess() throws Exception {
        AssociadoRequestDTO requestDTO = AssociadoStub.createAssociadoRequestDTO();
        AssociadoResponseDTO responseDTO = AssociadoStub.createAssociadoResponseDTO();

        Associado associado = AssociadoStub.createAssociadoWithId(ID);

        when(associadoService.createAssociado(requestDTO)).thenReturn(associado);
        when(associadoController.createAssociado(any())).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(responseDTO));

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(associado.getName())));
    }

    @Test
    void testCreateAssociado_ReturnsDocumentAlreadyUsed() throws Exception {
        when(associadoController.createAssociado(any())).thenReturn(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build());

        mockMvc.perform(post(PATH + "/documento/03116037000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
