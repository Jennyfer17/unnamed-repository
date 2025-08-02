package com.quizz_no_bolso.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizz_no_bolso.demo.model.Question;
import com.quizz_no_bolso.demo.model.Match;
import com.quizz_no_bolso.demo.model.Answer;
import com.quizz_no_bolso.demo.model.request.RequestSubmitAnswer;
import com.quizz_no_bolso.demo.repository.AnswerRepository;
import com.quizz_no_bolso.demo.shared.Utils;

import jakarta.validation.Valid;

@Service
public class AnswerService {
    private final AnswerRepository repository;

    public AnswerService(AnswerRepository repository) {
        this.repository = repository;
    }

    public Answer addResponse(Match match, Question question) {
        Answer ans = new Answer();
        ans.setId(Utils.generateRandomId());
        ans.setQuestion(question);
        ans.setMatch(match);

        return repository.save(ans);
    }

    public Answer addMatchToAnswer(String id, Match match) {
        Answer ans = getResponseById(id);
        ans.setMatch(match);

        return repository.save(ans);
    }

    public List<Answer> getAllResponses() {
        return (List<Answer>)repository.findAll();
    }

    public Answer getResponseById(String id) {
        return repository.findById(id).orElseThrow();
    }

    private boolean isAnswerCorrect(Question question, @Valid RequestSubmitAnswer newResponse) {
        return question.getCorrectOptionIndex() == newResponse.getOption();
    }

    public Answer updateResponse(String id, Question question, RequestSubmitAnswer updatedResponse) {
        Answer response = getResponseById(id);
        response.setText(question.getOptions().get(updatedResponse.getOption()));
        response.setAnsweredAt(System.currentTimeMillis());
        boolean isCorrect = isAnswerCorrect(question, updatedResponse);
        response.setCorrect(isCorrect);
        response.setPointsEarned(isCorrect ? question.getPoint() : 0);

        return repository.save(response);

    }
}
