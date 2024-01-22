package org.dan.webapp.apiservlet.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.dan.webapp.apiservlet.headers.models.Carro;
import org.dan.webapp.apiservlet.headers.models.ItemCarro;
import org.dan.webapp.apiservlet.headers.models.Producto;
import org.dan.webapp.apiservlet.headers.services.ProductoService;
import org.dan.webapp.apiservlet.headers.services.ProductoServiceJDBCImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {
    @Inject
    private Carro carro;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJDBCImpl(conn);
        Optional<Producto> producto = service.porId(id);
        if (producto.isPresent()) {
            ItemCarro item = new ItemCarro(1, producto.get());
            carro.addItemsCarro(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
