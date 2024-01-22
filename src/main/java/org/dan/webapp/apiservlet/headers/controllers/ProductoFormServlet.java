package org.dan.webapp.apiservlet.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dan.webapp.apiservlet.headers.models.Categoria;
import org.dan.webapp.apiservlet.headers.models.Producto;
import org.dan.webapp.apiservlet.headers.services.ProductoService;
import org.dan.webapp.apiservlet.headers.services.ProductoServiceImpl;
import org.dan.webapp.apiservlet.headers.services.ProductoServiceJDBCImpl;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJDBCImpl(conn);
        List<Categoria> categorias = service.listarCategoria();
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        if (id > 0) {
            Optional<Producto> o = service.porId(id);
            if (o.isPresent()) {
                producto = o.get();
            }
        }
        req.setAttribute("categorias", categorias);
        req.setAttribute("producto", producto);
        req.setAttribute("title", req.getAttribute("title") + ": Formulario de Productos");
        req.getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    //En el dogetEstamos guardando todo el objeto categoria
    //en el dopost solo hacemos la relacion
    //Por el campo value, al seleccionar la categoria en el jsp
    //se obtiene el id de la categoria y de esa forma se relacion
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJDBCImpl(conn);
        String nombre = req.getParameter("nombre");
        Integer precio;
        try {
            precio = Integer.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0;
        }
        String sku = req.getParameter("sku");
        String fechaString = req.getParameter("fecha_registro");
        Long categoriaId;
        try {
            categoriaId = Long.valueOf(req.getParameter("categoria"));
        } catch (NumberFormatException e) {
            categoriaId = 0L;
        }

        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre no puede ser vacio");
        }

        if (sku == null || sku.isBlank()) {
            errores.put("sku", "El sku no puede ser vacio");
        }

        if (fechaString == null || fechaString.isBlank()) {
            errores.put("fecha", "La fecha es requerida");
        }

        if (precio.equals(0)) {
            errores.put("precio", "El precio es requerido");
        }

        if (categoriaId.equals(0L)) {
            errores.put("categoria", "La categoria es requerida");
        }

        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e){
            fecha = null;
        }
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setSku(sku);
        producto.setFechaIngreso(fecha);
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        producto.setCategoria(categoria);
        if (errores.isEmpty()) {
            if (id > 0){
                producto.setId(id);
                service.actualizar(producto);
            }
            if (producto.getId() == null || producto.getId() == 0){
                service.guardar(producto);
            }
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listarCategoria());
            req.setAttribute("producto", producto);
            req.setAttribute("title", req.getAttribute("title")  + ": Formulario de productos");
            req.getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
