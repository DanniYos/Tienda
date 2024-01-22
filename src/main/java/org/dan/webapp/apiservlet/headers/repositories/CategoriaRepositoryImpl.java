package org.dan.webapp.apiservlet.headers.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.dan.webapp.apiservlet.headers.configs.MysqlConn;
import org.dan.webapp.apiservlet.headers.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CategoriaRepositoryImpl implements Repository<Categoria> {

    private Connection conn;

    @Inject
    public CategoriaRepositoryImpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stms = conn.createStatement();
            ResultSet rs = stms.executeQuery("SELECT * FROM categorias")){
            while (rs.next()){
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }
        }

        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement stms = conn.prepareStatement("SELECT * FROM categorias AS c WHERE c.id = ?");){
            stms.setLong(1, id);
            try(ResultSet rs = stms.executeQuery()){
                if (rs.next()){
                    categoria = getCategoria(rs);
                }
            }
        }

        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void actualizar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setNombre(rs.getString("nombre"));
        categoria.setId(rs.getLong("id"));
        return categoria;
    }
}
