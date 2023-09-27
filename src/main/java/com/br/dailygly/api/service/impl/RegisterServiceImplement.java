package com.br.dailygly.api.service.impl;

import com.br.dailygly.api.dto.register.GetDayRegisterDTO;
import com.br.dailygly.api.dto.register.GetMonthDayRegistersDTO;
import com.br.dailygly.api.dto.register.SetRegisterDTO;
import com.br.dailygly.api.enumerator.RegisterPeriod;
import com.br.dailygly.api.model.DayRegister;
import com.br.dailygly.api.model.MonthRegister;
import com.br.dailygly.api.model.User;
import com.br.dailygly.api.repository.MonthRegisterRepository;
import com.br.dailygly.api.repository.UserRespository;
import com.br.dailygly.api.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterServiceImplement implements RegisterService {

    private final MonthRegisterRepository monthRegisterRepository;
    private final UserRespository userRespository;

    @Autowired
    public RegisterServiceImplement(MonthRegisterRepository monthRegisterRepository, UserRespository userRespository) {
        this.monthRegisterRepository = monthRegisterRepository;
        this.userRespository = userRespository;
    }

    private User findTargetUser(Long userId) {

        return this.userRespository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

    }

    private void setTargetPeriod(SetRegisterDTO registerData, DayRegister targetDay) {

        if (registerData.period().equals(RegisterPeriod.OBSERVACAO)) {
            targetDay.setPeriodValue(RegisterPeriod.OBSERVACAO.toString(), registerData.observation());
        } else {
            targetDay.setPeriodValue(registerData.period().toString(), registerData.gly_value());
        }

    }

    public GetMonthDayRegistersDTO getUserMonthDaysRegisters(int targetYear, int targetMonth, Long userId) {

        User targetUser = this.findTargetUser(userId);

        MonthRegister targetMonthRegister = targetUser.findMonthRegister(targetYear, targetMonth);

        if (targetMonthRegister == null) {
            return new GetMonthDayRegistersDTO(
                    targetYear,
                    targetMonth,
                    new ArrayList<>()
            );
        } else {

            List<GetDayRegisterDTO> daysRegisters = targetMonthRegister.findAllDayRegistersByMonth(targetMonth);

            return new GetMonthDayRegistersDTO(
                    targetYear,
                    targetMonth,
                    daysRegisters
            );
        }

    }

    public void setRegister(SetRegisterDTO registerData) {

        User targetUser = this.findTargetUser(registerData.user_id());

        MonthRegister targetMonthRegister = targetUser.findMonthRegister(registerData.date().getYear(), registerData.date().getMonth().getValue());

        if (targetMonthRegister == null) {

            MonthRegister newMonthRegister = MonthRegister.builder()
                    .user_id(targetUser)
                    .year(registerData.date().getYear())
                    .month(registerData.date().getMonth().getValue())
                    .days(new ArrayList<>())
                    .build();

            newMonthRegister.addDayRegister(DayRegister.builder()
                    .month(newMonthRegister)
                    .day(registerData.date().getDayOfMonth())
                    .build());

            DayRegister createdDay = newMonthRegister.findDayRegister(registerData.date().getDayOfMonth());

            this.setTargetPeriod(registerData, createdDay);

            targetUser.addMonthRegister(newMonthRegister);

            this.userRespository.save(targetUser);

        } else {

            DayRegister monthTargetDay;

            try {
                monthTargetDay = targetMonthRegister.findDayRegister(registerData.date().getDayOfMonth());
            } catch (Exception error) {
                monthTargetDay = null;
            }

            if (monthTargetDay == null) {
                targetMonthRegister.addDayRegister(DayRegister.builder()
                        .month(targetMonthRegister)
                        .day(registerData.date().getDayOfMonth())
                        .build()
                );

                DayRegister createdDayRegister = targetMonthRegister.findDayRegister(registerData.date().getDayOfMonth());

                this.setTargetPeriod(registerData, createdDayRegister);

                this.userRespository.save(targetUser);

            } else {

                this.setTargetPeriod(registerData, monthTargetDay);

                this.userRespository.save(targetUser);

            }

        }
    }

}

