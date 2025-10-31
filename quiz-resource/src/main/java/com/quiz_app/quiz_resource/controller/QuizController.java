package com.quiz_app.quiz_resource.controller;

import com.quiz_app.quiz_resource.data.Quiz;
import com.quiz_app.quiz_resource.data.QuizRequestDto;
import com.quiz_app.quiz_resource.data.QuizResponseDto;
import com.quiz_app.quiz_resource.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {
    @Autowired
    private QuizService service;

    @PostMapping("/quizzes")
    public ResponseEntity<QuizResponseDto> createQuiz(@RequestBody QuizRequestDto dto){
        Quiz quiz = service.mapToQuizModel(dto);
        Quiz createdQuiz = service.createQuiz(quiz);
        return new ResponseEntity<>(service.mapToQuizDto(createdQuiz), HttpStatus.CREATED);
    }
}
