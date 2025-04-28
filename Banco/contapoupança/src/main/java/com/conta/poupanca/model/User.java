package com.conta.poupanca.model;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Table(name = "`user`")
@Entity
public class User {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	
	

	public boolean senhaCoincideCom(String senha) {
		return getSenha().equals(senha);
	}
	
	public boolean senhaNaoCoincideCom(String senha) {
		return !senhaCoincideCom(senha);
	}
	
	
	
	

}
