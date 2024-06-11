package com.javi.tiendaprofe.modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.javi.tiendaprofe.modelo.entidad.Producto;
import com.javi.tiendaprofe.modelo.entidad.Tipo;
import com.javi.tiendaprofe.modelo.repositorio.RepositorioProducto;
import com.javi.tiendaprofe.modelo.repositorio.RepositorioTipo;

@Component
public class DatosIniciales implements ApplicationRunner{
	
	@Autowired private RepositorioTipo rt;
	@Autowired private RepositorioProducto rp;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Tipo comida=this.rt.save(new Tipo(1,"Comida"));
		Tipo juguete=this.rt.save(new Tipo(2,"Juguetes"));
		Tipo bicho=this.rt.save(new Tipo(3,"Bichos"));
		
		this.rp.save(new Producto("GA01", "Peluchín", 20, bicho));
		this.rp.save(new Producto("GA02", "Patitas de terciopelo", 23, bicho));
		this.rp.save(new Producto("GA03", "Orejitas", 12, bicho));
		this.rp.save(new Producto("JU01", "Rascador 100cm", 50, juguete));
		
		
		for (Tipo t:this.rt.findAll())
			System.out.println(t.getId() + " --- " + t.getNombre());
		
		for (Producto p:this.rp.findAll())
			System.out.println(p.getNombre() + " --- " + p.getTipo().getNombre());
		
	}

}
