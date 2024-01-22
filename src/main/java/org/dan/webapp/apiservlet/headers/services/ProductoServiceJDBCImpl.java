package org.dan.webapp.apiservlet.headers.services;

import jakarta.faces.flow.builder.ReturnBuilder;
import org.dan.webapp.apiservlet.headers.models.Categoria;
import org.dan.webapp.apiservlet.headers.models.Producto;
import org.dan.webapp.apiservlet.headers.repositories.CategoriaRepositoryImpl;
import org.dan.webapp.apiservlet.headers.repositories.ProductoRepositortyJDBCImpl;
import org.dan.webapp.apiservlet.headers.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJDBCImpl implements ProductoService{

    private Repository<Producto> productoRepository;
    private Repository<Categoria> categoriaRepository;

    public ProductoServiceJDBCImpl(Connection conn) {
        this.productoRepository = new ProductoRepositortyJDBCImpl(conn);
        this.categoriaRepository = new CategoriaRepositoryImpl(conn);
    }

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
