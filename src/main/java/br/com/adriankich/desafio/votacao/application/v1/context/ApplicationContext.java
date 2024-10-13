package br.com.adriankich.desafio.votacao.application.v1.context;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ApplicationContext {

    public static final String VERSION = "api/v1/";

    /**
     * getServerBaseUrl
     *
     * @return String
     */
    public static String getServerBaseUrl()
    {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + VERSION;
    }
}
