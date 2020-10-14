package br.com.conexa.desafio.excecao;

public class InternalException extends RestException {

	private static final long serialVersionUID = 1L;

	public InternalException(String message) {
		super(message);
	}

	public InternalException(ExceptionResponse exceptionResponse) {
		super(exceptionResponse);
	}

	public InternalException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
