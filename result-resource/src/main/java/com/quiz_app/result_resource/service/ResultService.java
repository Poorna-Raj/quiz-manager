package com.quiz_app.result_resource.service;

import com.quiz_app.result_resource.data.*;
import com.quiz_app.result_resource.exception.ContentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {
    @Autowired
    private ResultRepository repository;
    @Autowired
    private ResultServiceClient serviceClient;

    public Result populateCorrectAnswers(Result result){
        List<ResultQuestion> fetchedQuestionList = result.getAnswers().stream()
                        .map(serviceClient::getQuestionResults)
                                .toList();

        result.setAnswers(fetchedQuestionList);
        return result;
    }

    public Result calculateTheScore (Result result){
        double score = 0;
        for(ResultQuestion question: result.getAnswers()){
            if(question.getAnswer().equalsIgnoreCase(question.getCorrectAnswer())){
                score ++;
            }
        }

        result.setScore(score);
        return result;
    }

    public Result saveResult(Result result){
        return repository.save(calculateTheScore(populateCorrectAnswers(result)));
    }

    public boolean deleteResultById(long id){
        Result result = repository.findById(id)
                .orElseThrow(()->new ContentNotFound("Invalid result for given ID of " + id));
        repository.delete(result);
        return true;
    }

    public Result getResultById(long id){
        return repository.findById(id)
                .orElseThrow(()->new ContentNotFound("Invalid result for given ID of " + id));
    }

    public List<Result> getResults(){
        return repository.findAll();
    }

    public ResultQuestion mapToResultQuestionModel(ResultQuestionRequestDto dto){
        ResultQuestion question = new ResultQuestion();
        question.setQuestionId(dto.getQuestionId());
        question.setAnswer(dto.getAnswer());
        return question;
    }

    public Result mapToResultModel(ResultRequestDto dto){
        Result result = new Result();
        result.setQuizId(dto.getQuizId());

        List<ResultQuestion> resultQuestionList = dto.getAnswers()
                .stream()
                .map(this::mapToResultQuestionModel)
                .toList();

        result.setAnswers(resultQuestionList);
        return result;
    }

    public ResultQuestionResponseDto mapToResultQuestionResponseDto(ResultQuestion question){
        ResultQuestionResponseDto dto = new ResultQuestionResponseDto();
        dto.setId(question.getId());
        dto.setAnswer(question.getAnswer());
        dto.setQuestionId(question.getQuestionId());
        dto.setCorrectAnswer(question.getCorrectAnswer());

        return dto;
    }

    public ResultResponseDto mapToResultResponseDto(Result result){
        ResultResponseDto dto = new ResultResponseDto();
        dto.setId(result.getId());
        dto.setScore(result.getScore());
        dto.setSubmittedAt(result.getSubmittedAt());
        dto.setQuizId(result.getQuizId());

        List<ResultQuestionResponseDto> responseDtos = result.getAnswers().stream()
                .map(this::mapToResultQuestionResponseDto)
                .toList();

        dto.setAnswers(responseDtos);

        return dto;
    }
}
