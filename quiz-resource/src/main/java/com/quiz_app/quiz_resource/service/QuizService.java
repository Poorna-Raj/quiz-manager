package com.quiz_app.quiz_resource.service;

import com.quiz_app.quiz_resource.data.*;
import com.quiz_app.quiz_resource.exception.BadRequest;
import com.quiz_app.quiz_resource.exception.ContentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository repository;

    @Autowired
    private QuestionServiceClient client;

    public Quiz createQuiz(Quiz quiz){
        QuestionListDto questionList = client.getQuestionListById(quiz.getQuestionListId());

        if(questionList == null||questionList.getQuestions_id().isEmpty()||questionList.getQuestions_id() == null){
            throw new ContentNotFound("Invalid question list for given ID");
        }

        //TODO :: check weather the question list have enough questions to create the list

        Collections.shuffle(questionList.getQuestions_id());
        List<Long> selectedQuestions = questionList.getQuestions_id()
                .stream()
                .limit(quiz.getQuestionCount())
                .toList();

        List<QuizQuestion> quizQuestions = selectedQuestions.stream()
                .map(question ->{
                    QuizQuestion newQuestion = new QuizQuestion();
                    newQuestion.setQuestionId(question);
                    return newQuestion;
                })
                .toList();

        Quiz newQuiz = new Quiz();
        newQuiz.setCreatedBy(quiz.getCreatedBy());
        newQuiz.setQuestions(quizQuestions);
        newQuiz.setStatus(quiz.getStatus());
        newQuiz.setQuestionCount(quiz.getQuestionCount());
        newQuiz.setQuestionListId(quiz.getQuestionListId());

        return repository.save(newQuiz);
    }

    public Quiz updateQuiz(long id,Quiz quiz){
        Quiz existingQuiz = repository.findById(id)
                .orElseThrow(() -> new ContentNotFound("Invalid Quiz for given ID!"));

        if(existingQuiz.getQuestionListId() != quiz.getQuestionListId() && !isQuestionListValid(quiz.getQuestionListId())){
            throw new BadRequest("Invalid question list");
        }

        existingQuiz.setName(quiz.getName());
        existingQuiz.setStatus(quiz.getStatus());
        existingQuiz.setQuestions(quiz.getQuestions());
        existingQuiz.setQuestionCount(quiz.getQuestionCount());
        existingQuiz.setCreatedBy(quiz.getCreatedBy());
        existingQuiz.setQuestionListId(quiz.getQuestionListId());

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
        return responseDto;
    }

    public QuizQuestionResponseDto mapToQuizQuestionDto(QuizQuestion question){
        QuizQuestionResponseDto responseDto = new QuizQuestionResponseDto();
        responseDto.setId(question.getId());
        responseDto.setQuestionId(question.getQuestionId());
        return responseDto;
    }

    public boolean isQuestionListValid(long questionListId){
        QuestionListDto questionList = client.getQuestionListById(questionListId);

        if(questionList == null||questionList.getQuestions_id().isEmpty()||questionList.getQuestions_id() == null){
            throw new ContentNotFound("Invalid question list for given ID");
        }

        return true;
    }
}
