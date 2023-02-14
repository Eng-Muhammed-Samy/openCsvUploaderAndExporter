package com.export.exportfilecsv.repository;

import com.export.exportfilecsv.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    @Query("UPDATE UserEntity set isValid = FALSE where id = ?1")
    void updateIsValid(long id);

    @Query("select u.id from UserEntity u where u.name = ?1")
    Long getIdByNAme(String name);
}
