package tennisscoreboard.example.tennisscoreboard.dao;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.mapping.Collection;
import tennisscoreboard.example.tennisscoreboard.dto.MatchDTO;
import tennisscoreboard.example.tennisscoreboard.model.Match;
import tennisscoreboard.example.tennisscoreboard.model.Player;
import tennisscoreboard.example.tennisscoreboard.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
public class MatchDAO {
    PlayersDAO playersDAO = new PlayersDAO();

    public void saveFinishMatch(int idOnePlayer, int idTwoPlayer, int idWinner) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Match match = Match.builder().idOnePlayer(idOnePlayer)
                    .idTwoPlayer(idTwoPlayer).idWinnerPlayer(idWinner).build();
            session.persist(match);
            transaction.commit();
        }
    }

    public List<MatchDTO> getMatchesByName(String name, int page, int pageSize) {
        Player player = playersDAO.getPlayerByName(name);
        if (player == null) {
            throw new IllegalArgumentException("Игрок с именем " + name + " не найден");
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<Match> listMatch = session.createQuery("FROM Match WHERE idOnePlayer = :playerId OR idTwoPlayer = :playerId", Match.class)
                 .setParameter("playerId", player.getId())
                 .setMaxResults(pageSize)
                 .setFirstResult((page - 1) * pageSize)
                 .list();

         if (listMatch.isEmpty()) {
             return Collections.emptyList();
         }
            return convertToDTO(listMatch, session);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении матчей по имени: " + name, e);
        }
    }

    public List<MatchDTO> getMatchesPage(int page, int pageSize) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Match> listMatch = session.createQuery("FROM Match ORDER BY id DESC", Match.class)
                    .setMaxResults(pageSize)
                    .setFirstResult((page - 1) * pageSize)
                    .list();
            return convertToDTO(listMatch, session);
        }
    }

    public int getMatchesCount() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long countMatch = session.createQuery("SELECT COUNT(*) FROM Match", Long.class).uniqueResult();
            return Math.toIntExact(countMatch);
        }
    }

    private List<MatchDTO> convertToDTO(List<Match> listMatch, Session session) {
        List<MatchDTO> result = new ArrayList<>();
        for (Match match : listMatch) {
            Player onePlayer = session.get(Player.class, match.getIdOnePlayer());
            Player twoPlayer = session.get(Player.class, match.getIdTwoPlayer());
            Player winnerPlayer = session.get(Player.class, match.getIdWinnerPlayer());
            result.add(new MatchDTO(onePlayer.getName(), twoPlayer.getName(), winnerPlayer.getName()));
        }
        return result;
    }
}
