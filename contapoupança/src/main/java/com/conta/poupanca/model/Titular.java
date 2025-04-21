package com.conta.poupanca.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;



@Data
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

}
