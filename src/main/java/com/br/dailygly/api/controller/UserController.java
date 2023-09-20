package com.br.dailygly.api.controller;

import com.br.dailygly.api.dto.userDto.UserLoginDTO;
import com.br.dailygly.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity userLogin(@Valid @RequestBody UserLoginDTO data) {

        return ResponseEntity.status(HttpStatus.OK).body(this.userService.userLogin(data.userToken()));

    }

}
