package com.conta.poupanca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = false)
public class Contacorrente extends Conta{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	protected int id;
	
	protected Long numero;
	
	@ManyToOne
	@JoinColumn(name = "titular_cpf")
	protected Titular titular;
	
	protected Double saldo;
	
	
	

}
