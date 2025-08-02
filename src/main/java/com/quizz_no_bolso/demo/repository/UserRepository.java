package com.quizz_no_bolso.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quizz_no_bolso.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    public User findUserByFirstName(String firstName);
    public boolean existsByFirstName(String firstName);
}
