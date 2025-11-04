package com.quiz_app.result_resource.service;

import com.quiz_app.result_resource.data.Result;
import com.quiz_app.result_resource.data.ResultQuestion;
import com.quiz_app.result_resource.data.ResultRepository;
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
}
