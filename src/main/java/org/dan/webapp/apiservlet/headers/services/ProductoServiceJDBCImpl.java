package org.dan.webapp.apiservlet.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dan.webapp.apiservlet.headers.models.Categoria;
import org.dan.webapp.apiservlet.headers.models.Producto;
import org.dan.webapp.apiservlet.headers.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductoServiceJDBCImpl implements ProductoService{

    @Inject
    private Repository<Producto> productoRepository;
    @Inject
    private Repository<Categoria> categoriaRepository;

    @Override
    public List<Producto> listar() {
        try {
            return productoRepository.listar();
        } catch (SQLException e) {
            throw new ServiceJDBCExeption(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(productoRepository.porId(id));
        } catch (SQLException e) {
            throw new ServiceJDBCExeption(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            productoRepository.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceJDBCExeption(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void actualizar(Producto producto) {
        try{
            productoRepository.actualizar(producto);
        }catch (SQLException e){
            throw new ServiceJDBCExeption(e.getMessage(), e.getCause());
        }
    }


    @Override
    public void eliminar(Long id) {
        try {
            productoRepository.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceJDBCExeption(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return categoriaRepository.listar();
        } catch (SQLException e) {
            throw new ServiceJDBCExeption(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(categoriaRepository.porId(id));
        } catch (SQLException e) {
            throw new ServiceJDBCExeption(e.getMessage(), e.getCause());
        }
    }

}
