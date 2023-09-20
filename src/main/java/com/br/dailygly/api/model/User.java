package com.br.dailygly.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uid_google;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Builder.Default
    private Integer target_gly = 200;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_user_register", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "register_id", referencedColumnName = "id")})
    private List<MonthRegister> registers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUid_google(), user.getUid_google()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getTarget_gly(), user.getTarget_gly()) &&
                Objects.equals(getRegisters(), user.getRegisters());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUid_google(), getName(), getTarget_gly(), getRegisters());
    }
}
