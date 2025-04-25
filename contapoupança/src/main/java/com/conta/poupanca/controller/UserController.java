package com.conta.poupanca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conta.poupanca.Exception.EntidadeEmUsoException;
import com.conta.poupanca.Exception.EntidadeNaoEncontradaExcption;
import com.conta.poupanca.model.Conta;
import com.conta.poupanca.model.User;
import com.conta.poupanca.repository.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class UserController {
		
		@Autowired
		private UserRepository userRepository;
		
		@Autowired
	    private PasswordEncoder passwordEncoder;
		
		
		@GetMapping
		public List<User> listar() {
			return userRepository.findAll();
		}
		
		@GetMapping("/{userId}")
		public ResponseEntity<User> buscar(@PathVariable Long userId) {
			Optional<User> user = userRepository.findById(userId);
			
			if (user != null) {
				return ResponseEntity.ok(user.get());
			}
			
			return ResponseEntity.notFound().build();
		}
		
		@PostMapping
	    public ResponseEntity<?> adicionar(@RequestBody User user) {
	        try {
	            
	            user.setSenha(passwordEncoder.encode(user.getSenha()));

	            user = userRepository.save(user);

	            return ResponseEntity.status(HttpStatus.CREATED).body(user);
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Erro ao cadastrar: " + e.getMessage());
	        }
	    }
		
		@PutMapping("/{userId}")
		public ResponseEntity<?> atualizar(@PathVariable Long userid,
				@RequestBody Conta user) {
			try {
				User userAtual = userRepository.findById(userid).orElse(null);
				
				if (userAtual != null) {
					BeanUtils.copyProperties(user, userAtual, "id");
					
					userAtual = userRepository.save(userAtual);
					return ResponseEntity.ok(userAtual);
				}
				
				return ResponseEntity.notFound().build();
			
			} catch (EntidadeNaoEncontradaExcption e) {
				return ResponseEntity.badRequest()
						.body(e.getMessage());
			}
		}
		
		@DeleteMapping("/{userId}")
		public ResponseEntity<Conta> remover(@PathVariable Long userId) {
			try {
				userRepository.deleteById(userId);	
				return ResponseEntity.noContent().build();
				
			} catch (EntidadeNaoEncontradaExcption e) {
				return ResponseEntity.notFound().build();
				
			} catch (EntidadeEmUsoException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		}
		

			
		
		
		
	}


