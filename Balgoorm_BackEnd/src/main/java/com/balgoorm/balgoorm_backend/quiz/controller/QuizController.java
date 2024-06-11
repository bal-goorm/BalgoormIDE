package com.balgoorm.balgoorm_backend.quiz.controller;

import com.balgoorm.balgoorm_backend.quiz.model.dto.response.ResponseQuizList;
import com.balgoorm.balgoorm_backend.quiz.model.enums.QuizSortType;
import com.balgoorm.balgoorm_backend.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/list/{page}")
    public ResponseEntity getQuizList(@RequestParam QuizSortType sortType ,@PathVariable int page, @RequestParam(defaultValue = "0") int level){

        /**
         * SpringDataJPA 에서 사용한 페이징 시스템의 시작 페이지는 0 부터 들어오는 변수는 1부터 들어오기 떄문에 -1 해서 서비스 메소드로 전달
         */

        System.out.println(level);
        Page<ResponseQuizList> quizList = quizService.getQuizList(sortType, page-1);

        return ResponseEntity.ok(quizList);
    }



}
