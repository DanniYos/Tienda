package org.dan.webapp.apiservlet.headers.services;

import org.dan.webapp.apiservlet.headers.models.Categoria;
import org.dan.webapp.apiservlet.headers.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> porId(Long id);

    void guardar(Producto producto);

    void actualizar(Producto producto);

    void eliminar(Long id);

    List<Categoria> listarCategoria();

    Optional<Categoria> porIdCategoria(Long id);
}
