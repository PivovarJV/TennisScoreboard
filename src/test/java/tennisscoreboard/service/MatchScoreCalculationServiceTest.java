package tennisscoreboard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tennisscoreboard.example.tennisscoreboard.service.CurrentMatch;
import tennisscoreboard.example.tennisscoreboard.service.MatchScoreCalculationService;

import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreCalculationServiceTest {
    private final MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();
    private CurrentMatch currentMatch = new CurrentMatch(1,2);

    @Test
    void testDeucePointDoesNotEndGame() {
        currentMatch.setPointsPlayerOne(40);
        currentMatch.setPointsPlayerTwo(40);
        matchScoreCalculationService.calculationService("1", currentMatch);

        assertEquals(0, currentMatch.getGamesPlayerOne());
        assertEquals(0, currentMatch.getGamesPlayerTwo());
    }

    @Test
    void playerOneWinsGameWhenScoringAt40_0() {
        currentMatch.setPointsPlayerOne(40);
        currentMatch.setPointsPlayerTwo(0);

        matchScoreCalculationService.calculationService("1", currentMatch);

        assertEquals(1, currentMatch.getGamesPlayerOne());
        assertEquals(0, currentMatch.getGamesPlayerTwo());
        assertEquals(0, currentMatch.getPointsPlayerOne());
        assertEquals(0, currentMatch.getPointsPlayerTwo());
    }

    @Test
    void tieBreakStartsWhenGamesAre6_6() {
        currentMatch.setGamesPlayerOne(5);
        currentMatch.setGamesPlayerTwo(6);
        currentMatch.setPointsPlayerOne(40);
        currentMatch.setPointsPlayerTwo(0);
        matchScoreCalculationService.calculationService("1", currentMatch);

        assertTrue(currentMatch.isTeaBreak());
        assertEquals(6, currentMatch.getGamesPlayerOne());
        assertEquals(6, currentMatch.getGamesPlayerTwo());
        assertEquals(0, currentMatch.getPointsPlayerOne());
        assertEquals(0, currentMatch.getPointsPlayerTwo());
        matchScoreCalculationService.calculationService("1", currentMatch);
        assertEquals(1, currentMatch.getPointsPlayerOne());
        assertEquals(0, currentMatch.getPointsPlayerTwo());
    }
}
