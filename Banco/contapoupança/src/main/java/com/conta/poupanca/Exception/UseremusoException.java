package com.conta.poupanca.Exception;

public class UseremusoException extends EntidadeEmUsoException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UseremusoException(String mensagem) {
		super(mensagem);
		
	}
	public UseremusoException(Long UserId) {
		this(String.format("O suario com código %d, esta em uso", UserId));
	}

}
