package br.com.adriankich.desafio.votacao.domain.enums;

public enum PautaEnum {

    APROVADA("Aprovada"),
    REPROVADA("Reprovada"),
    EMPATADA("Empatada"),
    EM_VOTACAO("Em votação"),
    VOTACAO_NAO_INICIADA("Votação não iniciada");

    private final String value;

    /**
     * PautaEnum
     *
     * @param status
     */
    PautaEnum(String status) {
        this.value = status;
    }

    /**
     * getValue
     *
     * @return String
     */
    public String getValue() {
        return value;
    }
}
