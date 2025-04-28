package com.conta.poupanca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conta.poupanca.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	

}
