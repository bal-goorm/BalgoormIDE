package com.balgoorm.balgoorm_backend.quiz.service;

import com.balgoorm.balgoorm_backend.quiz.model.dto.response.ResponseQuizList;
import com.balgoorm.balgoorm_backend.quiz.model.enums.QuizSortType;
import org.springframework.data.domain.Page;


public interface QuizService {

    public Page<ResponseQuizList> getQuizList(QuizSortType sortType, int page);

}
