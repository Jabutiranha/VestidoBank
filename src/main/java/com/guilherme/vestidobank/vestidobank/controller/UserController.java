package com.guilherme.vestidobank.vestidobank.controller;

import com.guilherme.vestidobank.vestidobank.model.User;
import com.guilherme.vestidobank.vestidobank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/usuarios")
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @PostMapping("/criar")
    public User createUser(@Validated @RequestBody User user){
        return repository.save(user);
    }
}
