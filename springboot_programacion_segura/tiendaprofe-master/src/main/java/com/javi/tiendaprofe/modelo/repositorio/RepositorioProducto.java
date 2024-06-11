package com.javi.tiendaprofe.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javi.tiendaprofe.modelo.entidad.Producto;

public interface RepositorioProducto extends JpaRepository<Producto, String> {

}
