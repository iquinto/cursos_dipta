package com.javi.tiendaprofe.modelo.entidad;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

@Entity
public class Usuario implements Serializable {
	public enum Grupo {EMPLEADO, DIRECCION, CLIENTE};
	
	@Id
	public String login;
	public String clave;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	public Set<Grupo> grupos=new HashSet<>();
	
	public Usuario() {
	}
	
	public Usuario(String login, String clave, Grupo ... grupos) {
		this.login = login;
		this.clave = clave;
		for (Grupo g:grupos)
			this.grupos.add(g);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Set<Grupo> getGrupos() {
		return grupos;
	}

//	public void setGrupos(Set<Grupo> grupos) {
//		this.grupos = grupos;
//	}
	
	
	public boolean addGrupo(Grupo grupo) {
		return this.grupos.add(grupo);
	}
	
	
}
