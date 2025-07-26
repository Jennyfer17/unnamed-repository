package com.quizz_no_bolso.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.quizz_no_bolso.demo.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, String> {
    
}
