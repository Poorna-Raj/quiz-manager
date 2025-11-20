package com.quiz_app.quiz_service.data.repository;

import com.quiz_app.quiz_service.data.model.QuestionList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionListRepository extends JpaRepository<QuestionList,Long> {
}

