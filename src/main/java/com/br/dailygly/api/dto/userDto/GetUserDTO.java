package com.br.dailygly.api.dto.userDto;

import com.br.dailygly.api.model.User;

public record GetUserDTO(
        Long id,
        String uid_google,
        String name,
        Integer target_gly

) {
    public GetUserDTO(Long id, String uid_google, String name, Integer target_gly) {
        this.id = id;
        this.uid_google = uid_google;
        this.name = name;
        this.target_gly = target_gly;
    }

    public GetUserDTO(User user) {
        this(user.getId(), user.getUid_google(), user.getName(), user.getTarget_gly());
    }
}
