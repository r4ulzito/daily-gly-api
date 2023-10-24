package com.br.dailygly.api.model;

import com.br.dailygly.api.dto.register.GetDayRegisterDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_month_registers")
public class MonthRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_month_register_day_register", joinColumns = {
            @JoinColumn(name = "month_register_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "day_register_id", referencedColumnName = "id")
    }
    )
    private List<DayRegister> days;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthRegister that = (MonthRegister) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUser_id(), that.getUser_id()) &&
                Objects.equals(getYear(), that.getYear()) &&
                Objects.equals(getMonth(), that.getMonth()) &&
                Objects.equals(getDays(), that.getDays());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser_id(), getYear(), getMonth(), getDays());
    }

    public void addDayRegister(DayRegister newDayRegister) {
        this.days.add(newDayRegister);
    }

    public DayRegister findDayRegister(int targetDay) {

        return this.days.stream().filter(rd ->
                        rd.getDay().equals(targetDay))
                .findAny().orElseThrow(() -> new RuntimeException("Failed to find day register"));

    }

    public List<GetDayRegisterDTO> findAllDayRegistersByMonth(int targetMonth) {
        return this.days.stream()
                .filter(dayRegister -> dayRegister.getMonth().getMonth() == targetMonth)
                .toList().stream().map(GetDayRegisterDTO::new).sorted(Comparator.comparing(GetDayRegisterDTO::day)
                ).collect(Collectors.toList());
    }

}
