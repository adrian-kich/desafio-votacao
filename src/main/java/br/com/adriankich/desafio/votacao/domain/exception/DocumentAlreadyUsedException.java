package br.com.adriankich.desafio.votacao.domain.exception;

public class DocumentAlreadyUsedException extends RuntimeException {

    /**
     * DocumentAlreadyUsedException
     *
     * @param message String
     */
    public DocumentAlreadyUsedException( String message )
    {
        super( message );
    }

    /**
     * DocumentAlreadyUsedException
     *
     * @param cause Throwable
     */
    public DocumentAlreadyUsedException( Throwable cause )
    {
        super( cause );
    }

    /**
     * DocumentAlreadyUsedException
     *
     * @param message String
     * @param cause Throwable
     */
    public DocumentAlreadyUsedException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
