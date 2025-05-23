package com.conta.poupanca.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "entidade em uso")
public class EntidadeEmUsoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
	
}


