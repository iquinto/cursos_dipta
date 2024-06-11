package com.javi.tiendaprofe.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javi.tiendaprofe.modelo.entidad.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, String> {

}
