package com.quizz_no_bolso.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quizz_no_bolso.demo.model.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, String> {

}
