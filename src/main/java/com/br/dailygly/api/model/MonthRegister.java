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
public class MonthRegister {

    private Long id;
    private User user_id;
    private String year;
    private Integer month;
    private List<DayRegister> days;

}
