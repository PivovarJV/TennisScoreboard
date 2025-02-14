package tennisscoreboard.example.tennisscoreboard.service;

import tennisscoreboard.example.tennisscoreboard.dao.MatchDAO;
import tennisscoreboard.example.tennisscoreboard.dao.PlayersDAO;

import java.util.concurrent.ConcurrentHashMap;

public class MatchService {
    static ConcurrentHashMap<String, CurrentMatch> activeMatch = new ConcurrentHashMap<>();
    private final MatchDAO matchDAO = new MatchDAO();

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

    public void saveMatchInBaseData(int idOnePlayer, int idTwoPlayer, int idWinner) {
        matchDAO.saveFinishMatch(idOnePlayer, idTwoPlayer, idWinner);
    }
}
// стек хит, потоки, почему общая перменная не потоко безоп, потоко безопасность (тригулов)