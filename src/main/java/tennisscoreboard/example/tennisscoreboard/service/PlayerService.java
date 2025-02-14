package tennisscoreboard.example.tennisscoreboard.service;

import tennisscoreboard.example.tennisscoreboard.dao.PlayersDAO;
import tennisscoreboard.example.tennisscoreboard.model.Player;

public class PlayerService {
    private final PlayersDAO playersDAO = new PlayersDAO();

    public Player findOrCreatePlayer(String name) {
        Player player = playersDAO.getPlayerByName(name);
        if (player == null) {
            return playersDAO.savePlayer(name);
        }
        return player;
    }

    public int getIdByNamePlayer(String name) {
        Player player = playersDAO.getPlayerByName(name);
        return player.getId();
    }
}
