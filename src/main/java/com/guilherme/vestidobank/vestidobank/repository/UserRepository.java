package com.guilherme.vestidobank.vestidobank.repository;

import com.guilherme.vestidobank.vestidobank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
