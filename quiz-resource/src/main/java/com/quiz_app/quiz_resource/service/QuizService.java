package com.quiz_app.quiz_resource.service;

import com.quiz_app.quiz_resource.data.*;
import com.quiz_app.quiz_resource.exception.BadRequest;
import com.quiz_app.quiz_resource.exception.ContentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {
    @Autowired
    private QuizRepository repository;

    @Autowired
    private QuizServiceClient client;

    public Quiz createQuiz(Quiz quiz){
        Quiz newQuiz = new Quiz();
        newQuiz.setCreatedBy(quiz.getCreatedBy());
        newQuiz.setQuestions(generateQuizQuestions(newQuiz,quiz.getQuestionListId(), quiz.getQuestionCount()));
        newQuiz.setStatus(quiz.getStatus());
        if(quiz.getQuestionCount() <= 0){
            throw new BadRequest("At least one question should be in the quiz");
        }
        newQuiz.setQuestionCount(quiz.getQuestionCount());
        newQuiz.setQuestionListId(quiz.getQuestionListId());
        newQuiz.setDescription(quiz.getDescription());
        newQuiz.setType(quiz.getType());
        newQuiz.setName(quiz.getName());

        return repository.save(newQuiz);
    }

    public Page <QuizResponseDto>getAllQuizzes(Pageable pageable)
    {
        Page<Quiz> quizPage = repository.findAll(pageable);
        return quizPage.map(this::mapToQuizDto);

    }

    public Quiz updateQuiz(long id,Quiz quiz){
        Quiz existingQuiz = repository.findById(id)
                .orElseThrow(() -> new ContentNotFound("Invalid Quiz for given ID!"));
        if(quiz.getQuestionCount() <= 0){
            throw new BadRequest("At least one question should be in the quiz");
        }
        if (existingQuiz.getQuestionListId() != quiz.getQuestionListId()) {
            List<QuizQuestion> newQuestions = generateQuizQuestions(existingQuiz,quiz.getQuestionListId(), quiz.getQuestionCount());
            existingQuiz.getQuestions().clear();
            newQuestions.forEach(q -> q.setQuiz(existingQuiz));
            existingQuiz.getQuestions().addAll(newQuestions);
        } else {
            if(existingQuiz.getQuestionCount() != quiz.getQuestionCount()){
                List<QuizQuestion> newQuestions = generateQuizQuestions(existingQuiz,quiz.getQuestionListId(), quiz.getQuestionCount());
                existingQuiz.getQuestions().clear();
                newQuestions.forEach(q -> q.setQuiz(existingQuiz));
                existingQuiz.getQuestions().addAll(newQuestions);
            } else {
                existingQuiz.getQuestions().clear();
                if (quiz.getQuestions() != null) {
                    existingQuiz.getQuestions().addAll(quiz.getQuestions());
                }
            }
        }

        existingQuiz.setName(quiz.getName());
        existingQuiz.setStatus(quiz.getStatus());
        existingQuiz.setQuestionCount(quiz.getQuestionCount());
        existingQuiz.setCreatedBy(quiz.getCreatedBy());
        existingQuiz.setQuestionListId(quiz.getQuestionListId());
        existingQuiz.setType(quiz.getType());
        existingQuiz.setDescription(quiz.getDescription());

        return repository.save(existingQuiz);
    }

    public boolean deleteQuiz(long id){
        Quiz quiz = repository.findById(id)
                .orElseThrow(()->new ContentNotFound("Invalid Quiz for the given ID!"));

        repository.delete(quiz);
        return true;
    }

    public Quiz getQuizById(long id){
        return repository.findById(id)
                .orElseThrow(()->new ContentNotFound("Invalid Quiz for the given ID"));
    }

    public List<Quiz> getAllQuiz(){
        return repository.findAll();
    }

    public Quiz mapToQuizModel(QuizRequestDto dto){
        Quiz newQuiz = new Quiz();
        newQuiz.setQuestionListId(dto.getQuestionListId());
        newQuiz.setQuestionCount(dto.getQuestionCount());
        newQuiz.setCreatedBy(dto.getCreatedBy());
        newQuiz.setName(dto.getName());
        newQuiz.setType(dto.getType());
        newQuiz.setDescription(dto.getDescription());

        return newQuiz;
    }

    public QuizResponseDto mapToQuizDto(Quiz createdQuiz) {
        List<QuizQuestionResponseDto> questionList = createdQuiz.getQuestions()
                .stream()
                .map(this::mapToQuizQuestionDto)
                .toList();
        QuizResponseDto responseDto = new QuizResponseDto();
        responseDto.setId(createdQuiz.getId());
        responseDto.setCreatedBy(createdQuiz.getCreatedBy());
        responseDto.setQuestions(questionList);
        responseDto.setStatus(createdQuiz.getStatus());
        responseDto.setQuestionCount(createdQuiz.getQuestionCount());
        responseDto.setCreatedAt(createdQuiz.getCreatedAt());
        responseDto.setQuestionListId(createdQuiz.getQuestionListId());
        responseDto.setName(createdQuiz.getName());
        responseDto.setType(createdQuiz.getType());
        responseDto.setDescription(createdQuiz.getDescription());
        return responseDto;
    }

    public QuizQuestionResponseDto mapToQuizQuestionDto(QuizQuestion question){
        QuizQuestionResponseDto responseDto = new QuizQuestionResponseDto();
        responseDto.setId(question.getId());
        responseDto.setQuestionId(question.getQuestionId());
        return responseDto;
    }

    public List<QuizQuestion> generateQuizQuestions(Quiz quiz,long questionListId,int count){
        QuestionListDto questionList = client.getQuestionListById(questionListId);

        if (questionList == null || questionList.getQuestions_id() == null || questionList.getQuestions_id().isEmpty()) {
            throw new ContentNotFound("Invalid question list for given ID");
        }

        //TODO::check if the question list have enough question to make the quiz

        Collections.shuffle(questionList.getQuestions_id());
        List<Long> selectedQuestions = questionList.getQuestions_id()
                .stream()
                .limit(count)
                .collect(Collectors.toCollection(ArrayList::new));

        return selectedQuestions.stream()
                .map(question ->{
                    QuizQuestion newQuestion = new QuizQuestion();
                    newQuestion.setQuestionId(question);
                    newQuestion.setQuiz(quiz);
                    return newQuestion;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
