package com.guilherme.vestidobank.vestidobank.repository;

import com.guilherme.vestidobank.vestidobank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "Select * from usuario where email = :email", nativeQuery = true)
    User findByEmail(String email);
    User findByUsername(String username);
}
