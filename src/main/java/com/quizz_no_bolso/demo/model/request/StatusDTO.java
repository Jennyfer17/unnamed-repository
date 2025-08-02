package com.quizz_no_bolso.demo.model.request;

public class StatusDTO {
    private int experience_points;
    private int level;
    private String league;
    public int getExperience_points() {
        return experience_points;
    }
    public void setExperience_points(int experience_points) {
        this.experience_points = experience_points;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String getLeague() {
        return league;
    }
    public void setLeague(String league) {
        this.league = league;
    }

    
}
