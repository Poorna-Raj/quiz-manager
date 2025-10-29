package com.quiz_app.question_resource.controller;

import com.quiz_app.question_resource.data.QuestionList;
import com.quiz_app.question_resource.data.QuestionListRequestDto;
import com.quiz_app.question_resource.data.QuestionListResponseDto;
import com.quiz_app.question_resource.service.QuestionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question-lists")
public class QuestionListController {
    @Autowired
    private QuestionListService service;

    @PostMapping
    public ResponseEntity<QuestionListResponseDto> createQuestionList(@RequestBody QuestionListRequestDto list){
        QuestionList newList = service.createQuestionList(service.mapToModel(list));
        return new ResponseEntity<>(service.mapToDto(newList), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<QuestionListResponseDto>> getAllQuestionLists(){
        List<QuestionList> list = service.getAllQuestions();
        List<QuestionListResponseDto> responseDto = new ArrayList<>();

        for(QuestionList listItem:list){
            responseDto.add(service.mapToDto(listItem));
        }

        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
