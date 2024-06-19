package com.balgoorm.balgoorm_backend.quiz.repository;

import com.balgoorm.balgoorm_backend.quiz.model.entity.SolvedQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SolvedQuizRepository extends JpaRepository<SolvedQuiz, Long> {

    Optional<SolvedQuiz> findByQuizIdAndUserId(Long quizId, Long userId);
}
