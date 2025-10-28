package com.quiz_app.question_resource.service;

import com.quiz_app.question_resource.data.OptionRepository;
import com.quiz_app.question_resource.data.Options;
import com.quiz_app.question_resource.data.Question;
import com.quiz_app.question_resource.data.QuestionRepository;
import com.quiz_app.question_resource.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class QuestionService {
    @Autowired
    OptionRepository optionRepository;

    @Autowired
    QuestionRepository questionRepository;

    public Question createQuestion(Question question){
        if(question.getQuestion() == null || question.getQuestion().trim().isEmpty()){
            throw new BadRequestException("Question cannot be empty");
        }

        if(question.getOptions() == null || question.getOptions().isEmpty()){
            throw new BadRequestException("At least one option is required");
        }

        if(!StringUtils.hasText(question.getQuestion())){
            throw new BadRequestException("Answer cannot be empty");
        }

        if(question.getMarks() <= 0){
            throw new BadRequestException("Marks cannot be empty");
        }

        for(Options option:question.getOptions()){
            option.setQuestion(question);
        }

        return questionRepository.save(question);
    }
}
