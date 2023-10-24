package com.br.dailygly.api.dto.register;

import java.util.List;

public record GetMonthDayRegistersDTO(

        Integer year,
        Integer month,
        List<GetDayRegisterDTO> registers

) {

    public GetMonthDayRegistersDTO(Integer year, Integer month, List<GetDayRegisterDTO> registers) {
        this.year = year;
        this.month = month;
        this.registers = registers;
    }
}
