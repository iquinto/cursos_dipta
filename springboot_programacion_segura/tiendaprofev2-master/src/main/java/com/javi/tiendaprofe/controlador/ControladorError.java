package com.javi.tiendaprofe.controlador;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorError implements ErrorController {

	@GetMapping("/error")
	public String redirección() {
		return "redirect:/error/general";
	}
	
	
	@GetMapping("/error/general")
	public String laPáginadeError() {
		return "/WEB-INF/varios/errorgeneral.jsp";
	}
}
