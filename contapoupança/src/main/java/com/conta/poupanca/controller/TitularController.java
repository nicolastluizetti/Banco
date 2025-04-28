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

import com.conta.poupanca.model.Conta;
import com.conta.poupanca.model.Titular;
import com.conta.poupanca.repository.Titularrepository;
@RestController
@RequestMapping(value = "/titulares")
public class TitularController {
	@Autowired
	private Titularrepository titularRepository;
	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@GetMapping
	public List<Titular> listar() {
		return titularRepository.findAll();
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@GetMapping("/{titularId}")
	public ResponseEntity<Titular> buscar(@PathVariable Long titularId) {
		Optional<Titular> conta = titularRepository.findById(titularId);
		
		if (conta != null) {
			return ResponseEntity.ok(conta.get());
		}
		return ResponseEntity.notFound().build();
		}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Titular titular) {
		
			titular = titularRepository.save(titular);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(titular);
	
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@PutMapping("/{contaId}")
	public ResponseEntity<?> atualizar(@PathVariable Long titularid,
			@RequestBody Conta conta) {
		
			Titular titularAtual = titularRepository.findById(titularid).orElse(null);
			
			if (titularAtual != null) {
				BeanUtils.copyProperties(conta, titularAtual, "id");
				
				titularAtual = titularRepository.save(titularAtual);
				return ResponseEntity.ok(titularAtual);
			}
			
			return ResponseEntity.notFound().build();
		
		
		}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@DeleteMapping("/{contaId}")
	public ResponseEntity<Conta> remover(@PathVariable Long contaId) {
		
			titularRepository.deleteById(contaId);	
			return ResponseEntity.noContent().build();
	
	}
	

		
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@GetMapping("/{titularcpf}")
	public Titular cpf(long cpf) {
		return titularRepository.findBycpf(cpf);
	}
	
}

