package br.com.adriankich.desafio.votacao.domain.exception;

public class AlreadyExistsException extends RuntimeException {

    /**
     * AlreadyExistsException
     *
     * @param message String
     */
    public AlreadyExistsException( String message )
    {
        super( message );
    }

    /**
     * AlreadyExistsException
     *
     * @param cause Throwable
     */
    public AlreadyExistsException( Throwable cause )
    {
        super( cause );
    }

    /**
     * AlreadyExistsException
     *
     * @param message String
     * @param cause Throwable
     */
    public AlreadyExistsException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
