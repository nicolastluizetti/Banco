package com.conta.poupanca.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;




@Entity
public class Titular {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	protected Long id;
	
	protected long cpf;
	
	protected String nome;
	
	protected Long telefone;
	
	@OneToMany(mappedBy = "Titular", cascade = CascadeType.ALL)
	protected List<Conta> contas;

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long i) {
		this.telefone = i;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	
	

}
