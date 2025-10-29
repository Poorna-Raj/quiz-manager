package com.quiz_app.question_resource.service;

import com.quiz_app.question_resource.data.*;
import com.quiz_app.question_resource.exception.BadRequestException;
import com.quiz_app.question_resource.exception.QuestionNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionListRepository listRepository;

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

        if(checkAnswerInTheOptions(question)){
            throw new BadRequestException("Answer of the question doesn't match to its options");
        }

        for(Option option:question.getOptions()){
            option.setQuestion(question);
        }

        return questionRepository.save(question);
    }

    public Question getQuestionById(long id){
        Optional<Question> question = questionRepository.findById(id);

        if(question.isEmpty()){
            throw new QuestionNotFound("Invalid question for the ID of " + id);
        }

        return question.get();
    }

    public Question updateQuestionById(long id, Question editedQuestion){
        Optional<Question> question = questionRepository.findById(id);

        if(question.isEmpty()){
            throw new QuestionNotFound("Invalid question for the ID of " + id);
        }

        if(!StringUtils.hasText(editedQuestion.getQuestion())){
            throw new BadRequestException("Question cannot be empty");
        }

        if(editedQuestion.getOptions() == null || editedQuestion.getOptions().isEmpty()){
            throw new BadRequestException("At least one option is required");
        }

        if(!StringUtils.hasText(editedQuestion.getAnswer())){
            throw new BadRequestException("Answer cannot be empty");
        }

        if(editedQuestion.getMarks() <= 0){
            throw new BadRequestException("Marks cannot be empty");
        }

        if(checkAnswerInTheOptions(editedQuestion)){
            throw new BadRequestException("Answer of the question doesn't match to its options");
        }

        question.get().setMarks(editedQuestion.getMarks());
        question.get().setAnswer(editedQuestion.getAnswer());
        question.get().setQuestion(editedQuestion.getQuestion());

        question.get().getOptions().clear();
        for(Option newOption: editedQuestion.getOptions()){
            newOption.setQuestion(question.get());
            question.get().getOptions().add(newOption);
        }

        return questionRepository.save(question.get());
    }

    public boolean deleteQuestionById(long id){
        Optional<Question> question = questionRepository.findById(id);

        if(question.isEmpty()){
            throw new QuestionNotFound("Invalid question for the given ID of " + id);
        }

        questionRepository.deleteById(id);
        return true;
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
        Optional<QuestionList> list = listRepository.findById(dto.getListId());
        if(list.isEmpty()){
            throw new QuestionNotFound("Invalid Question List");
        }
        question.setList(listRepository.getReferenceById(dto.getListId()));
        return question;
    }

    public QuestionResponseDto mapToDto(Question question){
        QuestionResponseDto dto = new QuestionResponseDto();
        dto.setId(question.getId());
        dto.setQuestion(question.getQuestion());
        dto.setMarks(question.getMarks());

        List<String> optionTexts = question.getOptions().stream()
                .map(Option::getOption_text)
                .collect(Collectors.toList());

        dto.setOptions(optionTexts);
        dto.setListId(question.getList().getId());

        return dto;
    }

    public boolean checkAnswerInTheOptions(Question question){
        for(Option option:question.getOptions()){
            if(option.getOption_text().equalsIgnoreCase(question.getAnswer())){
                return false;
            }
        }
        return true;
    }

    //TODO::add get all question method with pagination
}
