package com.conta.poupanca.Exception;

public class TitularNaoencontradoException extends EntidadeNaoEncontradaExcption{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TitularNaoencontradoException(String mensagem) {
		super(mensagem);
		
	}
	public TitularNaoencontradoException(Long titularId) {
		this(String.format("Não existe um cadastro de titular com código %d", titularId));
	}

}
