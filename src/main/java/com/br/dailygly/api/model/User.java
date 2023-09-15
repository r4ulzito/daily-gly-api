package com.br.dailygly.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private String uid_google;
    private String name;
    private Integer target_gly;
    private List<MonthRegister> registers;

}
