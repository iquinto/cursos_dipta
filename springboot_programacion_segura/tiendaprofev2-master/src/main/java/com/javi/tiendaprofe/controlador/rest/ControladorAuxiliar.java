package com.javi.tiendaprofe.controlador.rest;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControladorAuxiliar {
	
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void errorDeBaseDeDatos(DataAccessException ex) {
		//LOGS --> ex
	}
	
	@ExceptionHandler(IllegalAccessException.class)
	public ResponseEntity<?> errorPorCulpaDelCliente(IllegalAccessException ex) {
		//LOGS --> ex
		//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return ResponseEntity.badRequest().build();
	}
}
