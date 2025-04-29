package com.conta.poupanca.Exception;

public class ContaemusoException extends EntidadeEmUsoException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ContaemusoException(String mensagem) {
		super(mensagem);
		
	}
	public ContaemusoException(Long contaId) {
		this(String.format("A conta com código %d, esta em uso", contaId));
	}

}
