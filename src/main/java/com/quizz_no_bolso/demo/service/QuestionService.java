package com.quizz_no_bolso.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.quizz_no_bolso.demo.model.Question;
import com.quizz_no_bolso.demo.model.request.QuestionDTO;
import com.quizz_no_bolso.demo.repository.QuestionRepository;
import com.quizz_no_bolso.demo.shared.Utils;

@Service
public class QuestionService {

    private final QuestionRepository repository;
    private final ModelMapper modelMapper;

    public QuestionService(QuestionRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Question addQuestion(QuestionDTO newQuestion) {
        Question question = modelMapper.map(newQuestion, Question.class);
        question.setId(Utils.generateRandomId());
        // question.setText(newQuestion.getText());
        // question.setOptions(newQuestion.getOptions());
        // question.setCorrectOptionIndex(newQuestion.getCorrectOptionIndex());
        // question.setPoint(newQuestion.getPoint());

        return repository.save(question);
    }

    public Question getQuestionById(String id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Question> getQuestionsByIds(List<String> ids) {
        List<Question> questions = (List<Question>) repository.findAllById(ids);

        return questions;
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = (List<Question>) repository.findAll();
        return questions;
    }

    public Question updateQuestion(String id, QuestionDTO updatedQuestion) {
        Question question = getQuestionById(id);
        question = modelMapper.map(updatedQuestion, Question.class);

        return repository.save(question);
    }

    public void deleteQuestion(String id) {
        repository.deleteById(id);
    }

    public boolean existsQuestion(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty.");
        }
        return repository.existsById(id);
    }
}
