package com.balgoorm.balgoorm_backend.quiz.repository;

import com.balgoorm.balgoorm_backend.quiz.model.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
