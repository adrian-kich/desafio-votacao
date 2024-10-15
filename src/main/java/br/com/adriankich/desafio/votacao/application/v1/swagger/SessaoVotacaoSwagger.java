package br.com.adriankich.desafio.votacao.application.v1.swagger;

import br.com.adriankich.desafio.votacao.application.v1.dto.PautaResponseDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.SessaoVotacaoResponseDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.VotoRequestDTO;
import br.com.adriankich.desafio.votacao.application.v1.dto.VotoResponseDTO;
import br.com.adriankich.desafio.votacao.application.v1.exception.StandardError;
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

public interface SessaoVotacaoSwagger {

    final String TAG_NAME = "Sessão de Votação";

    @Operation(
            operationId = "buscar sessão",
            summary = "Busca sessão de votação de acordo com o id",
            tags = { TAG_NAME },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = SwaggerConfig.SUCCESS_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = SessaoVotacaoResponseDTO.class ),
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
    public ResponseEntity<SessaoVotacaoResponseDTO> getSessaoById(@PathVariable Long id);

    @Operation(
            operationId = "adicionar voto",
            summary = "Adiciona um voto a sessão de votação",
            tags = { TAG_NAME },
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = SwaggerConfig.SUCCESS_MESSAGE,
                            content = @Content(
                                    schema = @Schema( implementation = VotoResponseDTO.class ),
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
    public ResponseEntity<VotoResponseDTO> addVoto(@PathVariable Long id, @RequestBody @Valid VotoRequestDTO votoDTO);
}
