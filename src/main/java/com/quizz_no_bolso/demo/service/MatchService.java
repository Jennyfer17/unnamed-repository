package com.quizz_no_bolso.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quizz_no_bolso.demo.model.Answer;
import com.quizz_no_bolso.demo.model.Match;
import com.quizz_no_bolso.demo.model.Question;
import com.quizz_no_bolso.demo.model.User;
import com.quizz_no_bolso.demo.model.request.RequestMatch;
import com.quizz_no_bolso.demo.model.request.RequestSubmitAnswer;
import com.quizz_no_bolso.demo.repository.MatchRepository;
import com.quizz_no_bolso.demo.shared.Utils;

@Service
public class MatchService {

    private final MatchRepository repository;
    private final UserService playerService;
    private final AnswerService answerService;
    private final QuestionService questionService;

    public MatchService(MatchRepository repository,
            UserService playerService, AnswerService answerService, QuestionService questionService) {
        this.repository = repository;
        this.playerService = playerService;
        this.answerService = answerService;
        this.questionService = questionService;
    }

    private boolean existsPlayer(String userId) {

        return playerService.existsUser(userId);
    }

    public Match startMatch(RequestMatch newMatch) {
        if (!existsPlayer(newMatch.getPlayerId())) {
            throw new IllegalArgumentException("Player does not exist.");
        }

        Match match = new Match();
        User player = playerService.getUserById(newMatch.getPlayerId());

        // Answer
        List<Answer> answers = new ArrayList<>();
        for (Question question : questionService.getAllQuestions()) {
            Answer answer = new Answer();
            answer.setId(Utils.generateRandomId());
            answer.setText(null);
            answer.setAnsweredAt(null);
            answer.setCorrect(false);
            answer.setPointsEarned(0);
            answer.setQuestion(question);
            answer.setMatch(match);
            answers.add(answer);
        }

        // Match
        match.setId(Utils.generateRandomId());
        match.setPlayer(player);
        match.setScore(0);
        match.setStartTime(System.currentTimeMillis());
        match.setEndTime(null);
        match.setAnswers(answers);

        return repository.save(match);
    }

    public List<Match> getAllMatches() {
        if (repository == null) {
            throw new IllegalStateException("MatchRepository is not initialized");
        }
        return (List<Match>) repository.findAll();
    }

    public Match getMatchById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Match not found with id: " + id));
    }

    private boolean isActiveMatch(Match match) {
        return match.getEndTime() == null;
    }

    private boolean existsAnswerInMatch(Match match, String answerId) {
        return match.getAnswers().stream().anyMatch(ans -> ans.getId().equals(answerId));
    }

    public Match updateMatchAnswer(String matchId, String answerId, RequestSubmitAnswer updatedAnswer) {
        Match match = getMatchById(matchId);

        if (!existsAnswerInMatch(match, answerId)) {
            throw new IllegalArgumentException("Answer not found in match.");
        }

        if (!isActiveMatch(match)) {
            throw new IllegalStateException("Match has already ended.");
        }

        // Assuming the answerId is valid and corresponds to a question in the match
        Answer ans = match.getAnswers().stream().filter(answer -> answer.getId().equals(answerId))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Answer not found in match."));

        ans.setText(ans.getQuestion().getOptions().get(updatedAnswer.getOption()));
        ans.setAnsweredAt(System.currentTimeMillis());
        ans.setCorrect(ans.getQuestion().getCorrectOptionIndex() == updatedAnswer.getOption());
        ans.setPointsEarned(ans.isCorrect() ? ans.getQuestion().getPoint() : 0);
        match.setScore(match.getScore() + ans.getPointsEarned());

        return repository.save(match);
    }

    public Match endMatch(String id) {
        Match match = getMatchById(id);

        if (!isActiveMatch(match)) {
            throw new IllegalStateException("Match has already ended.");
        }

        match.setEndTime(System.currentTimeMillis());
        return repository.save(match);

    }


    // public Match updateMatch(String id, RequestSubmitAnswer answer) {
    //     Match match = getMatchById(id);

    //     if (match.getEndTime() != null) {
    //         throw new IllegalStateException("Match has already ended.");
    //     }
    //     if (answer.getOption() < 0) {
    //         throw new IllegalArgumentException("Invalid option.");
    //     }
    //     return repository.save(match);
    // }

    // public Match answerQuestion(String id, RequestSubmitAnswer answer) {
    //     Match match = getMatchById(id);
    //     if (!isActiveMatch(match)) {
    //         throw new IllegalStateException("Match has already ended.");
    //     }
    //     if (answer.getOption() < 0) {
    //         throw new IllegalArgumentException("The chosen option does not exist.");
    //     }

    //     List<Answer> answers = new ArrayList<>();

    //     for (Answer ans : match.getAnswers()) {
    //         if (ans.getId().equals(answer.getId())) {
    //             ans.setText(ans.getQuestion().getOptions().get(answer.getOption()));
    //             ans.setAnsweredAt(System.currentTimeMillis());
    //             ans.setCorrect(ans.getQuestion().getCorrectOptionIndex() == answer.getOption());
    //             ans.setPointsEarned(ans.isCorrect() ? ans.getQuestion().getPoint() : 0);
    //             match.setScore(match.getScore() + ans.getPointsEarned());
    //         }
    //         answers.add(ans);
    //     }

    //     return repository.save(match);
    // }

}
