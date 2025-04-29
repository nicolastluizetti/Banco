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

import com.conta.poupanca.Exception.UserNaoencontradoException;
import com.conta.poupanca.Exception.UseremusoException;
import com.conta.poupanca.model.Conta;
import com.conta.poupanca.model.User;
import com.conta.poupanca.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> listar() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new UserNaoencontradoException("Erro ao listar usuários: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> buscar(@PathVariable Long userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Usuário com ID " + userId + " não encontrado.");
        } catch (Exception e) {
            throw new UserNaoencontradoException("Erro ao buscar usuário: " + e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User adicionar(@RequestBody User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserNaoencontradoException("Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> atualizar(@PathVariable Long userId, @RequestBody User user) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (!optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Usuário com ID " + userId + " não encontrado.");
            }

            User userAtual = optionalUser.get();
            BeanUtils.copyProperties(user, userAtual, "id");
            User atualizado = userRepository.save(userAtual);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            throw new UserNaoencontradoException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long userId) {
        try {
            if (!userRepository.existsById(userId)) {
                throw new UserNaoencontradoException("Usuário com ID " + userId + " não encontrado.");
            }
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new UserNaoencontradoException("Erro ao remover usuário: " + e.getMessage());
        }
    }
}