package com.br.dailygly.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_day_registers")
public class DayRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "month_register_id")
    private MonthRegister month;

    @Column(nullable = false)
    private Integer day;

    @Column
    @Builder.Default()
    private Integer before_breakfast = 0;

    @Column
    @Builder.Default()
    private Integer after_breakfast = 0;

    @Column
    @Builder.Default()
    private Integer before_lunch = 0;

    @Column
    @Builder.Default()
    private Integer after_lunch = 0;

    @Column
    @Builder.Default()
    private Integer before_dinner = 0;

    @Column
    @Builder.Default()
    private Integer after_dinner = 0;

    @Column
    @Builder.Default()
    private Integer before_sleep = 0;

    @Column
    @Builder.Default()
    private String observation = "";

    public void setMonth(MonthRegister month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayRegister that = (DayRegister) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getMonth(), that.getMonth()) &&
                Objects.equals(getDay(), that.getDay()) &&
                Objects.equals(getBefore_breakfast(), that.getBefore_breakfast()) &&
                Objects.equals(getAfter_breakfast(), that.getAfter_breakfast()) &&
                Objects.equals(getBefore_lunch(), that.getBefore_lunch()) &&
                Objects.equals(getAfter_lunch(), that.getAfter_lunch()) &&
                Objects.equals(getBefore_dinner(), that.getBefore_dinner()) &&
                Objects.equals(getAfter_dinner(), that.getAfter_dinner()) &&
                Objects.equals(getBefore_sleep(), that.getBefore_sleep()) &&
                Objects.equals(getObservation(), that.getObservation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getMonth(),
                getDay(),
                getBefore_breakfast(),
                getAfter_breakfast(),
                getBefore_lunch(),
                getAfter_lunch(),
                getBefore_dinner(),
                getAfter_dinner(),
                getBefore_sleep(),
                getObservation());
    }

    public void setPeriodValue(String period, Integer value) {

        if (value == null) {
            throw new RuntimeException("Period value cannot be null");
        }

        if (period.equals("OBSERVACAO")) {
            throw new RuntimeException("The selected period cannot be a number");
        } else {
            switch (period) {
                case "PRE_CAFE" -> this.before_breakfast = value;
                case "POS_CAFE" -> this.after_breakfast = value;
                case "PRE_ALMOCO" -> this.before_lunch = value;
                case "POS_ALMOCO" -> this.after_lunch = value;
                case "PRE_JANTAR" -> this.before_dinner = value;
                case "POS_JANTAR" -> this.after_dinner = value;
                case "ANTES_DORMIR" -> this.before_sleep = value;
            }
        }

    }

    public void setPeriodValue(String period, String value) {

        if (value == null || value.equals("")) {
            throw new RuntimeException("Observation value cannot be empty");
        }

        if (period.equals("OBSERVACAO")) {
            this.observation = value;
        } else {
            throw new RuntimeException("The selected period cannot be a string");
        }
    }

}
