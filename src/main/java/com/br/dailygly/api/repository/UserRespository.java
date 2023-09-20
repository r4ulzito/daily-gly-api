package com.br.dailygly.api.repository;

import com.br.dailygly.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM tb_users WHERE uid_google = :uidGoogle", nativeQuery = true)
    User findUserByUidGoogle(@Param("uidGoogle") String uidGoogle);

}
