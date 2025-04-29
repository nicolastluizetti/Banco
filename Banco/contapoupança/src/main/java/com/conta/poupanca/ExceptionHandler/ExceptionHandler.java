package com.conta.poupanca.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.conta.poupanca.Exception.EntidadeNaoEncontradaExcption;
import com.conta.poupanca.Exception.NegocioException;

@ControllerAdvice
public class ExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler(EntidadeNaoEncontradaExcption.class)
	public String tratarEntidadeNaoEncontradaException(
			EntidadeNaoEncontradaExcption e) {
			return e.getMessage();
		
	
	}
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(NegocioException.class)
	public String tratarNegocioException(NegocioException e) {
		
			return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@org.springframework.web.bind.annotation.ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public String tratarHttpMediaTypeNotSupportedException(NegocioException e) {
		
		
		return e.getMessage();
	}
}
