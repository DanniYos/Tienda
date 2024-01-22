package org.dan.webapp.apiservlet.headers.services;

import org.dan.webapp.apiservlet.headers.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);
    List<Usuario> listar();
    void guardar(Usuario usuario);
    void actualizar(Usuario usuario);
    void eliminar(Long id);
    Optional<Usuario> porId(Long id);
}
