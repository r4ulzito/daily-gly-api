package com.br.dailygly.api.dto.register;

import com.br.dailygly.api.model.DayRegister;

public record GetDayRegisterDTO(

        Integer day,

        Integer before_breakfast,

        Integer after_breakfast,

        Integer before_lunch,

        Integer after_lunch,

        Integer before_dinner,

        Integer after_dinner,

        Integer before_sleep,

        String observation

) {

    public GetDayRegisterDTO(DayRegister dayRegister) {
        this(
                dayRegister.getDay(),
                dayRegister.getBefore_breakfast(),
                dayRegister.getAfter_breakfast(),
                dayRegister.getBefore_lunch(),
                dayRegister.getAfter_lunch(),
                dayRegister.getBefore_dinner(),
                dayRegister.getAfter_dinner(),
                dayRegister.getBefore_sleep(),
                dayRegister.getObservation()
        );
    }

}
