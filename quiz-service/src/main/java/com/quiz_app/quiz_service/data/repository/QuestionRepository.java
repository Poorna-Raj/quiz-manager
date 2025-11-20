package com.quiz_app.quiz_service.data.repository;

import com.quiz_app.quiz_service.data.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
