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
import org.springframework.web.bind.annotation.RestController;

import com.conta.poupanca.Exception.EntidadeEmUsoException;
import com.conta.poupanca.Exception.EntidadeNaoEncontradaExcption;
import com.conta.poupanca.model.Conta;
import com.conta.poupanca.model.Titular;
import com.conta.poupanca.repository.ContaRepository;

@RestController
@RequestMapping(value = "/contas")
public class ContaController {
	@Autowired
	private ContaRepository contaRepository;
	
	
	@GetMapping
	public List<Conta> listar() {
		return contaRepository.findAll();
	}
	
	@GetMapping("/{contaId}")
	public ResponseEntity<Conta> buscar(@PathVariable Long contaId) {
		Optional<Conta> conta = contaRepository.findById(contaId);
		
		if (conta != null) {
			return ResponseEntity.ok(conta.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Conta conta) {
		try {
			conta = contaRepository.save(conta);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(conta);
		} catch (EntidadeNaoEncontradaExcption e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{contaId}")
	public ResponseEntity<?> atualizar(@PathVariable Long contaId,
			@RequestBody Conta conta) {
		try {
			Conta contaAtual = contaRepository.findById(contaId).orElse(null);
			
			if (contaAtual != null) {
				BeanUtils.copyProperties(conta, contaAtual, "id");
				
				contaAtual = contaRepository.save(contaAtual);
				return ResponseEntity.ok(contaAtual);
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
			contaRepository.deleteById(contaId);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaExcption e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping("/{titular}")
	public Conta achar(@PathVariable Titular titular) {
		return contaRepository.findByTitular(titular);
		
	}
	
}

