package org.dan.webapp.apiservlet.headers.models;

import java.time.LocalDate;

public class Producto {
    private Long id;
    private String nombre;
    private Categoria categoria;
    private int precio;
    private String sku;
    private LocalDate fechaIngreso;

    public Producto(){

    }

    public Producto(Long id, String nombre,String tipo, int precio) {
        this.id = id;
        this.nombre = nombre;
        Categoria category = new Categoria();
        category.setNombre(tipo);
        this.categoria = category;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}