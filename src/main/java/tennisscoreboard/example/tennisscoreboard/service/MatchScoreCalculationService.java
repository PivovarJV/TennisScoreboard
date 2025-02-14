package tennisscoreboard.example.tennisscoreboard.service;

import static tennisscoreboard.example.tennisscoreboard.service.MatchService.activeMatch;

public class MatchScoreCalculationService {
    MatchService matchService = new MatchService();
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

       if (currentMatch.getPointsPlayerOne() == 40 && currentMatch.getPointsPlayerTwo() == 40) {
           resetPoints(currentMatch);
           currentMatch.setHandleDeuce(true);
           handleDeuce(currentMatch, player);
           return;
       }
        if (currentMatch.isHandleDeuce()) {
            handleDeuce(currentMatch, player);
        } else if (!currentMatch.isTeaBreak()) {
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
        } else {
            teaBreak(currentMatch, player);
        }
    }

    private void handleDeuce(CurrentMatch currentMatch, Player player) {
        if (currentMatch.isHandleDeuce()) {
            int newPoints = (player == Player.ONE)
                    ? currentMatch.getPointsPlayerOne() + 1
                    : currentMatch.getPointsPlayerTwo() + 1;

            updatePoints(currentMatch, player, newPoints);
        }

        if (currentMatch.getPointsPlayerOne() == 1 && currentMatch.getPointsPlayerTwo() == 1) {
            resetPoints(currentMatch);
        }
        if (currentMatch.getPointsPlayerOne() == 2 && currentMatch.getPointsPlayerTwo() == 0) {
            calculationGames(currentMatch, Player.ONE);
            currentMatch.setHandleDeuce(false);
            return;
        }
        if (currentMatch.getPointsPlayerOne() == 0 && currentMatch.getPointsPlayerTwo() == 2) {
            calculationGames(currentMatch, Player.TWO);
            currentMatch.setHandleDeuce(false);
        }
    }
    private void calculationGames(CurrentMatch currentMatch, Player player) {
        if (player == Player.ONE) {
            currentMatch.setGamesPlayerOne(currentMatch.getGamesPlayerOne() + 1);
            resetPoints(currentMatch);
            if (currentMatch.getGamesPlayerOne() == 6 && currentMatch.getGamesPlayerTwo() == 6) {
                currentMatch.setTeaBreak(true);
            }
            if (currentMatch.getGamesPlayerOne() == 6 && currentMatch.getGamesPlayerTwo() <= 4
                    || currentMatch.getGamesPlayerOne() == 7 && currentMatch.getGamesPlayerTwo() <= 5) {
                calculationSets(currentMatch, player);
            }
        } else if (player == Player.TWO) {
            currentMatch.setGamesPlayerTwo(currentMatch.getGamesPlayerTwo() + 1);
            resetPoints(currentMatch);
            if (currentMatch.getGamesPlayerOne() == 6 && currentMatch.getGamesPlayerTwo() == 6) {
                currentMatch.setTeaBreak(true);
            }
            if ((currentMatch.getGamesPlayerTwo() == 6 && currentMatch.getGamesPlayerOne() <= 4)
            || (currentMatch.getGamesPlayerTwo() == 7 && currentMatch.getGamesPlayerOne() <= 5)) {
                calculationSets(currentMatch, player);
            }
        }
    }

    private void teaBreak(CurrentMatch currentMatch, Player player) {
        int currentPoints = (player == Player.ONE)
                ? currentMatch.getPointsPlayerOne()
                : currentMatch.getPointsPlayerTwo();

        int newPoints = currentPoints + 1;
        updatePoints(currentMatch, player, newPoints);
        if (player == Player.ONE) {
            if (newPoints - currentMatch.getPointsPlayerTwo() >= 2 && newPoints >= 7) {
                calculationSets(currentMatch, player);
                resetPoints(currentMatch);
                currentMatch.setTeaBreak(false);
            }
        } else if (player == Player.TWO) {
            if (newPoints - currentMatch.getPointsPlayerOne() >= 2 && newPoints >=7) {
                calculationSets(currentMatch, player);
                resetPoints(currentMatch);
                currentMatch.setTeaBreak(false);
            }
        }
    }

    private void calculationSets(CurrentMatch currentMatch, Player player) {
        if (player == Player.ONE) {
            currentMatch.setSetsPlayerOne(currentMatch.getSetsPlayerOne() + 1);
            resetGames(currentMatch);
            if (currentMatch.getSetsPlayerOne() == 2 && currentMatch.getSetsPlayerTwo() <= 1) {
                matchService.saveMatchInBaseData(currentMatch.getIdPlayerOne(), currentMatch.getIdPlayerTwo(), currentMatch.getIdPlayerOne());
                matchService.removeMatch(activeMatch, currentMatch.getIdMatch());
            }
        } else {
            currentMatch.setSetsPlayerTwo(currentMatch.getSetsPlayerTwo() + 1);
            resetGames(currentMatch);
            if (currentMatch.getSetsPlayerTwo() == 2 && currentMatch.getSetsPlayerOne() <= 1) {
                matchService.saveMatchInBaseData(currentMatch.getIdPlayerOne(), currentMatch.getIdPlayerTwo(), currentMatch.getIdPlayerTwo());
                matchService.removeMatch(activeMatch, currentMatch.getIdMatch());
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