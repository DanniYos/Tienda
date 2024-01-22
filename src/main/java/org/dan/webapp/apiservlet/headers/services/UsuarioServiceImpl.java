package org.dan.webapp.apiservlet.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.dan.webapp.apiservlet.headers.models.Usuario;
import org.dan.webapp.apiservlet.headers.repositories.Repository;
import org.dan.webapp.apiservlet.headers.repositories.UsuarioRepository;
import org.dan.webapp.apiservlet.headers.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService{
    @Inject
    private UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u-> u.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceJDBCExeption(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Usuario> listar() {
        try{
           return usuarioRepository.listar();
        }catch (SQLException e){
            throw new ServiceJDBCExeption(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Usuario usuario) {
        try {
            usuarioRepository.guardar(usuario);
        }catch (SQLException e){
            throw new ServiceJDBCExeption(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void actualizar(Usuario usuario) {
        try{
            usuarioRepository.actualizar(usuario);
        }catch (SQLException e){
            throw new ServiceJDBCExeption(e.getMessage(), e.getCause());
        }

    }

    @Override
    public void eliminar(Long id) {
        try {
            usuarioRepository.eliminar(id);
        }catch (SQLException e){
            throw new ServiceJDBCExeption(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        try{
            return Optional.ofNullable(usuarioRepository.porId(id));
        }catch (SQLException e){
            throw new ServiceJDBCExeption(e.getMessage(), e.getCause());
        }
    }

}
