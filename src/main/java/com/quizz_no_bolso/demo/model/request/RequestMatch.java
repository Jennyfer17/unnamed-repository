package com.quizz_no_bolso.demo.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RequestMatch {
    @NotNull(message = "Player ID cannot be null")
    @Size(min = 1, message = "Player ID must not be empty")
    private String playerId;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
