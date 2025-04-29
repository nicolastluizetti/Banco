package com.conta.poupanca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "conta")
public abstract class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	protected Long id;
	
	protected Long numero;
	
	@ManyToOne
    @JoinColumn(name = "conta") // Chave estrangeira
	protected Titular Titular;
	
	protected Double saldo;
	
	
	public void sacar(Double valor) throws Exception {
	    if (valor < this.saldo) {
	        throw new Exception("Saldo insuficiente");
	    }
	    this.saldo -= valor;
	}
	
	public void depositar(Double valor) {
        this.saldo += valor;
    }
	

}
