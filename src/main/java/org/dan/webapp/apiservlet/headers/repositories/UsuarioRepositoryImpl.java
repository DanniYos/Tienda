package org.dan.webapp.apiservlet.headers.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dan.webapp.apiservlet.headers.configs.MysqlConn;
import org.dan.webapp.apiservlet.headers.configs.Repositorio;
import org.dan.webapp.apiservlet.headers.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repositorio
public class UsuarioRepositoryImpl implements UsuarioRepository{

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try(Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM usuarios")){
            while (rs.next()){
                Usuario u = getUsuario(rs);
                usuarios.add(u);
            }
        }
        return usuarios;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario usuario = null;
        try(PreparedStatement stms = conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?")){
            stms.setLong(1, id);
            try (ResultSet rs = stms.executeQuery()){
                while (rs.next()){
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios VALUES(DEFAULT,?,?,?)";
        try (PreparedStatement stms = conn.prepareStatement(sql)){
            stms.setString(1, usuario.getUsername());
            stms.setString(2,usuario.getPassword());
            stms.setString(3, usuario.getEmail());
            stms.executeUpdate();
        }
    }

    @Override
    public void actualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET user=?, password=?, email=? WHERE id=?";
        try (PreparedStatement stms = conn.prepareStatement(sql)){
            stms.setString(1, usuario.getUsername());
            stms.setString(2,usuario.getPassword());
            stms.setString(3, usuario.getEmail());
            stms.setLong(4, usuario.getId());
            stms.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try(PreparedStatement stms = conn.prepareStatement(sql)) {
            stms.setLong(1, id);
            stms.executeUpdate();
        }
    }

    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE user = ?")){
            stmt.setString(1, username);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setUsername(rs.getString("user"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setUsername(rs.getString("email"));
                }
            }
        }

        return usuario;
    }

    private static Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getLong("id"));
        u.setUsername(rs.getString("user"));
        u.setPassword(rs.getString("password"));
        u.setEmail(rs.getString("email"));
        return u;
    }
}
