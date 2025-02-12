package tennisscoreboard.example.tennisscoreboard.service;

import java.util.concurrent.ConcurrentHashMap;

public class MatchService {
    static ConcurrentHashMap<String, CurrentMatch> activeMatch = new ConcurrentHashMap<>();

    public CurrentMatch startMatch(int idPlayerOne, int idPlayerTwo) {
        CurrentMatch currentMatch = new CurrentMatch(idPlayerOne, idPlayerTwo);
        activeMatch.put(currentMatch.getIdMatch(), currentMatch);
        return currentMatch;
    }

    public String whoWinner(CurrentMatch currentMatch, String nameOnePlayer, String nameTwoPlayer) {
        String winner = null;
        if (currentMatch.getSetsPlayerOne() == 2) {
            winner = nameOnePlayer;
        } else if (currentMatch.getSetsPlayerTwo() == 2) {
            winner = nameTwoPlayer;
        }
        return winner;
    }
    public void removeMatch(ConcurrentHashMap<String, CurrentMatch> activeMatch, String key) {
        activeMatch.remove(key);
    }

    public CurrentMatch getOngoingMatchById(String id) {
        return activeMatch.get(id);
    }
}
// стек хит, потоки, почему общая перменная не потоко безоп, потоко безопасность (тригулов)