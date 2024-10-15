package br.com.adriankich.desafio.votacao.application.v1.swagger;

import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.AssociadoResponseDTO;
import br.com.adriankich.desafio.votacao.application.v1.exception.StandardError;
import br.com.adriankich.desafio.votacao.domain.model.Associado;
import br.com.adriankich.desafio.votacao.infrastructure.config.SwaggerConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AssociadoSwagger {

    final String TAG_NAME = "Associado";

    @Operation(
            operationId = "buscar associados",
            summary = "Busca todos os associados",
            tags = { TAG_NAME },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = SwaggerConfig.SUCCESS_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = Associado.class ),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            }
    )
    public ResponseEntity<List<AssociadoResponseDTO>> getAssociados();

    @Operation(
            operationId = "buscar associado",
            summary = "Busca associado de acordo com o documento",
            tags = { TAG_NAME },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = SwaggerConfig.SUCCESS_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = Associado.class ),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = SwaggerConfig.NOT_FOUND_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = StandardError.class ),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            }
    )
    public ResponseEntity<AssociadoResponseDTO> getAssociadoByDocument(@PathVariable String document);

    @Operation(
            operationId = "buscar associado",
            summary = "Busca associado de acordo com o id",
            tags = { TAG_NAME },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = SwaggerConfig.SUCCESS_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = Associado.class ),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = SwaggerConfig.NOT_FOUND_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = StandardError.class ),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            }
    )
    public ResponseEntity<AssociadoResponseDTO> getAssociadoById(@PathVariable Long id);

    @Operation(
            operationId = "criar associado",
            summary = "Cria um novo associado",
            tags = { TAG_NAME },
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = SwaggerConfig.SUCCESS_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = Associado.class ),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = SwaggerConfig.BAD_REQUEST_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = StandardError.class ),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            }
    )
    public ResponseEntity<AssociadoResponseDTO> createAssociado(@RequestBody @Valid AssociadoRequestDTO associadoDTO);

}
