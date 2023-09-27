package com.br.dailygly.api.controller;

import com.br.dailygly.api.dto.register.SetRegisterDTO;
import com.br.dailygly.api.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registers")
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity createDayRegister(@Valid @RequestBody SetRegisterDTO data) {

        this.registerService.setRegister(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);

    }

    @GetMapping
    public ResponseEntity getUserMonthRegisters(
            @RequestParam int year,
            @RequestParam int month,
            @RequestHeader(name = "userId", required = true) Long userId) {

        return ResponseEntity.status(HttpStatus.OK).body(this.registerService.getUserMonthDaysRegisters(year, month, userId));

    }
}
