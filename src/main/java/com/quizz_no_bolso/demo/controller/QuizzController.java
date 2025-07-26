package com.quizz_no_bolso.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizz_no_bolso.demo.model.Quizz;
import com.quizz_no_bolso.demo.model.request.RequestQuizz;
import com.quizz_no_bolso.demo.service.QuizzService;

@RestController
@RequestMapping("/api/quizz")
public class QuizzController {
    private final QuizzService service;

    public QuizzController(QuizzService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Quizz>> getUsers() {
        List<Quizz> quizzes = service.getAllQuizzes();
        
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quizz> getQuizzById(@PathVariable String id) {
        Quizz quizz = service.getQuizzById(id);

        return new ResponseEntity<>(quizz, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Quizz> createQuizz(@RequestBody RequestQuizz quizzDetails) {
        Quizz quizz = service.addQuizz(quizzDetails);
        return new ResponseEntity<>(quizz, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public String updateQuizz(@PathVariable String id, @RequestBody Quizz quizzDetails) {
        return "Update user with ID: " + id;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizz(@PathVariable String id) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
