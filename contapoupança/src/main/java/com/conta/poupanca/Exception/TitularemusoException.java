package com.conta.poupanca.Exception;

public class TitularemusoException extends EntidadeEmUsoException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TitularemusoException(String mensagem) {
		super(mensagem);
		
	}
	public TitularemusoException(Long titularId) {
		this(String.format("O titular com código %d, esta em uso", titularId));
	}

}
