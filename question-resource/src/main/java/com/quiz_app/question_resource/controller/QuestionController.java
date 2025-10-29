package com.quiz_app.question_resource.controller;

import com.quiz_app.question_resource.data.Question;
import com.quiz_app.question_resource.data.QuestionRequestDto;
import com.quiz_app.question_resource.data.QuestionResponseDto;
import com.quiz_app.question_resource.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionController {
    @Autowired
    private QuestionService service;

    @PostMapping(path = "/questions")
    public ResponseEntity<QuestionResponseDto> createQuestion(@RequestBody QuestionRequestDto dto){
        Question question = service.mapToQuestion(dto);
        Question savedQuestion = service.createQuestion(question);
        return new ResponseEntity<>(service.mapToDto(savedQuestion), HttpStatus.CREATED);
    }

    @GetMapping(path = "/questions/{id}")
    public ResponseEntity<QuestionResponseDto> getQuestionById(@PathVariable long id){
        Question question = service.getQuestionById(id);
        return new ResponseEntity<>(service.mapToDto(question),HttpStatus.OK);
    }

    @PutMapping(path = "/questions/{id}")
    public ResponseEntity<QuestionResponseDto> updateQuestionById(@PathVariable long id,@RequestBody QuestionRequestDto dto){
        Question question = service.updateQuestion(id, service.mapToQuestion(dto));
        return new ResponseEntity<>(service.mapToDto(question),HttpStatus.OK);
    }
}
