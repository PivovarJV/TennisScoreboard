package org.example.tennisscoreboard.service;

import org.example.tennisscoreboard.dao.PlayersDAO;
import org.example.tennisscoreboard.model.Player;

public class PlayerService {
    private final PlayersDAO playersDAO = new PlayersDAO();

    public Player findOrCreatePlayer(String name) {
        Player player = playersDAO.getPlayerByName(name);
        if (player == null) {
            return playersDAO.savePlayer(name);
        }
        return player;
    }
}
