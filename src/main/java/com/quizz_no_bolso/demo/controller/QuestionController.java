package com.quizz_no_bolso.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizz_no_bolso.demo.model.Question;
import com.quizz_no_bolso.demo.model.request.QuestionDTO;
import com.quizz_no_bolso.demo.service.QuestionService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = service.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable String id) {
        Question question = service.getQuestionById(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody @Valid QuestionDTO questionDetails) {
        Question question = service.addQuestion(questionDetails);
        return new ResponseEntity<>(question, HttpStatus.OK);   
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable String id, @RequestBody @Valid QuestionDTO questionDetails) {
        Question updatedQuestion = service.updateQuestion(id, questionDetails);
        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }
}
