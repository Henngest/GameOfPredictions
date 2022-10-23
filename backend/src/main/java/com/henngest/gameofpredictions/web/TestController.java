package com.henngest.gameofpredictions.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henngest.gameofpredictions.model.User;
import com.henngest.gameofpredictions.repository.UserRepository;

@RestController
public class TestController {
    
    @Autowired
    private UserRepository repository;

    @GetMapping("/user")
    public User user() {
        return repository.findByUsername("admin@foo.com");
    }

    @GetMapping("/forbidden")
    public User userForbidden() {
        return repository.findByUsername("test@foo.com");
    }

}
