package com.balgoorm.balgoorm_backend.quiz.repository;

import com.balgoorm.balgoorm_backend.quiz.model.entity.Quiz;
import com.balgoorm.balgoorm_backend.quiz.model.entity.UserRecQuiz;
import com.balgoorm.balgoorm_backend.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRecQuizRepository extends JpaRepository<UserRecQuiz, Long> {

    Optional<UserRecQuiz> findByUserAndQuiz(User user, Quiz quiz);
}
