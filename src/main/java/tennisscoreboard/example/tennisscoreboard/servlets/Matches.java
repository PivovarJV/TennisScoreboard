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
        String namePlayer = req.getParameter("filter_by_player_name");
        int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
        int totalPage = matchService.getMatchesCountService(5);
        List<MatchDTO> listMatch = List.of();
        try {
            if (namePlayer != null && !namePlayer.isBlank()) {
                listMatch = matchService.getMatchesListByNameService(namePlayer, page, 5);
                System.out.println(listMatch);
            } else {
                listMatch = matchService.getMatchesPageService(page, 5);
            }
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
        } catch (RuntimeException e) {
            req.setAttribute("error", e.getMessage());
        }

        HttpSession session = req.getSession();
        session.setAttribute("listMatch", listMatch);
        session.setAttribute("page", page);
        session.setAttribute("totalPage", totalPage);
        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
