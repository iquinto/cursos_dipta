package com.javi.tiendaprofe.vista.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javi.tiendaprofe.modelo.entidad.Producto;
import com.javi.tiendaprofe.modelo.entidad.Tipo;
import com.javi.tiendaprofe.vista.formateador.Sanear;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ProductoDTO {
	@NotNull
	@Pattern(regexp = "^[A-Z]{2,3}[0-9]{2,3}$")
	private String referencia;
	
	@NotNull
	@Length(min=3, max = 40)
	@Sanear(conFormato = true)
	private String nombre;
	
	@NotNull
	@Range(min=0, max=9999)
	private double precio;
	
	@NotNull
	private Integer tipoId;
	private String tipoNombre;

	
	public ProductoDTO() {
	}
	
	public ProductoDTO(Producto p) {
		this.referencia=p.getReferencia();
		this.nombre=p.getNombre();
		this.precio=p.getPrecio();
		this.tipoId=p.getTipo().getId();
		this.tipoNombre=p.getTipo().getNombre();
	}
	
	@JsonIgnore
	public Producto getEntidad() {
		Tipo t=new Tipo(this.tipoId==null ? 0 : this.tipoId, this.tipoNombre);
		return new Producto(this.referencia, this.nombre, this.precio, t);
	}
	
	public static List<ProductoDTO> convertirLista(List<Producto> lista) {
		List<ProductoDTO> listaDTO=new ArrayList<>();
		lista.forEach(p->listaDTO.add(new ProductoDTO(p)));
		return listaDTO;
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

	public Integer getTipoId() {
		return tipoId;
	}

	public void setTipoId(Integer tipoId) {
		this.tipoId = tipoId;
	}

	public String getTipoNombre() {
		return tipoNombre;
	}

	public void setTipoNombre(String tipoNombre) {
		this.tipoNombre = tipoNombre;
	}

}
