package org.example.tennisscoreboard.service;

import lombok.Data;

import java.util.UUID;
@Data
public class CurrentMatch {
    private String idMatch;
    private int idPlayerOne;
    private int idPlayerTwo;
    private int pointsPlayerOne;
    private int pointsPlayerTwo;
    private int gamesPlayerOne;
    private int gamesPlayerTwo;
    private int setsPlayerOne;
    private int setsPlayerTwo;

    public CurrentMatch(int idPlayerOne, int idPlayerTwo) {
        this.idMatch = UUID.randomUUID().toString();
        this.idPlayerOne = idPlayerOne;
        this.idPlayerTwo = idPlayerTwo;
        this.pointsPlayerOne = 0;
        this.pointsPlayerTwo = 0;
        this.gamesPlayerOne = 0;
        this.gamesPlayerTwo = 0;
        this.setsPlayerOne = 0;
        this.setsPlayerTwo = 0;
    }
}
