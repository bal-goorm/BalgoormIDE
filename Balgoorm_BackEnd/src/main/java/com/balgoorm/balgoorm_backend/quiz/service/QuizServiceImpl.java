package com.balgoorm.balgoorm_backend.quiz.service;

import com.balgoorm.balgoorm_backend.quiz.model.dto.response.ResponseQuizList;
import com.balgoorm.balgoorm_backend.quiz.model.entity.Quiz;
import com.balgoorm.balgoorm_backend.quiz.model.enums.QuizSortType;
import com.balgoorm.balgoorm_backend.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{

    private final QuizRepository quizRepository;

    @Value("${quiz.list.size}")
    private int pageSize;

    @Override
    public Page<ResponseQuizList> getQuizList(QuizSortType sortType, int page) {

        Page<Quiz> quizList = null;

        switch(sortType){

            case DEFAULT :
                quizList = quizRepository.findAll(PageRequest.of(page, pageSize));
                break;
            case DATE_ASC:
                quizList = quizRepository.findAll(PageRequest.of(page, pageSize, Sort.by("quizRegDate").ascending()));
                break;
            case DATE_DESC:
                quizList = quizRepository.findAll(PageRequest.of(page, pageSize, Sort.by("quizRegDate").descending()));
                break;
            case REC_ASC:
                quizList = quizRepository.findAll(PageRequest.of(page, pageSize, Sort.by("quizRecCnt").ascending()));
                break;
            case REC_DESC:
                quizList = quizRepository.findAll(PageRequest.of(page, pageSize, Sort.by("quizRecCnt").descending()));
                break;
            case CORRECT_RATE_ASC:
                quizList = quizRepository.findAllOrderByCorrectRateAsc(PageRequest.of(page,pageSize));
                break;
            case CORRECT_RATE_DESC:
                quizList = quizRepository.findAllOrderByCorrectRateDesc(PageRequest.of(page,pageSize));
                break;
        }

        Page<ResponseQuizList> responseQuizListPage = quizList.map(
                quiz -> {
                    return new ResponseQuizList().builder()
                            .quizId(quiz.getQuizId())
                            .quizTitle(quiz.getQuizTitle())
                            .quiz_level(quiz.getQuizLevel())
                            .quiz_rec_cnt(quiz.getQuizRecCnt())
                            .correct_rate(quiz.getSubmitCnt() == 0 ? 0 : quiz.getCorrectCnt() * 100 / quiz.getSubmitCnt())
                            .build();
                }
        );

        return responseQuizListPage;
    }
}
