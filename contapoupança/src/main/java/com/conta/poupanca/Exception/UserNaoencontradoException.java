package com.conta.poupanca.Exception;

public class UserNaoencontradoException extends EntidadeNaoEncontradaExcption{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserNaoencontradoException(String mensagem) {
		super(mensagem);
		
	}
	public UserNaoencontradoException(Long UserId) {
		this(String.format("Não existe um cadastro de Usuario com código %d", UserId));
	}

}
