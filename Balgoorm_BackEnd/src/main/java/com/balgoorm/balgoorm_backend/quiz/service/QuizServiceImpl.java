package com.balgoorm.balgoorm_backend.quiz.service;

import com.balgoorm.balgoorm_backend.quiz.model.dto.response.ResponseQuizDetail;
import com.balgoorm.balgoorm_backend.quiz.model.dto.response.ResponseQuizList;
import com.balgoorm.balgoorm_backend.quiz.model.entity.Quiz;
import com.balgoorm.balgoorm_backend.quiz.model.entity.UserRecQuiz;
import com.balgoorm.balgoorm_backend.quiz.model.enums.QuizSortType;
import com.balgoorm.balgoorm_backend.quiz.repository.CustomQuizRepository;
import com.balgoorm.balgoorm_backend.quiz.repository.CustomQuizRepositoryImpl;
import com.balgoorm.balgoorm_backend.quiz.repository.QuizRepository;
import com.balgoorm.balgoorm_backend.quiz.repository.UserRecQuizRepository;
import com.balgoorm.balgoorm_backend.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{

    private final QuizRepository quizRepository;
    private final CustomQuizRepository customQuizRepository;
    private final UserRecQuizRepository userRecQuizRepository;

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

    @Override
    public ResponseQuizDetail getQuizDetail(Long quizId, Long userId) {

        Optional<Quiz> findQuiz = quizRepository.findById(quizId);
        if (!findQuiz.isPresent()) {
            // TODO : Throw Exception ( can't find quiz )
        }

        // TODO : find User ( user repository 연동 후에 작업 )
        User user = null;

        Optional<UserRecQuiz> userRecQuiz = userRecQuizRepository.findByUserAndQuiz(user, findQuiz.get());

        ResponseQuizDetail quizDetail = ResponseQuizDetail.builder()
                .quizId(quizId)
                .quizTitle(findQuiz.get().getQuizTitle())
                .quizContent(findQuiz.get().getQuizContent())
                .quizPoint(findQuiz.get().getQuizPoint())
                .quizRegDate(findQuiz.get().getQuizRegDate())
                .quizLevel(findQuiz.get().getQuizLevel())
                .quizRecCnt(findQuiz.get().getQuizRecCnt())
                .isRec(userRecQuiz.isPresent()) // 추천 목록에 있다면 추천한 전적이 있다는 것
                .build();

        return quizDetail;
    }
}
