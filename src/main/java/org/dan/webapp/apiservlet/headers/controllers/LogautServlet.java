package org.dan.webapp.apiservlet.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.dan.webapp.apiservlet.headers.services.LoginService;
import org.dan.webapp.apiservlet.headers.services.LoginServiceCookieImpl;
import org.dan.webapp.apiservlet.headers.services.LoginServiceSecionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logaut")
public class LogautServlet extends HttpServlet {

    @Inject
    private LoginService auth;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> username = auth.getUserName(req);
        if (username.isPresent()){
            HttpSession session = req.getSession();
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
