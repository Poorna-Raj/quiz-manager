package com.quiz_app.question_resource.controller;

import com.quiz_app.question_resource.data.Question;
import com.quiz_app.question_resource.data.QuestionRequestDto;
import com.quiz_app.question_resource.data.QuestionResponseDto;
import com.quiz_app.question_resource.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Question question = service.updateQuestionById(id, service.mapToQuestion(dto));
        return new ResponseEntity<>(service.mapToDto(question),HttpStatus.OK);
    }

    @DeleteMapping(path = "/questions/{id}")
    public ResponseEntity<HttpStatus> deleteQuestionById(@PathVariable long id){
        if(service.deleteQuestionById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/questions/{id}/correct-answer")
    public ResponseEntity<String> getCorrectAnswerByQuestionId(@PathVariable long id) {
        return new ResponseEntity<>(service.getCorrectAnswerById(id),HttpStatus.OK);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<QuestionResponseDto>> getAllQuestionsPaged(Pageable pageable) {
        QuestionService questionService;
        Page<QuestionResponseDto> dtoPage = service.getAllQuestions(pageable);
        return ResponseEntity.ok(dtoPage);
    }
}
