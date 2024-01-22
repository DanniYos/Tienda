package org.dan.webapp.apiservlet.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.dan.webapp.apiservlet.headers.models.Usuario;
import org.dan.webapp.apiservlet.headers.services.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    @Inject
    private UsuarioService service;

    @Inject
    private LoginService auth;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> optionalUsername = auth.getUserName(req);
        if (optionalUsername.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("<meta charset = \"UTF-8\">");
                out.println("<title>Hola</title>");
                out.println("   </head>");
                out.println("<body>");
                out.println("<h1> Hola " + optionalUsername.get() + " has iniciado secion con exito</h1>");
                out.println("<p><a href='"+ req.getContextPath() +"/index.jsp'> volver </a> </p>");
                out.println("<p><a href='"+ req.getContextPath() +"/logaut'> Cerrar Sesion </a> </p>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            req.setAttribute("title", req.getAttribute("title")  + ": Login");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Optional<Usuario> usuarioOptional = service.login(username, password);
        if (usuarioOptional.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            try (PrintWriter out = resp.getWriter()) {
                resp.sendRedirect(req.getContextPath() + "/login.html");
            }
        } else {
//            resp.setStatus(401);
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta pagina");

        }

    }
}
