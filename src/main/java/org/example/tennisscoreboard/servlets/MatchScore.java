package org.example.tennisscoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.tennisscoreboard.service.CurrentMatch;
import org.example.tennisscoreboard.service.MatchService;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScore extends HttpServlet {
    MatchService matchService = new MatchService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idMatch = req.getParameter("id");
        CurrentMatch currentMatch = matchService.getOngoingMatchById(idMatch);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }
}
