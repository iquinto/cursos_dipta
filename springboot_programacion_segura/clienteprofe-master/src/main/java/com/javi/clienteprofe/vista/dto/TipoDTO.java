package com.javi.clienteprofe.vista.dto;

public class TipoDTO {
	private int id;
	private String nombre;
	
	public TipoDTO() {
	}

	public TipoDTO(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return String.format("Tipo %d - %s", id, nombre);
	}
	
	
}
