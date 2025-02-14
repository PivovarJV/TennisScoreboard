package tennisscoreboard.example.tennisscoreboard.dao;

import lombok.NoArgsConstructor;
import tennisscoreboard.example.tennisscoreboard.model.Player;
import tennisscoreboard.example.tennisscoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@NoArgsConstructor
public class PlayersDAO {
    public Player getPlayerByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Player WHERE name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }

    public Player savePlayer(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Player player = Player.builder()
                    .name(name)
                    .build();
            Transaction transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
            return player;
        }
    }
}
