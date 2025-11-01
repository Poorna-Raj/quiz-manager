package com.quiz_app.quiz_resource.service;

import com.quiz_app.quiz_resource.data.QuestionListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuestionServiceClient {
    @Autowired
    private RestTemplate restTemplate;

    public QuestionListDto getQuestionListById(long questionListId){
        String url = "http://localhost:8081/question-resource/question-lists/" + questionListId;
        return restTemplate.getForObject(url,QuestionListDto.class);
    }
}
