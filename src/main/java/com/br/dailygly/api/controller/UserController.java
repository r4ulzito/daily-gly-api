package com.br.dailygly.api.controller;

import com.br.dailygly.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestHeader(name = "Authorization", required = true) String token) {

        return ResponseEntity.status(HttpStatus.OK).body(this.userService.userLogin(token));

    }

}
