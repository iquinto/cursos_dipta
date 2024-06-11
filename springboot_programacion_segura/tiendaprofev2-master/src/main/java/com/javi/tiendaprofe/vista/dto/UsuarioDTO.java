package com.javi.tiendaprofe.vista.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class UsuarioDTO {
	@NotBlank
	@Length(min = 3, max = 50)
	private String login;
	
	@NotBlank
	@Length(min = 5, max = 80)
	private String clave;
	
	public UsuarioDTO() {
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
}
