package tennisscoreboard.example.tennisscoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tennisscoreboard.example.tennisscoreboard.dto.MatchDTO;
import tennisscoreboard.example.tennisscoreboard.service.MatchService;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class Matches extends HttpServlet {
    MatchService matchService = new MatchService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
        int totalPage = matchService.getMatchesCountService(5);
        List<MatchDTO> listMatch = matchService.getMatchesPageService(page, 5);

        HttpSession session = req.getSession();
        session.setAttribute("listMatch", listMatch);
        System.out.println("Session listMatch set: " + session.getAttribute("listMatch"));

        session.setAttribute("page", page);
        session.setAttribute("totalPage", totalPage);
        System.out.println("Матчи: " + listMatch);

        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
