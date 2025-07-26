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

import com.quizz_no_bolso.demo.model.Answer;
import com.quizz_no_bolso.demo.model.Match;
import com.quizz_no_bolso.demo.model.request.RequestMatch;
import com.quizz_no_bolso.demo.model.request.RequestSubmitAnswer;
import com.quizz_no_bolso.demo.service.MatchService;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService service;

    public MatchController(MatchService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = service.getAllMatches();
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable String id) {
        Match match = service.getMatchById(id);
        return ResponseEntity.ok(match);
    }

    @PostMapping("/start")
    public ResponseEntity<Match> createMatch(@RequestBody RequestMatch matchDetails) {
        Match match = service.startMatch(matchDetails);

        return ResponseEntity.ok(match);
    }

    // @PutMapping("{playerId}/{id}/answer")
    // public ResponseEntity<Match> updateMatch(@PathVariable String playerId, @PathVariable String id, @RequestBody RequestSubmitAnswer answerDetails) {
    //     Match match = service.updateMatch(id, answerDetails);
    //     return ResponseEntity.ok(match);
    // }

    @PutMapping("answer/{matchId}/{answerId}")
    public ResponseEntity<Match> updateMatchAnswer(@PathVariable String matchId, @PathVariable String answerId, @RequestBody RequestSubmitAnswer answerDetails) {
        Match match = service.updateMatchAnswer(matchId, answerId, answerDetails);
        return ResponseEntity.ok(match);
    }

    // @PutMapping("answer/{id}")
    // public ResponseEntity<Match> answerQuestion(@PathVariable String id, @RequestBody RequestSubmitAnswer answerDetails) {
    //     Match match = service.answerQuestion(id, answerDetails);

    //     return new ResponseEntity<>(match, HttpStatus.OK);
    // }

    @PutMapping("end/{id}")
    public ResponseEntity<Match> endMatch(@PathVariable String id) {
        Match match = service.endMatch(id);
        return ResponseEntity.ok(match);
    }
}
