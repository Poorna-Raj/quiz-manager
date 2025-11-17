package com.quiz_app.quiz_resource.service;

import com.quiz_app.quiz_resource.data.QuestionListDto;
import com.quiz_app.quiz_resource.exception.QuestionServiceUnavailable;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuizServiceClient {
    @Autowired
    private RestTemplate restTemplate;

    private static final String questionService = "questionService";

    @CircuitBreaker(name = questionService, fallbackMethod = "getQuestionListFallback")
    public QuestionListDto getQuestionListById(long questionListId){
        String url = "http://localhost:8081/question-resource/question-lists/" + questionListId;
        return restTemplate.getForObject(url,QuestionListDto.class);
    }

    public QuestionListDto getQuestionListFallback(long questionListId, Exception ex){
        throw new QuestionServiceUnavailable("Question service is unavailable : " + ex.getMessage() , ex);
    }
}
