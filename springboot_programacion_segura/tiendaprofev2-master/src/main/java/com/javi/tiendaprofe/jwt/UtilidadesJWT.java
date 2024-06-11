package com.javi.tiendaprofe.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class UtilidadesJWT {

	@Value("${clave.secreta}")
	private String textoClaveSecreta;
	
	private SecretKey secretKey;
	
	@PostConstruct
	public void iniciarTodo() {
		this.secretKey=Keys.hmacShaKeyFor(this.textoClaveSecreta.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generarToken(UserDetails ud) {
		return Jwts.builder()
				.claims()
				.subject(ud.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 3600000))
				.and()
				.signWith(secretKey)
				.compact();	
	}
	
	public Claims getClaims(String token) {
		return Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public String getLogin(String token) {
		Claims claims=this.getClaims(token);
		return claims.getSubject();
	}
	
	public Date getFechaCaducidad(String token) {
		Claims claims=this.getClaims(token);
		return claims.getExpiration();
	}
	
	
}
