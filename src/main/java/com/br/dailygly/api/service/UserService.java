package com.br.dailygly.api.service;

import com.br.dailygly.api.dto.userDto.GetUserDTO;

public interface UserService {

    GetUserDTO userLogin(String userJwtToken);

}
