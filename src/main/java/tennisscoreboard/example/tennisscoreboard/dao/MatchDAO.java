package tennisscoreboard.example.tennisscoreboard.dao;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tennisscoreboard.example.tennisscoreboard.model.Match;
import tennisscoreboard.example.tennisscoreboard.util.HibernateUtil;

@NoArgsConstructor
public class MatchDAO {
    public void saveFinishMatch(int idOnePlayer, int idTwoPlayer, int idWinner) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Match match = Match.builder().player1(idOnePlayer)
                    .player2(idTwoPlayer).winner(idWinner).build();
            session.persist(match);
            transaction.commit();
        }
    }
}
