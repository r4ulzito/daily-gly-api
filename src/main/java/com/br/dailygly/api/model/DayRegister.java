package com.br.dailygly.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DayRegister {

    private Long id;
    private MonthRegister month;
    private Integer day;
    private Integer before_breakfast;
    private Integer after_breakfast;
    private Integer before_lunch;
    private Integer after_lunch;
    private Integer before_dinner;
    private Integer after_dinner;
    private Integer before_sleep;
    private String observation;

}
