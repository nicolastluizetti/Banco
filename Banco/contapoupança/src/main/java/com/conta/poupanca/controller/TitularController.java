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

import com.conta.poupanca.Exception.TitularNaoencontradoException;
import com.conta.poupanca.model.Conta;
import com.conta.poupanca.model.Titular;
import com.conta.poupanca.repository.Titularrepository;
@RestController
@RequestMapping("/titulares")
public class TitularController {

    @Autowired
    private Titularrepository titularRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Titular> listar() {
        try {
            return titularRepository.findAll();
        } catch (Exception e) {
            throw new TitularNaoencontradoException("Erro ao listar titulares: " + e.getMessage());
        }
    }

    @GetMapping("/{titularId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> buscar(@PathVariable Long titularId) {
        try {
            Optional<Titular> titular = titularRepository.findById(titularId);
            if (titular.isPresent()) {
                return ResponseEntity.ok(titular.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Titular com ID " + titularId + " não encontrado.");
        } catch (Exception e) {
            throw new TitularNaoencontradoException("Erro ao buscar titular: " + e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Titular adicionar(@RequestBody Titular titular) {
        try {
            return titularRepository.save(titular);
        } catch (Exception e) {
            throw new TitularNaoencontradoException("Erro ao adicionar titular: " + e.getMessage());
        }
    }

    @PutMapping("/{titularId}")
    public ResponseEntity<?> atualizar(@PathVariable Long titularId,
                                       @RequestBody Conta conta) {
        try {
            Optional<Titular> optionalTitular = titularRepository.findById(titularId);
            if (!optionalTitular.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Titular com ID " + titularId + " não encontrado.");
            }

            Titular titularAtual = optionalTitular.get();
            BeanUtils.copyProperties(conta, titularAtual, "id");
            Titular atualizado = titularRepository.save(titularAtual);

            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            throw new TitularNaoencontradoException("Erro ao atualizar titular: " + e.getMessage());
        }
    }

    @DeleteMapping("/{titularId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long titularId) {
        try {
            if (!titularRepository.existsById(titularId)) {
                throw new TitularNaoencontradoException("Titular com ID " + titularId + " não encontrado.");
            }
            titularRepository.deleteById(titularId);
        } catch (Exception e) {
            throw new TitularNaoencontradoException("Erro ao remover titular: " + e.getMessage());
        }
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<?> buscarPorCpf(@PathVariable long cpf) {
        try {
            Optional<Titular> titular = titularRepository.findByCpf(cpf);
            if (titular == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Titular com CPF " + cpf + " não encontrado.");
            }
            return ResponseEntity.ok(titular);
        } catch (Exception e) {
            throw new TitularNaoencontradoException("Erro ao buscar titular por CPF: " + e.getMessage());
        }
    }
}

