package com.balgoorm.balgoorm_backend.quiz.repository;

import com.balgoorm.balgoorm_backend.quiz.model.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query("SELECT q FROM Quiz q ORDER BY " +
            "CASE WHEN q.submitCnt = 0 THEN 0 ELSE (q.correctCnt *100 / q.submitCnt) END ASC")
    Page<Quiz> findAllOrderByCorrectRateAsc(Pageable pageable);

    @Query("SELECT q FROM Quiz q ORDER BY " +
            "CASE WHEN q.submitCnt = 0 THEN 0 ELSE (q.correctCnt *100 / q.submitCnt) END DESC")
    Page<Quiz> findAllOrderByCorrectRateDesc(Pageable pageable);
}
