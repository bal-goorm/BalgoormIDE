package com.balgoorm.balgoorm_backend.quiz.model.entity;

import com.balgoorm.balgoorm_backend.ide.model.enums.LanguageType;
import com.balgoorm.balgoorm_backend.user.model.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmitRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    private LocalDateTime submitDate;

    private String submitCode;

    private Boolean isSuccess;

    private String errorLog;

    private String executionTime;

    private String memoryUsage;

    @Enumerated(EnumType.STRING)
    private LanguageType languageType;

    @ManyToOne
    @JoinColumn(name = "QUIZ_ID")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
