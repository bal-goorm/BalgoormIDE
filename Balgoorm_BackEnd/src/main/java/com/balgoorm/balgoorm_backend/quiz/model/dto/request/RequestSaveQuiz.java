package com.balgoorm.balgoorm_backend.quiz.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//TODO : Validation 추가
public class RequestSaveQuiz {

    Long userId;

    String quizTitle;

    String quizContent;

    int quizPoint;

    int quizLevel;

    String quizAnswer;

}
