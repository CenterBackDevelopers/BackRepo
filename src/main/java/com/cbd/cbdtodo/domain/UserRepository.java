package com.cbd.cbdtodo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserId(String userId);

    boolean existsByUserPassword(String userPassword);

    boolean existsByUserIdAndUserPassword(String userId, String userPassword);
}
