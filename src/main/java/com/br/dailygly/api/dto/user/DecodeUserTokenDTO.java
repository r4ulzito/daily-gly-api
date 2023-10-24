package com.br.dailygly.api.dto.user;

public record DecodeUserTokenDTO(

        String name,
        String uid_google
) {

    public DecodeUserTokenDTO(String name, String uid_google) {
        this.name = name;
        this.uid_google = uid_google;
    }
}
