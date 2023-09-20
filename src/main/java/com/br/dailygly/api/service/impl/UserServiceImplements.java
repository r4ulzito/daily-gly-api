package com.br.dailygly.api.service.impl;

import com.br.dailygly.api.dto.userDto.DecodeUserTokenDTO;
import com.br.dailygly.api.dto.userDto.GetUserDTO;
import com.br.dailygly.api.exception.InvalidTokenException;
import com.br.dailygly.api.model.User;
import com.br.dailygly.api.repository.UserRespository;
import com.br.dailygly.api.service.UserService;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserServiceImplements implements UserService {

    private final UserRespository userRespository;

    @Autowired
    public UserServiceImplements(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    private DecodeUserTokenDTO decodeUserToken(String userJwtToken) {

        try {
            Base64.Decoder jwtDecoder = Base64.getUrlDecoder();

            String payload = new String(jwtDecoder.decode(userJwtToken));

            System.out.println(payload);

            String name = JsonPath.read(payload, "$.name");
            String uid_google = JsonPath.read(payload, "$.user_id");

            return new DecodeUserTokenDTO(name, uid_google);
        } catch (Exception error) {
            throw new InvalidTokenException("Invalid JWT token");
        }

    }

    @Override
    public GetUserDTO userLogin(String userJwtToken) {

        DecodeUserTokenDTO decodeUser = this.decodeUserToken(userJwtToken);

        User targetUser = this.userRespository.findUserByUidGoogle(decodeUser.uid_google());

        if (targetUser == null) {

            User createdNewUser = User.builder()
                    .name(decodeUser.name())
                    .uid_google(decodeUser.uid_google())
                    .build();

            return new GetUserDTO(this.userRespository.save(createdNewUser));
        }

        return new GetUserDTO(targetUser);
    }
}
