package com.guilherme.vestidobank.vestidobank.service;

import com.guilherme.vestidobank.vestidobank.model.Account;
import com.guilherme.vestidobank.vestidobank.model.User;
import com.guilherme.vestidobank.vestidobank.repository.AccountRepository;
import com.guilherme.vestidobank.vestidobank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
//        User user = repository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Usuario nao encontrado!");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getSenha(),new ArrayList<>());
    }

    public User save(User user){
        Account account = new Account();
        account.setSaldo(0.00);
        accountRepository.save(account);

        User novo = new User();
        novo.setNome(user.getNome());
        novo.setSobrenome(user.getSobrenome());
        novo.setEmail(user.getEmail());
        novo.setUsername(user.getUsername());
        novo.setSenha(bcryptEncoder.encode(user.getSenha()));
        novo.setConta(user.getConta());

        return repository.save(novo);
    }
}
