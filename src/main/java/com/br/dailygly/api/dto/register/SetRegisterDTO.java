package com.br.dailygly.api.dto.register;

import com.br.dailygly.api.enumerator.RegisterPeriod;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record SetRegisterDTO(

        @NotNull(message = "The user id is required")
        @Min(value = 1, message = "Invalid user id")
        Long user_id,

        @PastOrPresent(message = "The date must be before or equal to today")
        @NotNull
        LocalDate date,

        @NotNull(message = "Period is required")
        RegisterPeriod period,

        @Min(value = 0, message = "Value must be greater or equal than 0")
        @Max(value = 600, message = "Value must be less than 600")
        Integer gly_value,

        @Size(max = 23)
        String observation

) {
}
