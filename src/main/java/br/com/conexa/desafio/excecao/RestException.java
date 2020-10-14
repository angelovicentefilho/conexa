package br.com.conexa.desafio.excecao;

import lombok.Getter;

public abstract class RestException extends Exception {

	private static final long serialVersionUID = 1L;

	@Getter
	private ExceptionResponse exceptionResponse;

	public RestException(String message) {
		super(message);
		this.exceptionResponse = ExceptionResponse.instance();
		this.exceptionResponse.addErrorMessage(message);
	}

	public RestException(ExceptionResponse exceptionResponse) {
		this.exceptionResponse = exceptionResponse;
	}

	public RestException(String message, Throwable throwable) {
		super(message, throwable);
		this.exceptionResponse = ExceptionResponse.instance();
		this.exceptionResponse.addErrorMessage(message);
	}

}
