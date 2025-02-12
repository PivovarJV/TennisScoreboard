package tennisscoreboard.example.tennisscoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tennisscoreboard.example.tennisscoreboard.service.CurrentMatch;
import tennisscoreboard.example.tennisscoreboard.service.MatchScoreCalculationService;
import tennisscoreboard.example.tennisscoreboard.service.MatchService;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScore extends HttpServlet {
    MatchService matchService = new MatchService();
    MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idMatch = req.getParameter("id");
        CurrentMatch currentMatch = matchService.getOngoingMatchById(idMatch);
        String player = req.getParameter("playerId");
        matchScoreCalculationService.calculationService(player, currentMatch);

        HttpSession session = req.getSession();
        String nameOnePlayer = (String) session.getAttribute("playerOne");
        String nameTwoPlayer = (String) session.getAttribute("playerTwo");
        String winner = matchService.whoWinner(currentMatch, nameOnePlayer, nameTwoPlayer);
        if (winner!= null) {
            session.setAttribute("winner", winner);
        }

        session.setAttribute("pointsOnePlayer", currentMatch.getPointsPlayerOne());
        session.setAttribute("gamesOnePlayer", currentMatch.getGamesPlayerOne());
        session.setAttribute("setsOnePlayer", currentMatch.getSetsPlayerOne());

        session.setAttribute("pointsTwoPlayer", currentMatch.getPointsPlayerTwo());
        session.setAttribute("gamesTwoPlayer", currentMatch.getGamesPlayerTwo());
        session.setAttribute("setsTwoPlayer", currentMatch.getSetsPlayerTwo());

        resp.sendRedirect("/match-score?id=" + idMatch);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }
}
