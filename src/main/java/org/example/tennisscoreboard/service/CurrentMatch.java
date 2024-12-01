package org.example.tennisscoreboard.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
public class CurrentMatch {
    private String idMatch;
    private int idPlayerOne;
    private int idPlayerTwo;
    private int scorePlayerOne;
    private int scorePlayerTwo;

    public CurrentMatch(int idPlayerOne, int idPlayerTwo) {
        this.idMatch = UUID.randomUUID().toString();
        this.idPlayerOne = idPlayerOne;
        this.idPlayerTwo = idPlayerTwo;
        this.scorePlayerOne = 0;
        this.scorePlayerTwo = 0;
    }
}
