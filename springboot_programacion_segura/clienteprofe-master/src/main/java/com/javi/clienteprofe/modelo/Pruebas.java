package com.javi.clienteprofe.modelo;


import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.javi.clienteprofe.vista.dto.TipoDTO;
import com.javi.clienteprofe.vista.dto.TokenDTO;
import com.javi.clienteprofe.vista.dto.UsuarioDTO;

@Component
public class Pruebas implements ApplicationRunner{
	@Autowired private RestTemplate rt;
	
	private HttpHeaders crearCabeceras(String login, String clave) {
		
		byte[] elArray=Base64.getEncoder().encode((login + ":" + clave).getBytes(Charset.forName("US-ASCII")));
		
		HttpHeaders cabeceras=new HttpHeaders();
		cabeceras.add("Authorization", "Basic " + new String(elArray));
		return cabeceras;
	}	
	
	private HttpHeaders crearCabecerasToken(String token) {
		HttpHeaders cabeceras=new HttpHeaders();
		cabeceras.add("Authorization", "Bearer " + token);
		return cabeceras;
	}
	
	private void ejemploLectura() {
		String url_base="https://localhost:8443/tiendaprofev2/rest/tipos";
		
		try {
			//ResponseEntity<TipoDTO> re=rt.getForEntity(url_base + "/{id}",TipoDTO.class,2);	
			
			ResponseEntity<TipoDTO> re=rt.exchange(url_base + "/{id}", 
					                               HttpMethod.GET,
					                               new HttpEntity(crearCabeceras("mari","clavemari")), 
					                               TipoDTO.class, 2);
			
			TipoDTO dto=re.getBody();
			System.out.println(dto);
		}
		catch (RestClientException e) {
			System.out.println("--------------------------------");
			e.printStackTrace();
		}
	}
	
	private void ejemploEscritura() {
		String url_base="https://localhost:8443/tiendaprofev2/rest/tipos";
		TipoDTO tipo=new TipoDTO(33,"Es una prueba de POST");
		try {
			ResponseEntity<?> re=rt.exchange(url_base, HttpMethod.POST, new HttpEntity(tipo, crearCabeceras("mari","clavemari")), TipoDTO.class);
			System.out.println("Parece que se ha creado");
		}
		catch (Exception e) {
			System.out.println("--------------------------------");
			e.printStackTrace();
		}
	}
	
	/* Esta cutrez sólo es un ejemplo. Ni las claves las plantas en el programa, ni pides el token una y otro y otra vez */
	private String getToken(String login, String clave) {
		String url_base="https://localhost:8443/tiendaprofev2/identificarme";
		UsuarioDTO dto=new UsuarioDTO(login, clave);
		try {
			ResponseEntity<TokenDTO> re= rt.postForEntity(url_base, dto, TokenDTO.class);
			return re.getBody().getToken();
		}
		catch (Exception e) {
			System.out.println("--------------------------------");
			e.printStackTrace();
			return null;
		}
	}
	
	
	private void ejemploLecturaToken(String token) {
		String url_base="https://localhost:8443/tiendaprofev2/rest/tipos";
		
		try {
			ResponseEntity<TipoDTO> re=rt.exchange(url_base + "/{id}", 
					                               HttpMethod.GET,
					                               new HttpEntity(crearCabecerasToken(token)), 
					                               TipoDTO.class, 2);
			
			TipoDTO dto=re.getBody();
			System.out.println(dto);
		}
		catch (RestClientException e) {
			System.out.println("--------------------------------");
			e.printStackTrace();
		}
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(new String(Base64.getDecoder().decode("bWFyaTpjbGF2ZW1hcmk=")));
		
		System.out.println("Esos son de autentificación Básica");
		this.ejemploLectura();
		this.ejemploEscritura();
		System.out.println("Y ahora, los tokens");
		String token=this.getToken("mari", "clavemari");
		System.out.println(token);
		this.ejemploLecturaToken(token);
		
		
				
	}

}
