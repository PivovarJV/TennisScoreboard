package org.example.tennisscoreboard.service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MatchService {
    static ConcurrentHashMap<String, CurrentMatch> activeMatch = new ConcurrentHashMap<>();

    public CurrentMatch startMatch(int idPlayerOne, int idPlayerTwo) {
        CurrentMatch currentMatch = new CurrentMatch(idPlayerOne, idPlayerTwo);
        activeMatch.put(currentMatch.getIdMatch(), currentMatch);
        return currentMatch;
    }

    public CurrentMatch getOngoingMatchById(String id) {
        return activeMatch.get(id);
    }
}
// стек хит, потоки, почему общая перменная не потоко безоп, потоко безопасность (тригулов)