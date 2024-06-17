package com.balgoorm.balgoorm_backend.user.repository;

import com.balgoorm.balgoorm_backend.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);


    // 중복 가입 방지를 위한 존재 여부 확인
    Boolean existsByUserId(String userId);
    Boolean existsByNickname(String nickname);
    Boolean existsByEmail(String email);
}