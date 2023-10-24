package com.br.dailygly.api.service;

import com.br.dailygly.api.dto.user.GetUserDTO;

public interface UserService {

    GetUserDTO userLogin(String userJwtToken);

}
