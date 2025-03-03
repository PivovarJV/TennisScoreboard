package tennisscoreboard.example.tennisscoreboard.dao;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tennisscoreboard.example.tennisscoreboard.dto.MatchDTO;
import tennisscoreboard.example.tennisscoreboard.model.Match;
import tennisscoreboard.example.tennisscoreboard.model.Player;
import tennisscoreboard.example.tennisscoreboard.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class MatchDAO {
    public void saveFinishMatch(int idOnePlayer, int idTwoPlayer, int idWinner) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Match match = Match.builder().idOnePlayer(idOnePlayer)
                    .idTwoPlayer(idTwoPlayer).idWinnerPlayer(idWinner).build();
            session.persist(match);
            transaction.commit();
        }
    }

    public List<MatchDTO> getMatchesPage(int page, int pageSize) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Match> listMatch = session.createQuery("FROM Match ORDER BY id DESC", Match.class)
                    .setMaxResults(pageSize)
                    .setFirstResult((page - 1) * pageSize)
                    .list();

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

    public int getMatchesCount() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long countMatch = session.createQuery("SELECT COUNT(*) FROM Match", Long.class).uniqueResult();
            return Math.toIntExact(countMatch);
        }
    }
}
