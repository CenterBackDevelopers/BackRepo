package com.cbd.cbdtodo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserId(String userId);

    boolean existsByUserPassword(String userPassword);

    boolean existsByUserIdAndUserPassword(String userId, String userPassword);

    // 이 메소드는 해당 userId를 가진 유저의 다른 모든 정보가 담겨져 있는 User 객체를 반환한다. 로직이 변경되어 주석처리 해 두었다.
//    Optional<User> findUserPasswordByUserId(String userId);

    Optional<User> findByUserIdAndUserPassword(String userId, String userPassword);
}
