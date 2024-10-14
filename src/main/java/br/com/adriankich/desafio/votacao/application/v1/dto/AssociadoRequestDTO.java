package br.com.adriankich.desafio.votacao.application.v1.dto;

import br.com.adriankich.desafio.votacao.domain.validation.CpfOrCnpj;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AssociadoRequestDTO {

    @NotEmpty( message = "O nome do associado é obrigatório" )
    private String name;

    @CpfOrCnpj()
    @NotEmpty( message = "O documento (CPF ou CNPJ) do associado é obrigatório" )
    private String document;
}
