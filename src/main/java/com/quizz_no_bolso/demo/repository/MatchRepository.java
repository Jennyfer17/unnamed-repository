package com.quizz_no_bolso.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quizz_no_bolso.demo.model.Match;

@Repository
public interface  MatchRepository extends CrudRepository<Match, String> {

    // This class can be extended with custom query methods if needed.
    // For example, you can add methods to find matches by player or quizz.
    
    // Example:
    // List<Match> findByPlayer(User player);
    // List<Match> findByQuizz(Quizz quizz);

}
