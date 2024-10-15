package br.com.adriankich.desafio.votacao.application.v1.swagger;

import br.com.adriankich.desafio.votacao.application.v1.exception.StandardError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface CpfValidatorSwagger {

    final String TAG_NAME = "Validação de documentos";

    @Operation(
            operationId = "validar documentos",
            summary = "Verifica se o documento informado é válido.",
            tags = { TAG_NAME },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Associado pode realizar a operação",
                            content = @Content(
                                    schema = @Schema(example = "{\n\"status\": \"ABLE_TO_VOTE\"\n}"),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Associado não pode realizar a operação",
                            content = @Content(
                                    schema = @Schema(example = "{\n\"status\": \"UNABLE_TO_VOTE\"\n}"),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            }
    )
    public ResponseEntity validator(@PathVariable String cpf);
}
