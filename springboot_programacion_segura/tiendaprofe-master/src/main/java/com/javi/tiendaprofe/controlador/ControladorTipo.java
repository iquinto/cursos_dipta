package com.javi.tiendaprofe.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.javi.tiendaprofe.modelo.entidad.Tipo;
import com.javi.tiendaprofe.modelo.repositorio.RepositorioTipo;

@Controller
@RequestMapping("/tipo")
public class ControladorTipo {

	@Autowired private RepositorioTipo rt;
	
	//@RequestMapping(value="/tipo/ver", method = RequestMethod.GET)
	@GetMapping("/ver")
	public ModelAndView ver() {
		ModelAndView mv=new ModelAndView("/WEB-INF/tipo/ver.jsp");
		mv.addObject("tipos", this.rt.findAll());
		return mv;
	}
	
	@GetMapping("/crear")
	public String crear() {
		return "/WEB-INF/tipo/crear.jsp";
	}	
	
	@PostMapping(value="/crear", params = {"id","nombre"})
	public ModelAndView crear(Tipo tipo, BindingResult estado) {
		ModelAndView mv=new ModelAndView("/WEB-INF/tipo/crear.jsp");
		
		//tipo.setCosasSecretas(null);
	
		try {
			if (estado.hasErrors()) throw new IllegalArgumentException("Mmmm, hay alguien tocando las narices");
			this.rt.save(tipo);
			mv.addObject("bien", true);
		}
		catch (DataAccessException | IllegalArgumentException e) {
			//LOGS enormes y descriptivos
			mv.addObject("mal", true);
		}	
		return mv;
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
