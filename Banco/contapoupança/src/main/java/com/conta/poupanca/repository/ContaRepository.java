package com.conta.poupanca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conta.poupanca.model.Conta;
import com.conta.poupanca.model.Titular;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
	
	

	
	
	

}
