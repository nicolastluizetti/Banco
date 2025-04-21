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

import com.conta.poupanca.Exception.EntidadeEmUsoException;
import com.conta.poupanca.Exception.EntidadeNaoEncontradaExcption;
import com.conta.poupanca.model.Conta;
import com.conta.poupanca.model.Titular;
import com.conta.poupanca.repository.Titularrepository;

public class TitularController {
	@Autowired
	private Titularrepository titularRepository;
	
	
	@GetMapping
	public List<Titular> listar() {
		return titularRepository.findAll();
	}
	
	@GetMapping("/{titularId}")
	public ResponseEntity<Titular> buscar(@PathVariable Long titularId) {
		Optional<Titular> conta = titularRepository.findById(titularId);
		
		if (conta != null) {
			return ResponseEntity.ok(conta.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Titular titular) {
		try {
			titular = titularRepository.save(titular);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(titular);
		} catch (EntidadeNaoEncontradaExcption e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{contaId}")
	public ResponseEntity<?> atualizar(@PathVariable Long titularid,
			@RequestBody Conta conta) {
		try {
			Titular titularAtual = titularRepository.findById(titularid).orElse(null);
			
			if (titularAtual != null) {
				BeanUtils.copyProperties(conta, titularAtual, "id");
				
				titularAtual = titularRepository.save(titularAtual);
				return ResponseEntity.ok(titularAtual);
			}
			
			return ResponseEntity.notFound().build();
		
		} catch (EntidadeNaoEncontradaExcption e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{contaId}")
	public ResponseEntity<Conta> remover(@PathVariable Long contaId) {
		try {
			titularRepository.deleteById(contaId);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaExcption e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	

		
	
	@GetMapping("/{titularcpf}")
	public Titular cpf(long cpf) {
		return titularRepository.findBycpf(cpf);
	}
	
}

