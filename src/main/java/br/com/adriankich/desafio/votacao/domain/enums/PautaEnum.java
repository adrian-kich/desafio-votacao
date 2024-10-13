package br.com.adriankich.desafio.votacao.domain.enums;

public enum PautaEnum {

    APROVADA("Aprovada"),
    REPROVADA("Reprovada"),
    EMPATADA("Empatada"),
    EM_VOTACAO("Em votação"),
    VOTACAO_NAO_INICIADA("Votação não iniciada");

    private final String value;

    PautaEnum(String status )
    {
        this.value = status;
    }

    public String getValue()
    {
        return value;
    }
}
