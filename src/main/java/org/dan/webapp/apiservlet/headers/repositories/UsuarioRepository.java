package org.dan.webapp.apiservlet.headers.repositories;

import org.dan.webapp.apiservlet.headers.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends Repository<Usuario>{
    Usuario porUsername(String username) throws SQLException;
}
