package tennisscoreboard.example.tennisscoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tennisscoreboard.example.tennisscoreboard.model.Player;
import tennisscoreboard.example.tennisscoreboard.service.CurrentMatch;
import tennisscoreboard.example.tennisscoreboard.service.MatchService;
import tennisscoreboard.example.tennisscoreboard.service.PlayerService;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatch extends HttpServlet {
    PlayerService playerService = new PlayerService();
    MatchService matchService = new MatchService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String namePlayer1 = req.getParameter("playerOne");
        String namePlayer2 = req.getParameter("playerTwo");

        Player playerOne = playerService.findOrCreatePlayer(namePlayer1);
        Player playerTwo = playerService.findOrCreatePlayer(namePlayer2);

        CurrentMatch currentMatch = matchService.startMatch(playerOne.getId(), playerTwo.getId());

        HttpSession session = req.getSession();
        session.setAttribute("playerOne", namePlayer1);
        session.setAttribute("playerTwo", namePlayer2);

        resp.sendRedirect("/match-score?id=" + currentMatch.getIdMatch());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }
}
