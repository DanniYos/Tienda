package org.dan.webapp.apiservlet.headers.controllers;

import jakarta.inject.Inject;
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
    @Inject
    private UsuarioService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
