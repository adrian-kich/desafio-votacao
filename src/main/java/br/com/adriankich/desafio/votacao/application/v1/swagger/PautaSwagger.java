package br.com.adriankich.desafio.votacao.application.v1.swagger;

import br.com.adriankich.desafio.votacao.application.v1.dto.*;
import br.com.adriankich.desafio.votacao.application.v1.exception.StandardError;
import br.com.adriankich.desafio.votacao.domain.model.Associado;
import br.com.adriankich.desafio.votacao.domain.model.Pauta;
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

public interface PautaSwagger {

    final String TAG_NAME = "Pauta";

    @Operation(
            operationId = "buscar pautas",
            summary = "Busca todos os pautas",
            tags = { TAG_NAME },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = SwaggerConfig.SUCCESS_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = PautaResponseDTO.class ),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            }
    )
    public ResponseEntity<List<PautaResponseDTO>> getPautas();

    @Operation(
            operationId = "buscar pauta",
            summary = "Busca pauta de acordo com o id",
            tags = { TAG_NAME },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = SwaggerConfig.SUCCESS_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = PautaResponseDTO.class ),
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
    public ResponseEntity<PautaResponseDTO> getPautaById(@PathVariable Long id);

    @Operation(
            operationId = "criar pauta",
            summary = "Cria uma nova pauta",
            tags = { TAG_NAME },
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = SwaggerConfig.SUCCESS_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = PautaResponseDTO.class ),
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
    public ResponseEntity<PautaResponseDTO> createPauta(@RequestBody @Valid PautaRequestDTO pautaDTO);

    @Operation(
            operationId = "criar uma sessão de votação",
            summary = "Cria uma sessão de votação para a pauta",
            tags = { TAG_NAME },
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = SwaggerConfig.SUCCESS_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = SessaoVotacaoResponseDTO.class ),
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
    public ResponseEntity<SessaoVotacaoResponseDTO> startSessaoVotacao(@PathVariable Long id,
                                                                @RequestBody(required = false) SessaoVotacaoRequestDTO sessaoDTO);

    @Operation(
            operationId = "informar resultado",
            summary = "Informa o resultado da pauta",
            tags = { TAG_NAME },
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = SwaggerConfig.SUCCESS_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = ResultResponseDTO.class ),
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
    public ResponseEntity<ResultResponseDTO> getResult(@PathVariable Long id);
}
