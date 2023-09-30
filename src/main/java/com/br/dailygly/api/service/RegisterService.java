package com.br.dailygly.api.service;

import com.br.dailygly.api.dto.register.GetMonthDayRegistersDTO;
import com.br.dailygly.api.dto.register.SetRegisterDTO;

public interface RegisterService {

    void setRegister(SetRegisterDTO RegisterData);

    GetMonthDayRegistersDTO getUserMonthDaysRegisters(int targetYear, int targetMonth, Long userId);

}
