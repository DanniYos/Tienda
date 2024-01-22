package org.dan.webapp.apiservlet.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dan.webapp.apiservlet.headers.models.Usuario;
import org.dan.webapp.apiservlet.headers.services.UsuarioService;
import org.dan.webapp.apiservlet.headers.services.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuario/form")
public class UsuarioFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0;
        }
        Usuario usuario = new Usuario();
        Optional<Usuario> o =  service.porId(id);
        if (o.isPresent()){
            usuario = o.get();
        }
        req.setAttribute("usuario", usuario);
        getServletContext().getRequestDispatcher("/usuarioForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);
        Map<String, String> errores = new HashMap<>();
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0;
        }
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        if (user == null || user.isBlank()){
            errores.put("usuario", "El campo usuario es requerido");
        }

        if (password == null || password.isBlank()){
            errores.put("password", "La contraseña es requerida");
        }

        if (email == null || email.isBlank()){
            errores.put("email", "El email es requerido");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(user);
        usuario.setPassword(password);
        usuario.setEmail(email);
        if (errores.isEmpty()) {
            if (id > 0) {
                usuario.setId(id);
                service.actualizar(usuario);
            } else {
                service.guardar(usuario);
            }
            resp.sendRedirect(req.getContextPath() + "/usuarios");
        }else {
            req.setAttribute("usuario", usuario);
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/usuarioForm.jsp").forward(req, resp);
        }
    }
}
