package com.balgoorm.balgoorm_backend.quiz.model.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public enum QuizSortType {
    /**
     * DEFAULT : 초기
     * DATE_ASC , DATE_DESC : 날짜별 오름차순 내림차순
     * REC_ASC, REC_DESC : 추천수별 오름차순 내림차순
     * CORRECT_RATE_ASC , CORRECT_RATE_DESC : 정답률별 오름차순 내림차순
     */
    @Enumerated(EnumType.STRING)
    DEFAULT, DATE_ASC, DATE_DESC, REC_ASC, REC_DESC, CORRECT_RATE_ASC, CORRECT_RATE_DESC
}
