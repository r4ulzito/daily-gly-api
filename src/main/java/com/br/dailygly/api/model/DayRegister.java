package com.br.dailygly.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToOne
    @JoinColumn(name = "month_register_id")
    private MonthRegister month;

    @Column(nullable = false)
    private Integer day;

    @Column
    private Integer before_breakfast;

    @Column()
    private Integer after_breakfast;

    @Column()
    private Integer before_lunch;

    @Column()
    private Integer after_lunch;

    @Column()
    private Integer before_dinner;

    @Column()
    private Integer after_dinner;

    @Column()
    private Integer before_sleep;

    @Column()
    private String observation;

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
}
