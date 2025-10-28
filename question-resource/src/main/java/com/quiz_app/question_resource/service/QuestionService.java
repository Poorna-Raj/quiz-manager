package com.quiz_app.question_resource.service;

import com.quiz_app.question_resource.data.*;
import com.quiz_app.question_resource.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

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

        for(Option option:question.getOptions()){
            option.setQuestion(question);
        }

        return questionRepository.save(question);
    }

    public Question mapToQuestion(QuestionRequestDto dto){
        Question question = new Question();
        question.setQuestion(dto.getQuestion());
        question.setAnswer(dto.getAnswer());
        question.setMarks(dto.getMarks());
        List<Option> options = dto.getOptions().stream()
                .map(optText -> {
                    Option option = new Option();
                    option.setOption_text(optText);
                    option.setQuestion(question);
                    return option;
                })
                .toList();
        question.setOptions(options);

        return question;
    }

    public QuestionResponseDto mapToDto(Question question){
        QuestionResponseDto dto = new QuestionResponseDto();
        dto.setQuestion(question.getQuestion());
        dto.setMarks(question.getMarks());

        List<String> optionTexts = question.getOptions().stream()
                .map(Option::getOption_text)
                .collect(Collectors.toList());

        dto.setOptions(optionTexts);

        return dto;
    }
}
