package com.conta.poupanca.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.conta.poupanca.model.User;
import com.conta.poupanca.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userRepository.save(user);
    }

}
