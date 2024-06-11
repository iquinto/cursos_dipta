package com.javi.tiendaprofe.modelo.entidad;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Producto implements Serializable{
	@Id
	private String referencia;
	private String nombre;
	private double precio;
	
	@ManyToOne
	private Tipo tipo;
	
	public Producto() {
	}

	public Producto(String referencia, String nombre, double precio, Tipo tipo) {
		this.referencia = referencia;
		this.nombre = nombre;
		this.precio = precio;
		this.tipo = tipo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
}
