package com.conta.poupanca.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entidade não encontrada")
public class EntidadeNaoEncontradaExcption  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaExcption(String mensagem) {
		super(mensagem);
	}
	
}
