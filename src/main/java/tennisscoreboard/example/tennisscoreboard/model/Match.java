package tennisscoreboard.example.tennisscoreboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "player1")
    private int idOnePlayer;

    @Column(name = "player2")
    private int idTwoPlayer;

    @Column(name = "winner")
    private int idWinnerPlayer;
}
