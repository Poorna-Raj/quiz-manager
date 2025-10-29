package com.quiz_app.question_resource.service;

import com.quiz_app.question_resource.data.*;
import com.quiz_app.question_resource.exception.BadRequestException;
import com.quiz_app.question_resource.exception.QuestionNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionListService {
    @Autowired
    private QuestionListRepository repository;

    public QuestionList createQuestionList(QuestionList list){
        if(list.getName().isEmpty()||list.getTopic().isEmpty()){
            throw new BadRequestException("Invalid List. Please fill all the fields!");
        }

        return repository.save(list);
    }

    public QuestionList updateQuestionList(long id,QuestionList newList){
        Optional<QuestionList> existingList = repository.findById(id);

        if(existingList.isEmpty()){
            throw new QuestionNotFound("Invalid question list for given ID");
        }

        if(newList.getName().isEmpty()||newList.getTopic().isEmpty()){
            throw new BadRequestException("Invalid List. Please fill all the fields!");
        }

        existingList.get().setName(newList.getName());
        existingList.get().setTopic(newList.getTopic());

        if(newList.getQuestions() == null){
            existingList.get().setQuestions(new ArrayList<>());
        } else{
            existingList.get().setQuestions(newList.getQuestions());
        }

        return repository.save(existingList.get());
    }

    public List<QuestionList> getAllQuestions(){
        return repository.findAll();
    }

    public QuestionList mapToModel(QuestionListRequestDto list) {
        QuestionList newList = new QuestionList();
        newList.setName(list.getName());
        newList.setTopic(list.getTopic());

        return newList;
    }

    public QuestionListResponseDto mapToDto(QuestionList newList) {
        QuestionListResponseDto responseDto = new QuestionListResponseDto();
        responseDto.setId(newList.getId());
        responseDto.setName(newList.getName());
        responseDto.setTopic(newList.getTopic());

        if (responseDto.getQuestions_id() == null) {
            responseDto.setQuestions_id(new ArrayList<>());
        }

        if(newList.getQuestions() != null){
            for(Question question: newList.getQuestions()){
                responseDto.getQuestions_id().add(question.getId());
            }
        }

        return responseDto;
    }
}
