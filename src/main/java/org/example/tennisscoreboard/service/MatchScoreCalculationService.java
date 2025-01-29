package org.example.tennisscoreboard.service;

public class MatchScoreCalculationService {
    public void calculationService(String playerID, CurrentMatch currentMatch) {
        if (playerID.equals("1")) {
            calculationPoints(currentMatch, Player.ONE);
        } else if (playerID.equals("2")) {
            calculationPoints(currentMatch, Player.TWO);
        }
    }
    private void calculationPoints(CurrentMatch currentMatch, Player player) {
       int currentPoints = (player == Player.ONE)
               ? currentMatch.getPointsPlayerOne()
               : currentMatch.getPointsPlayerTwo();

        int newPoints = switch (currentPoints) {
            case 0 -> 15;
            case 15 -> 30;
            case 30 -> 40;
            case 40 -> {
                calculationGames(currentMatch, player);
                yield 0;
            }
            default -> throw new IllegalStateException();
        };
        updatePoints(currentMatch, player, newPoints);
    }

    private void calculationGames(CurrentMatch currentMatch, Player player) {
        if (player == Player.ONE) {
            currentMatch.setGamesPlayerOne(currentMatch.getGamesPlayerOne() + 1);
            resetPoints(currentMatch);
            if (currentMatch.getGamesPlayerOne() == 6 && currentMatch.getGamesPlayerTwo() <= 4
                    || currentMatch.getGamesPlayerOne() == 7 && currentMatch.getGamesPlayerTwo() <= 5) {
                calculationSets(currentMatch, player);
            }
        } else if (player == Player.TWO) {
            currentMatch.setGamesPlayerTwo(currentMatch.getGamesPlayerTwo() + 1);
            resetPoints(currentMatch);
            if ((currentMatch.getGamesPlayerTwo() == 6 && currentMatch.getGamesPlayerOne() <= 4)
            || (currentMatch.getGamesPlayerTwo() == 7 && currentMatch.getGamesPlayerOne() <= 5)) {
                calculationSets(currentMatch, player);
            }
        }
    }
    // доработать
    private void teaBreak(CurrentMatch currentMatch, Player player) {
//        int pointOnePlayer = currentMatch.getPointsPlayerOne();
//        int pointTwoPlayer = currentMatch.getPointsPlayerTwo();
//
//        currentMatch.setPointsPlayerOne(pointOnePlayer += 1);
//        if (player == Player.ONE) {
//            if (pointOnePlayer - pointTwoPlayer == 2 && pointOnePlayer >= 7) {
//                resetPoints(currentMatch);
//                calculationSets(currentMatch, player);
//            }
//        }
        int currentPoints = (player == Player.ONE)
                ? currentMatch.getPointsPlayerOne()
                : currentMatch.getPointsPlayerTwo();

        int newPoints = currentPoints + 1;
        updatePoints(currentMatch, player, newPoints);
        if (player == Player.ONE) {
            if (newPoints - currentMatch.getPointsPlayerTwo() == 2 && newPoints >= 7) {
                calculationSets(currentMatch, player);
                resetPoints(currentMatch);
            }
        } else if (player == Player.TWO) {
            if (newPoints - currentMatch.getPointsPlayerOne() == 2 && newPoints >=7) {
                calculationSets(currentMatch, player);
                resetPoints(currentMatch);
            }
        }
    }

    private void calculationSets(CurrentMatch currentMatch, Player player) {
        if (player == Player.ONE) {
            currentMatch.setSetsPlayerOne(currentMatch.getSetsPlayerOne() + 1);
            resetGames(currentMatch);
            if (currentMatch.getSetsPlayerOne() == 2 && currentMatch.getSetsPlayerTwo() <= 1) {
                resetSets(currentMatch);
            }
        } else {
            currentMatch.setSetsPlayerTwo(currentMatch.getSetsPlayerTwo() + 1);
            resetGames(currentMatch);
            if (currentMatch.getSetsPlayerTwo() == 2 && currentMatch.getSetsPlayerOne() <= 1) {
                resetSets(currentMatch);
            }
        }
    }

    private void updatePoints(CurrentMatch currentMatch, Player player, int newPoints) {
        if (player == Player.ONE) {
            currentMatch.setPointsPlayerOne(newPoints);
        } else {
            currentMatch.setPointsPlayerTwo(newPoints);
        }
    }

    private void resetPoints(CurrentMatch currentMatch) {
        currentMatch.setPointsPlayerOne(0);
        currentMatch.setPointsPlayerTwo(0);
    }

    private void resetGames(CurrentMatch currentMatch) {
        currentMatch.setGamesPlayerOne(0);
        currentMatch.setGamesPlayerTwo(0);
    }

    private void resetSets(CurrentMatch currentMatch) {
        currentMatch.setSetsPlayerOne(0);
        currentMatch.setSetsPlayerTwo(0);
    }
    public enum Player {
        ONE, TWO
    }
}
/// сделать логику сетов, при счете 7\7 матч заканчивается. Сделать тайм брейк