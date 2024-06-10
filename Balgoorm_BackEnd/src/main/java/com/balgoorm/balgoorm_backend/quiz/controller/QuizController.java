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
    public ResponseEntity getQuizList(@RequestParam QuizSortType sortType ,@PathVariable int page){

        Page<ResponseQuizList> quizList = quizService.getQuizList(sortType, page);

        return ResponseEntity.ok(quizList);
    }

    @GetMapping("/list/level/{page}")
    public ResponseEntity getQuizList(@RequestParam QuizSortType sortType, @RequestParam int level, @PathVariable int page){

        return ResponseEntity.ok("ok");
    }


}
