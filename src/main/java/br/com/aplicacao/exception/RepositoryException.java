package br.com.aplicacao.exception;

/**
 * Exception Padrão para repository
 * 
 * @author Ricardo Braga
 */
public class RepositoryException extends Exception {

	private static final long serialVersionUID = 6756183795065428289L;

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(String message,Throwable cause) {
		super(message,cause);
	}
}
