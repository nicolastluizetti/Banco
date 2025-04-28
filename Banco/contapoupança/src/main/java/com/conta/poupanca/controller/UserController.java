package com.conta.poupanca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

		
		
		@GetMapping
		public List<User> listar() {
			return userRepository.findAll();
		}
		
		@ResponseStatus(HttpStatus.NOT_FOUND)
		@GetMapping("/{userId}")
		public ResponseEntity<User> buscar(@PathVariable Long userId) {
			Optional<User> user = userRepository.findById(userId);
			
			if (user != null) {
				return ResponseEntity.ok(user.get());
			}
			
			return ResponseEntity.notFound().build();
		}
		
		@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
		@PostMapping
	    public ResponseEntity<?> adicionar(@RequestBody User user) {
	        
	            user = userRepository.save(user);
	            return ResponseEntity.status(HttpStatus.CREATED)
						.body(user);
		

	           
	    }
		@ResponseStatus(HttpStatus.NOT_FOUND)
		@PutMapping("/{userId}")
		public User atualizar(@PathVariable Long userid,
				@RequestBody Conta user) {
				User userAtual = userRepository.findById(userid).orElse(null);
				
				if (userAtual != null) {
					BeanUtils.copyProperties(user, userAtual, "id");
					
					userAtual = userRepository.save(userAtual);
					return userRepository.save(userAtual);
				}
				return userRepository.save(userAtual);
				
			
		}
		
		@ResponseStatus(HttpStatus.NOT_FOUND)
		@DeleteMapping("/{userId}")
		public ResponseEntity<Conta> remover(@PathVariable Long userId) {
			
				userRepository.deleteById(userId);	
				return ResponseEntity.noContent().build();
				
			
		}
		

			
		
		
		
	}


