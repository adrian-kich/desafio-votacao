package br.com.adriankich.desafio.votacao.domain.enums;

public enum SessaoEnum {

    ABERTA("Aberta"),
    ENCERRADA("Encerrada"),

    APROVADA("Aprovada"),
    REPROVADA("Reprovada"),
    EMPATADA("Empate"),
    EM_VOTACAO("Em votação");

    private final String value;

    SessaoEnum(String status )
    {
        this.value = status;
    }

    public String getValue()
    {
        return value;
    }
}
