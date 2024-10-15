package br.com.adriankich.desafio.votacao.domain.exception;

public class InvalidCpfException extends RuntimeException {

    /**
     * InvalidCpfException
     *
     * @param message String
     */
    public InvalidCpfException( String message )
    {
        super( message );
    }

    /**
     * InvalidCpfException
     *
     * @param cause Throwable
     */
    public InvalidCpfException( Throwable cause )
    {
        super( cause );
    }

    /**
     * InvalidCpfException
     *
     * @param message String
     * @param cause Throwable
     */
    public InvalidCpfException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
