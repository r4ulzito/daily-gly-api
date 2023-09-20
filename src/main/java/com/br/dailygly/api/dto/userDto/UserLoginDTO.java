package com.br.dailygly.api.dto.userDto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(

        @NotBlank(message = "User token is required")
        String userToken

) {
}
