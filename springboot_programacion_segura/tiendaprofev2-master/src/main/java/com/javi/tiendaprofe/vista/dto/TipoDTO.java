package com.javi.tiendaprofe.vista.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javi.tiendaprofe.modelo.entidad.Tipo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TipoDTO {
	
	@NotNull
	@Min(1)
	private Integer id;
	
	@NotNull
	@Length(min = 3, max = 50)
	private String nombre;
	
	public TipoDTO(Tipo entidad) {
		this.id=entidad.getId();
		this.nombre=entidad.getNombre();
	}
	
	public TipoDTO() {
	}
	
	public static List<TipoDTO> convertirLista(List<Tipo> lista) {
		List<TipoDTO> listaDTO=new ArrayList<>();
		//lista.forEach(tipo->listaDTO.add(new TipoDTO(tipo)));
		
		for(Tipo tipo:lista)
			listaDTO.add(new TipoDTO(tipo));
		
		return listaDTO;
	}
	
	@JsonIgnore
	public Tipo getEntidad() {
		return new Tipo(this.id, this.nombre);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
