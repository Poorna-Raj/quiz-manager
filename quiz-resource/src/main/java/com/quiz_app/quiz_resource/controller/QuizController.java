package com.quiz_app.quiz_resource.controller;

import com.quiz_app.quiz_resource.data.Quiz;
import com.quiz_app.quiz_resource.data.QuizRequestDto;
import com.quiz_app.quiz_resource.data.QuizResponseDto;
import com.quiz_app.quiz_resource.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/quizzes/{id}")
    public ResponseEntity<QuizResponseDto> updateQuizById(@RequestBody QuizRequestDto dto, @PathVariable long id){
        Quiz quiz = service.mapToQuizModel(dto);
        Quiz updatedQuiz = service.updateQuiz(id,quiz);
        return new ResponseEntity<>(service.mapToQuizDto(updatedQuiz),HttpStatus.OK);
    }

    @DeleteMapping("quizzes/{id}")
    public ResponseEntity<HttpStatus> deleteQuizById(@PathVariable long id){
        if(service.deleteQuiz(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("quizzes/{id}")
    public ResponseEntity<QuizResponseDto> getQuizById(@PathVariable long id){
        return new ResponseEntity<>(service.mapToQuizDto(service.getQuizById(id)),HttpStatus.OK);
    }

    @GetMapping("quizzes/")
    public ResponseEntity<List<QuizResponseDto>> getAllQuizzes(){
        List<QuizResponseDto> quizList = service.getAllQuiz()
                .stream()
                .map(quiz -> {
                    return service.mapToQuizDto(quiz);
                })
                .toList();
        return new ResponseEntity<>(quizList,HttpStatus.OK);
    }
}
