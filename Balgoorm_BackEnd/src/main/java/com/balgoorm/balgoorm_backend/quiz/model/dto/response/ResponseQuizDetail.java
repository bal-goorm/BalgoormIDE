package com.balgoorm.balgoorm_backend.quiz.model.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseQuizDetail {

    Long quizId;
    String quizTitle;
    String quizContent;
    int quizPoint;
    LocalDateTime quizRegDate;
    int quizLevel;
    int quizRecCnt;
    boolean isRec;

}
