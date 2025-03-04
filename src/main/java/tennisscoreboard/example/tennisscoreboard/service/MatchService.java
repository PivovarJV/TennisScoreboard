package tennisscoreboard.example.tennisscoreboard.service;

import tennisscoreboard.example.tennisscoreboard.dao.MatchDAO;
import tennisscoreboard.example.tennisscoreboard.dto.MatchDTO;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MatchService {
    static ConcurrentHashMap<String, CurrentMatch> activeMatch = new ConcurrentHashMap<>();
    private final MatchDAO matchDAO = new MatchDAO();

    public List<MatchDTO> getMatchesListByNameService(String name, int page, int pageSize) {
        return matchDAO.getMatchesByName(name, page, pageSize);
    }

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

    public int getMatchesCountService(int pageSize) {
        return (int) Math.ceil((double) matchDAO.getMatchesCount() / pageSize);
    }

    public List<MatchDTO> getMatchesPageService(int page, int pageSize) {
        return matchDAO.getMatchesPage(page, pageSize);
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