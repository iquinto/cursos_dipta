package com.javi.tiendaprofe.controlador.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.javi.tiendaprofe.jwt.UtilidadesJWT;
import com.javi.tiendaprofe.vista.dto.UsuarioDTO;

@Controller
public class ControladorJWT {
	
	@Autowired
	private UtilidadesJWT utilidadesJWT;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/identificarme")
	public ResponseEntity<?> identificarme(@Validated @RequestBody UsuarioDTO dto, BindingResult estado) {
		if (estado.hasErrors()) ResponseEntity.badRequest().build();
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getClave()));
		UserDetails usuario=this.userDetailsService.loadUserByUsername(dto.getLogin());
		
		//y ahora devuelvo el token
		return ResponseEntity.ok(new Object() {
			public String token=utilidadesJWT.generarToken(usuario);
		});
		
	}
}
