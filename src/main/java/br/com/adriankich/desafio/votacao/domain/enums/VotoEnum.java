package br.com.adriankich.desafio.votacao.domain.enums;

public enum VotoEnum {
    SIM("Sim"),
    NAO("Não");

    private final String valor;

    /**
     * VotoEnum
     *
     * @param voto String
     */
    VotoEnum( String voto )
    {
        this.valor = voto;
    }

    public String getValor() {
        return this.valor;
    }
}
