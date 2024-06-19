package com.balgoorm.balgoorm_backend.quiz.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolvedQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long solvedQuizId;

    private Long quizId;

    private Long userId;

    @Column(nullable = false)
    private LocalDateTime solvedAt = LocalDateTime.now();
}
