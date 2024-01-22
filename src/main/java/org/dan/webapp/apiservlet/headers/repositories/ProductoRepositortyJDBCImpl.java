package org.dan.webapp.apiservlet.headers.repositories;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.dan.webapp.apiservlet.headers.models.Categoria;
import org.dan.webapp.apiservlet.headers.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductoRepositortyJDBCImpl implements Repository<Producto> {
    @Inject
    @Named("conn")
    private Connection conn;

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement stms = conn.createStatement();
             ResultSet rs = stms.executeQuery("SELECT p.*, c.nombre AS categoria FROM productos AS p" +
                     " INNER JOIN categorias as c ON(p.id_categoria=c.id) ORDER BY p.id ASC")) {

            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement stms = conn.prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p " +
                "INNER JOIN categorias as c ON(p.id_categoria=c.id) WHERE p.id=? ORDER BY p.id ASC")) {
            stms.setLong(1, id);
            try (ResultSet rs = stms.executeQuery()) {
                while (rs.next()) {
                    producto = getProducto(rs);
                }
            }

        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos(nombre, precio, sku, id_categoria, fecha_registro) VALUES (?,?,?,?,?)";
        try (PreparedStatement stms = conn.prepareStatement(sql)) {
            stms.setString(1, producto.getNombre());
            stms.setInt(2, producto.getPrecio());
            stms.setString(3, producto.getSku());
            stms.setLong(4, producto.getCategoria().getId());
            stms.setDate(5, Date.valueOf(producto.getFechaIngreso()));
            stms.executeUpdate();
        }
    }

    @Override
    public void actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, sku= ?,  id_categoria = ? WHERE id = ?";
        try (PreparedStatement stms = conn.prepareStatement(sql)) {
            stms.setString(1, producto.getNombre());
            stms.setInt(2, producto.getPrecio());
            stms.setString(3, producto.getSku());
            stms.setLong(4, producto.getCategoria().getId());
            stms.setLong(5, producto.getId());
            stms.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id=?";
        try (PreparedStatement stms = conn.prepareStatement(sql)) {
            stms.setLong(1, id);
            stms.executeUpdate();
        }
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setSku(rs.getString("sku"));
        p.setFechaIngreso(rs.getDate("fecha_registro").toLocalDate());
        Categoria c = new Categoria();
        c.setId(rs.getLong("id_categoria"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);
        return p;
    }
}
