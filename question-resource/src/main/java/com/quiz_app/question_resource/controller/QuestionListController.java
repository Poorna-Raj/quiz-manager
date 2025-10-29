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

    @GetMapping(path = "{id}")
    public ResponseEntity<QuestionListResponseDto> getQuestionListById(@PathVariable long id){
        QuestionList list = service.getQuestionListById(id);
        return new ResponseEntity<>(service.mapToDto(list),HttpStatus.OK);
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

    @PutMapping(path = "{id}")
    public ResponseEntity<QuestionListResponseDto> updateById(@PathVariable long id,@RequestBody QuestionListRequestDto dto){
        QuestionList newList = service.updateQuestionList(id, service.mapToModel(dto));
        return new ResponseEntity<>(service.mapToDto(newList),HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable long id){
        if(service.deleteById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(path ="{id}/questions")
    public ResponseEntity<QuestionListResponseDto> deleteQuestionOnAListById(@PathVariable long id){
        QuestionList updatedList = service.clearAllQuestionsById(id);
        return new ResponseEntity<>(service.mapToDto(updatedList),HttpStatus.OK);
    }
}
