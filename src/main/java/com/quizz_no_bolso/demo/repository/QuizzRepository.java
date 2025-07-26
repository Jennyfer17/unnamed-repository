package com.quizz_no_bolso.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quizz_no_bolso.demo.model.Quizz;

@Repository
public interface QuizzRepository extends CrudRepository<Quizz, String> {

}
