package com.quiz_app.result_resource.service;

import com.quiz_app.result_resource.data.ResultQuestion;
import com.quiz_app.result_resource.exception.BadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResultServiceClient {
    @Autowired
    private RestTemplate restTemplate;

    public ResultQuestion getQuestionResults(ResultQuestion requestedResult){
        if(requestedResult.getQuestionId() <= 0){
            throw new BadRequest("Invalid question ID of " + requestedResult.getQuestionId());
        }
        String url = "http://localhost:8081/question-resource/internal/questions/"+requestedResult.getQuestionId()+"/correct-answer";
        String answer = restTemplate.getForObject(url,String.class);
        requestedResult.setAnswer(answer);

        return requestedResult;
    }
}
