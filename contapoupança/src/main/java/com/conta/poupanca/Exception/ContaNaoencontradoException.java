package com.conta.poupanca.Exception;

public class ContaNaoencontradoException extends EntidadeNaoEncontradaExcption{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ContaNaoencontradoException(String mensagem) {
		super(mensagem);
		
	}
	public ContaNaoencontradoException(Long ContaId) {
		this(String.format("Não existe um cadastro de conta com código %d", ContaId));
	}

}
