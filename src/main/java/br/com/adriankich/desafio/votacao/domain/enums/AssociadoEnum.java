package br.com.adriankich.desafio.votacao.domain.enums;

public enum AssociadoEnum {

    ABLE_TO_VOTE("ABLE_TO_VOTE"),
    UNABLE_TO_VOTE("UNABLE_TO_VOTE");

    private final String value;

    AssociadoEnum(String status )
    {
        this.value = status;
    }

    public String getValue()
    {
        return value;
    }
}
