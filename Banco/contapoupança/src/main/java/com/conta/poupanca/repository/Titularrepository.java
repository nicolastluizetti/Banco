package com.conta.poupanca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conta.poupanca.model.Titular;

@Repository
public interface Titularrepository extends JpaRepository<Titular, Long>{
	
	
	Optional<Titular> findByCpf(long cpf);
 

	
}
