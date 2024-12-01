package org.example.tennisscoreboard.service;

import java.util.HashMap;

public class MatchService {
    HashMap<String, CurrentMatch> activeMatch = new HashMap<>();

    public CurrentMatch startMatch(int idPlayerOne, int idPlayerTwo) {
        CurrentMatch currentMatch = new CurrentMatch(idPlayerOne, idPlayerTwo);
        activeMatch.put(currentMatch.getIdMatch(), currentMatch);
        return currentMatch;
    }

    public CurrentMatch getOngoingMatchById(String id) {
        CurrentMatch currentMatch = activeMatch.get(id);
        return currentMatch;
    }
}
