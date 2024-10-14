package br.com.adriankich.desafio.votacao.domain.exception;

public class AlreadyVotedException extends  RuntimeException {

    /**
     * AlreadyVotedException
     *
     * @param message String
     */
    public AlreadyVotedException( String message )
    {
        super( message );
    }

    /**
     * AlreadyVotedException
     *
     * @param cause Throwable
     */
    public AlreadyVotedException( Throwable cause )
    {
        super( cause );
    }

    /**
     * AlreadyVotedException
     *
     * @param message String
     * @param cause Throwable
     */
    public AlreadyVotedException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
