package org.dan.webapp.apiservlet.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dan.webapp.apiservlet.headers.models.Usuario;
import org.dan.webapp.apiservlet.headers.services.LoginService;
import org.dan.webapp.apiservlet.headers.services.LoginServiceSecionImpl;
import org.dan.webapp.apiservlet.headers.services.UsuarioService;
import org.dan.webapp.apiservlet.headers.services.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/usuarios")
public class UsuariosServlet extends HttpServlet {
    @Inject
    private UsuarioService service;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Usuario> usuarios = service.listar();
        LoginService loginService = new LoginServiceSecionImpl();
        Optional<String> usuarioOptional = loginService.getUserName(req);
        req.setAttribute("username", usuarioOptional);
        req.setAttribute("usuarios", usuarios);
        req.setAttribute("title", req.getAttribute("title")+ ": Usuarios");
        getServletContext().getRequestDispatcher("/usuarios.jsp").forward(req, resp);
    }
}
