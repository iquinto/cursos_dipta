package com.javi.tiendaprofe.modelo.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Tipo implements Serializable {
	
	@Id
	private int id;
	private String nombre;
	private String cosasSecretas;
	
	@OneToMany(mappedBy = "tipo")
	private List<Producto> productos=new ArrayList<>();
	
	public Tipo() {
	}
	
	public Tipo(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public List<Producto> getProductos() {
		return productos;
	}
	
	public void addProducto(Producto p) {
		this.productos.add(p);
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

	public String getCosasSecretas() {
		return cosasSecretas;
	}

	public void setCosasSecretas(String cosasSecretas) {
		this.cosasSecretas = cosasSecretas;
	}


}
