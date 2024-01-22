package org.dan.webapp.apiservlet.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dan.webapp.apiservlet.headers.services.UsuarioService;
import org.dan.webapp.apiservlet.headers.services.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/usuario/eliminar")
public class UsuarioEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0;
        }

        if (id > 0 ){
            service.eliminar(id);
        }
        resp.sendRedirect(req.getContextPath() + "/usuarios");
    }
}
