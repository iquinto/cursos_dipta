package com.javi.tiendaprofe.controlador.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javi.tiendaprofe.modelo.entidad.Tipo;
import com.javi.tiendaprofe.modelo.repositorio.RepositorioTipo;
import com.javi.tiendaprofe.vista.dto.TipoDTO;

@RestController
@RequestMapping({"/rest/tipos", "/otro/tipos"})
@CrossOrigin(origins = "https://miamigo.com", allowCredentials = "true")
public class ControladorRestTipo {
	
	@Autowired private RepositorioTipo rt;
	
	@GetMapping
	public ResponseEntity<List<TipoDTO>> leerTodos() {
		return new ResponseEntity<>(TipoDTO.convertirLista(this.rt.findAll()), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id:[0-9]+}")
	public ResponseEntity<TipoDTO> leerUno(@PathVariable("id") int id) {
		Optional<Tipo> caja= this.rt.findById(id);
		if (caja.isEmpty()) return ResponseEntity.notFound().build();
		return  ResponseEntity.ok(new TipoDTO(caja.get()));
	}
	
	
	@PostMapping
	public ResponseEntity<?> crear(@Validated @RequestBody TipoDTO dto, BindingResult estado) {
		if (estado.hasErrors()) return ResponseEntity.badRequest().build();
		
		if (rt.findById(dto.getId()).isPresent()) return ResponseEntity.badRequest().build();
		
		this.rt.save(dto.getEntidad());
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id:[0-9]+}")
	public ResponseEntity<?> borrar (@PathVariable("id") int id) {
		if (this.rt.findById(id).isEmpty()) return ResponseEntity.notFound().build();
		this.rt.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id:[0-9]+}")
	public ResponseEntity<?> modificar(@PathVariable("id") int id, @Validated @RequestBody TipoDTO dto, BindingResult estado) {
		if (estado.hasErrors()) return ResponseEntity.badRequest().build();
		
		Optional<Tipo> caja= this.rt.findById(id);
		if (caja.isEmpty()) return ResponseEntity.notFound().build();
		
		//chapuzas
		dto.setId(id);
		
		this.rt.save(dto.getEntidad());
		return ResponseEntity.noContent().build();
	}
}
