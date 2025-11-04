package com.quiz_app.result_resource.data;

import java.util.List;

public class ResultRequestDto {
    private long quizId;
    private List<ResultQuestionRequestDto> answers;

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public List<ResultQuestionRequestDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ResultQuestionRequestDto> answers) {
        this.answers = answers;
    }
}
