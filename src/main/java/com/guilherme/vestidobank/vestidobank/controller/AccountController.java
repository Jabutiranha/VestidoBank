package com.guilherme.vestidobank.vestidobank.controller;

import com.guilherme.vestidobank.vestidobank.exception.ResourceNotFoundException;
import com.guilherme.vestidobank.vestidobank.model.Account;
import com.guilherme.vestidobank.vestidobank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private AccountRepository repository;


    @GetMapping("/account")
    public List<Account> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getById(@PathVariable(value = "id") long accountId) throws ResourceNotFoundException {
        Account account = repository.findById(accountId).orElseThrow(() ->
                new ResourceNotFoundException("Conta não existe: " + accountId));
        return ResponseEntity.ok().body(account);
    }

    @PostMapping("/account/create")
    public Account createAccount(@Validated @RequestBody Account account) {
        return repository.save(account);
    }

    @PutMapping("/account/edit/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable(value = "id") Long accountId,
                                                   @Validated @RequestBody Account account) throws  ResourceNotFoundException{
        Account account1 = repository.findById(accountId).orElseThrow(()-> new ResourceNotFoundException("Conta não encontrado: " + accountId));
        account1.setUsuario(account.getUsuario());
        account1.setSaldo(account.getSaldo());
        return ResponseEntity.ok(repository.save(account1));
    }

    @DeleteMapping("/account/deletar/{id}")
    public Map<String, Boolean> deleteAccount(@PathVariable(value = "id") Long accountId) throws Exception{
        Account account = repository.findById(accountId).orElseThrow(()-> new ResourceNotFoundException("Conta não encontrado: " + accountId));
        repository.delete(account);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
