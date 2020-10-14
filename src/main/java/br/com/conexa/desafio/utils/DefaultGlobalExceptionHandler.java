package br.com.conexa.desafio.utils;

import java.util.Objects;
import java.util.stream.Collectors;

import javax.naming.CommunicationException;

import org.springframework.core.NestedRuntimeException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import br.com.conexa.desafio.excecao.ExceptionResponse;
import br.com.conexa.desafio.excecao.InternalException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultGlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleException(Exception e) {
		log.error(e.getMessage(), e);
		return RestResponseEntity.exceptionFail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getCause());
	}

	@ExceptionHandler(InternalException.class)
	public ResponseEntity<ExceptionResponse> handleInternalException(InternalException e) {
		log.error(e.getMessage(), e);
		return RestResponseEntity.exceptionFail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleNotFoundException(CommunicationException e) {
		log.info(e.getMessage(), e);
		return RestResponseEntity.exceptionFail(HttpStatus.NOT_FOUND.value(), e);
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class })
	public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(NestedRuntimeException e) {
		log.error(e.getMessage(), e);
		return RestResponseEntity.fail(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		String message = e.getBindingResult().getAllErrors().stream()
				.map(fieldError -> Objects.isNull(fieldError) ? "null"
						: fieldError.getObjectName() + ": " + fieldError.getDefaultMessage())
				.collect(Collectors.joining(", "));
		return RestResponseEntity.fail(HttpStatus.BAD_REQUEST.value(), message);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ExceptionResponse> handleNoHandlerFoundException(NoHandlerFoundException e) {
		log.error(e.getMessage(), e);
		return RestResponseEntity.fail(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		log.error(e.getMessage(), e);
		return RestResponseEntity.fail(HttpStatus.METHOD_NOT_ALLOWED.value(),
				HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<ExceptionResponse> handleHttpMediaTypeNotAcceptableException(
			HttpMediaTypeNotAcceptableException e) {
		log.error(e.getMessage(), e);
		return RestResponseEntity.fail(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
	}

}
