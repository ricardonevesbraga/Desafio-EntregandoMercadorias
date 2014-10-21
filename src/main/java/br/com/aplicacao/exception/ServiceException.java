package br.com.aplicacao.exception;

/**
 * Exception Padrão para services
 * 
 * @author Ricardo Braga
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -3346313279043958074L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message,Throwable cause) {
		super(message,cause);
	}

}
