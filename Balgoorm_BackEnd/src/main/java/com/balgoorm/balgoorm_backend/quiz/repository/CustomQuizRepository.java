package com.balgoorm.balgoorm_backend.quiz.repository;


import com.balgoorm.balgoorm_backend.quiz.model.entity.Quiz;
import com.balgoorm.balgoorm_backend.quiz.model.enums.QuizSortType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomQuizRepository {

    List<Quiz> getQuizListSorted(Pageable pageable, List<Integer> levels, QuizSortType sortType);

}
