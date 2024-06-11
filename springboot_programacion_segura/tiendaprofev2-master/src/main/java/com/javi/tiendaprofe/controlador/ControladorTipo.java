package com.javi.tiendaprofe.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.javi.tiendaprofe.modelo.repositorio.RepositorioTipo;
import com.javi.tiendaprofe.vista.dto.ProductoDTO;
import com.javi.tiendaprofe.vista.dto.TipoDTO;

@Controller
@RequestMapping("/tipo")
public class ControladorTipo {

	@Autowired private RepositorioTipo rt;
	
	//@RequestMapping(value="/tipo/ver", method = RequestMethod.GET)
	@GetMapping("/ver")
	public ModelAndView ver() {
		ModelAndView mv=new ModelAndView("/WEB-INF/tipo/ver.jsp");
		mv.addObject("tipos",TipoDTO.convertirLista(this.rt.findAll()));
		return mv;
	}
	
	@GetMapping("/crear")
	public String crear() {
		return "/WEB-INF/tipo/crear.jsp";
	}	
	
/*	@PostMapping(value="/crear", params = {"id","nombre"})
	public ModelAndView crear(@Validated TipoDTO dto, BindingResult estado) {
		ModelAndView mv=new ModelAndView("/WEB-INF/tipo/crear.jsp");
		
		try {
			if (estado.hasErrors()) throw new IllegalArgumentException("Mmmm, hay alguien tocando las narices");
			this.rt.save(dto.getEntidad());
			mv.addObject("bien", true);
		}
		catch (DataAccessException | IllegalArgumentException e) {
			//LOGS enormes y descriptivos
			mv.addObject("mal", true);
		}	
		return mv;
	}
	*/
	
	@PostMapping(value="/crear")
	//@ResponseBody
	public ResponseEntity<?> crear(@Validated @RequestBody TipoDTO dto, BindingResult estado) {
		if (estado.hasErrors()) return ResponseEntity.badRequest().build(); //400
		try {
			//SEGUIR AQUÍ
			if (this.rt.findById(dto.getId()).isPresent())  return ResponseEntity.badRequest().build(); //400. Antes era un 500...
			this.rt.save(dto.getEntidad());
			return ResponseEntity.noContent().build(); //204
		}
		catch (DataAccessException e) {
			return ResponseEntity.internalServerError().build(); //500
		}
	}
	
	@GetMapping("/borrar")
	public String borrar() {
		return "/WEB-INF/tipo/borrar.jsp";
	}

	
	@GetMapping("/modificar")
	public String modificar() {
		return "/WEB-INF/tipo/modificar.jsp";
	}


}
