package br.com.conexa.desafio.excecao;

public class ExceptionHandler {
	private ExceptionHandler() {
	}

	public static ExceptionResponse handleException(RestException ce) {
		return ce.getExceptionResponse();
	}

}
