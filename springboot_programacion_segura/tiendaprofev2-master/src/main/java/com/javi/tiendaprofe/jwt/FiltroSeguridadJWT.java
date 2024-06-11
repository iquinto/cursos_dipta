package com.javi.tiendaprofe.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//NO PUEDO @Component
public class FiltroSeguridadJWT extends OncePerRequestFilter {
	
	private UserDetailsService userDetailsService;
	private UtilidadesJWT utilidadesJWT;

	public FiltroSeguridadJWT(UserDetailsService userDetailsService, UtilidadesJWT utilidadesJWT) {
		this.userDetailsService = userDetailsService;
		this.utilidadesJWT = utilidadesJWT;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String cabecera=request.getHeader("Authorization");
		
		
		String token=null;
		String login=null;
		if (cabecera!=null && cabecera.startsWith("Bearer")) {
			token=cabecera.substring(7);
			
			try {
				login=this.utilidadesJWT.getLogin(token);
				System.out.println("Parece que ha llegado un token válido: " + request.getServletPath());
			}
			catch (IllegalArgumentException e) {
				System.out.println("El token no es válido: " + request.getServletPath());
			}
			catch (ExpiredJwtException e) {
				System.out.println("El token ha caducado: " + request.getServletPath());
			}
		}
		else {
			System.out.println("No hay ningún token en la cabecera de petición: " + request.getServletPath());
		}
		
		if (token!=null && login!=null) {
			UserDetails usuario=this.userDetailsService.loadUserByUsername(login);
			UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(usuario.getUsername(), null, usuario.getAuthorities());	
			upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
			SecurityContextHolder.getContext().setAuthentication(upat);
		}
		
		 filterChain.doFilter(request, response); 
		
	}

}
