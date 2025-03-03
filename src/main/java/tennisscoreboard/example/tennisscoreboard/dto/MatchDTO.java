package tennisscoreboard.example.tennisscoreboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchDTO {
    private String nameOnePlayer;
    private String nameTwoPlayer;
    private String nameWinnerPlayer;
}
