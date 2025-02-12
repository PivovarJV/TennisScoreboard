package tennisscoreboard.example.tennisscoreboard.util;

import lombok.Getter;
import tennisscoreboard.example.tennisscoreboard.model.Match;
import tennisscoreboard.example.tennisscoreboard.model.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Player.class);
            configuration.addAnnotatedClass(Match.class);
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
