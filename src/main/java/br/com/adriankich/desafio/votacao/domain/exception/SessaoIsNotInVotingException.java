package br.com.adriankich.desafio.votacao.domain.exception;

public class SessaoIsNotInVotingException extends RuntimeException {

    /**
     * SessaoIsNotInVotingException
     *
     * @param message String
     */
    public SessaoIsNotInVotingException( String message )
    {
        super( message );
    }

    /**
     * SessaoIsNotInVotingException
     *
     * @param cause Throwable
     */
    public SessaoIsNotInVotingException( Throwable cause )
    {
        super( cause );
    }

    /**
     * SessaoIsNotInVotingException
     *
     * @param message String
     * @param cause Throwable
     */
    public SessaoIsNotInVotingException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
