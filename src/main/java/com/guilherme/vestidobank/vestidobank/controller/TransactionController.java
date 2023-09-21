package com.guilherme.vestidobank.vestidobank.controller;

import com.guilherme.vestidobank.vestidobank.Enumeration.TransactionType;
import com.guilherme.vestidobank.vestidobank.exception.ResourceNotFoundException;
import com.guilherme.vestidobank.vestidobank.model.Account;
import com.guilherme.vestidobank.vestidobank.model.Transaction;
import com.guilherme.vestidobank.vestidobank.repository.AccountRepository;
import com.guilherme.vestidobank.vestidobank.repository.TransactionRepository;
import com.guilherme.vestidobank.vestidobank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/transaction")
    public List<Transaction> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable(value = "id") long transactionId) throws ResourceNotFoundException {

        Transaction transactions = repository.findById(transactionId).orElseThrow(() ->
                new ResourceNotFoundException("Transação não encontrado: " + transactionId));
        return ResponseEntity.ok().body(transactions);

    }
    @PostMapping("/transaction/depositar")
    public Transaction createDepositTransaction(@Validated @RequestBody Transaction transaction, Authentication authentication) {
        transaction.setTipo(TransactionType.DEPOSITO);

        String user = authentication.getName();
        Account account = userRepository.findByUsername(user).getConta();

        account.setSaldo(account.getSaldo() + transaction.getValor());
        accountRepository.save(account);
        transaction.setDataTransacao(new Date());
        transaction.setUsuario(account.getUsuario());
        return repository.save(transaction);
    }

    @PostMapping("/transaction/transfer/{usuariodestinoId}")
    public Transaction createTransferTransaction(@PathVariable (value = "usuariodestinoId") Long usuariodestinoId, @Validated @RequestBody Transaction transaction, Authentication authentication) {
        transaction.setTipo(TransactionType.TRANSFERENCIA);

        String user = authentication.getName();

        Account account = userRepository.findByUsername(user).getConta();

        Account contaDestino = userRepository.findById(usuariodestinoId).get().getConta();

        if(account.getSaldo() < transaction.getValor()){
            throw new RuntimeException("Não tem grana pra isso !");
        }

        account.setSaldo(account.getSaldo() - transaction.getValor());
        contaDestino.setSaldo(contaDestino.getSaldo() + transaction.getValor());

        accountRepository.save(account);
        accountRepository.save(contaDestino);
        transaction.setDataTransacao(new Date());
        transaction.setUsuario(account.getUsuario());
        return repository.save(transaction);
    }

    @PostMapping("/transaction/withdraw")
    public Transaction createWithdrawTransaction(@Validated @RequestBody Transaction transaction, Authentication authentication) {
        transaction.setTipo(TransactionType.SAQUE);

        String user = authentication.getName();
        Account account = userRepository.findByUsername(user).getConta();

        if(account.getSaldo() < transaction.getValor()){
            throw new RuntimeException("Não tem grana pra isso !");
        }

        account.setSaldo(account.getSaldo() - transaction.getValor());
        accountRepository.save(account);

        transaction.setDataTransacao(new Date());
        transaction.setUsuario(account.getUsuario());
        return repository.save(transaction);
    }


}
