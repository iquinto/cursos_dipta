package com.javi.tiendaprofe.controlador;

import java.util.Optional;

import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.javi.tiendaprofe.modelo.entidad.Producto;
import com.javi.tiendaprofe.modelo.entidad.Tipo;
import com.javi.tiendaprofe.modelo.repositorio.RepositorioProducto;
import com.javi.tiendaprofe.modelo.repositorio.RepositorioTipo;
import com.javi.tiendaprofe.vista.dto.ProductoDTO;

@Controller
@EnableMethodSecurity(prePostEnabled = true /*, securedEnabled = true*/)
@RequestMapping("/producto")
public class ControladorProducto {
	@Autowired private RepositorioProducto rp;
	@Autowired private RepositorioTipo rt;
	
	//@Autowired  @Qualifier("Ninguna") private PolicyFactory saneador;
	
	@GetMapping("/ver")
	public ModelAndView ver() {
		ModelAndView mv=new ModelAndView("/WEB-INF/producto/ver.jsp");
		mv.addObject("productos",ProductoDTO.convertirLista(this.rp.findAll()));
		return mv;
	}
	
	@GetMapping("/crear")
	@PreAuthorize("hasRole('DIRECCION')")
	public ModelAndView crear(ModelAndView mv) {
		mv.setViewName("/WEB-INF/producto/crear.jsp");
		//es que sé que después voy a buscar la lista de tipos de producto
		return mv;
	}
	
	@PostMapping(value="/crear", params = {"referencia","nombre","precio","tipoId"})
	@PreAuthorize("#dto.precio > 100.0")
	//SE SUPONE que "@Param" sólo era necesario ANTES de Java 8! Con Maven no me pasa... Obviamente el ejecutable es distinto...
	public ModelAndView crear(@Param("dto") @Validated ProductoDTO dto, BindingResult estado) {
		ModelAndView mv=new ModelAndView();
		
		//dto.setNombre(this.saneador.sanitize(dto.getNombre()));
		
		try {
			if (estado.hasErrors()) throw new IllegalArgumentException();
			
			//Este bloque no sirve de nada EN ESTE CASO
			Optional<Tipo> otraCaja=this.rt.findById(dto.getTipoId());
			if (otraCaja.isEmpty()) throw new DataSourceLookupFailureException("El tipo no existe");
			dto.setTipoNombre(otraCaja.get().getNombre());
			
		 	Optional<Producto> caja=this.rp.findById(dto.getReferencia());
		 	if (caja.isPresent()) throw new DuplicateKeyException("Ese producto ya existe");
		 	
			this.rp.save(dto.getEntidad());
			mv.addObject("bien", true);
		}
		catch (DataAccessException | IllegalArgumentException e) {
			//LOGs que me lo cuentan todo
			mv.addObject("mal", true);
		}
		
		return this.crear(mv);
	}
	
	@GetMapping("/borrar")
	public String borrar() {
		return "/WEB-INF/producto/borrar.jsp";
	}

	@GetMapping("/modificar")
	public String modificar() {
		return "/WEB-INF/producto/modificar.jsp";
	}
}
