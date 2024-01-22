package org.dan.webapp.apiservlet.headers.repositories;

import org.dan.webapp.apiservlet.headers.models.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioRepository extends Repository<Usuario>{
    Usuario porUsername(String username) throws SQLException;
}
