package com.br.dailygly.api.controller;

import com.br.dailygly.api.model.User;
import com.br.dailygly.api.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity createUser() {
        return ResponseEntity.ok().build();
    }
}
