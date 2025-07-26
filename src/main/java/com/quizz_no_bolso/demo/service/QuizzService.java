package com.quizz_no_bolso.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizz_no_bolso.demo.model.Quizz;
import com.quizz_no_bolso.demo.model.request.RequestQuizz;
import com.quizz_no_bolso.demo.repository.QuizzRepository;
import com.quizz_no_bolso.demo.shared.Utils;

@Service
public class QuizzService {
    private final QuizzRepository repository;
    private final QuestionService questionService;
    
    public QuizzService(QuizzRepository repository, QuestionService questionService) {
        this.repository = repository;
        this.questionService = questionService;
    }

    private boolean existsQuestion(List<String> questionsId) {

        for (String questionId : questionsId) {
            return questionService.getAllQuestions().stream()
                    .anyMatch(question -> question.getId().equals(questionId));
        }
        return false;
    }
    public Quizz addQuizz(RequestQuizz newQuizz) {
        if (newQuizz.getQuestionsId() == null || newQuizz.getQuestionsId().isEmpty()) {
            throw new IllegalArgumentException("Quizz must have at least one question.");
        }
        if (!existsQuestion(newQuizz.getQuestionsId())) {
            throw new IllegalArgumentException("One or more questions do not exist.");
        }
        Quizz quizz = new Quizz();
        quizz.setId(Utils.generateRandomId());
        quizz.setTitle(newQuizz.getTitle());
        quizz.setSubject(newQuizz.getSubject());
        quizz.setDescription(newQuizz.getDescription());
        quizz.setQuestions(questionService.getQuestionsByIds(newQuizz.getQuestionsId()));

        return repository.save(quizz);
    }

    public Quizz getQuizzById(String id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Quizz> getAllQuizzes() {
        List<Quizz> quizzes = (List<Quizz>)repository.findAll();

        return quizzes;
    }

    public boolean existsQuizz(String quizzId) {
        if (quizzId == null || quizzId.isEmpty()) {
            throw new IllegalArgumentException("Quizz ID cannot be null or empty.");
        }
        return repository.existsById(quizzId);
    }

    public void updateQuizz() {

    }

    public void deleteQuizz(String id) {

    }
}
