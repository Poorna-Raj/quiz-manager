package com.quiz_app.question_resource.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.List;

public class QuestionListResponseDto {
    private long id;
    private String topic;
    private String name;
    private List<Long> questions_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getQuestions_id() {
        return questions_id;
    }

    public void setQuestions_id(List<Long> questions_id) {
        this.questions_id = questions_id;
    }
}
