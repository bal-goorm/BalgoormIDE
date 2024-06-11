package com.balgoorm.balgoorm_backend.quiz.service;

import com.balgoorm.balgoorm_backend.quiz.model.dto.response.ResponseQuizList;
import com.balgoorm.balgoorm_backend.quiz.model.entity.Quiz;
import com.balgoorm.balgoorm_backend.quiz.model.enums.QuizSortType;
import com.balgoorm.balgoorm_backend.quiz.repository.CustomQuizRepository;
import com.balgoorm.balgoorm_backend.quiz.repository.CustomQuizRepositoryImpl;
import com.balgoorm.balgoorm_backend.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{

    private final QuizRepository quizRepository;
    private final CustomQuizRepository customQuizRepository;

    @Value("${quiz.list.size}")
    private int pageSize;

    @Override
    public List<ResponseQuizList> getQuizList(QuizSortType sortType, List<Integer> levels, int page) {

        List<Quiz> quizList = customQuizRepository.getQuizListSorted(PageRequest.of(page, pageSize), levels, sortType);

        List<ResponseQuizList> responseQuizList = quizList.stream().map(quiz ->
            new ResponseQuizList().builder()
                    .quizId(quiz.getQuizId())
                    .quizTitle(quiz.getQuizTitle())
                    .quiz_level(quiz.getQuizLevel())
                    .quiz_rec_cnt(quiz.getQuizRecCnt())
                    .correct_rate(quiz.getSubmitCnt() == 0 ? 0 : quiz.getCorrectCnt() * 100 / quiz.getSubmitCnt())
                    .build()).collect(Collectors.toList());


        return responseQuizList;
    }
}
