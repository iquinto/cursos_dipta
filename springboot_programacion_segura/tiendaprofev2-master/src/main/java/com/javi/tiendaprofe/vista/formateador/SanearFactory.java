package com.javi.tiendaprofe.vista.formateador;

import java.util.HashSet;
import java.util.Set;

import org.owasp.html.PolicyFactory;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

public class SanearFactory implements AnnotationFormatterFactory<Sanear>{

	private PolicyFactory sin;
	private PolicyFactory con;

	public SanearFactory(PolicyFactory sin, PolicyFactory con) {
		this.sin = sin;
		this.con = con;
	}

	@Override
	public Set<Class<?>> getFieldTypes() {
		Set<Class<?>> lista=new HashSet<>();
		lista.add(String.class);
		//lista.add(StringBuilder.class);
		return lista;
	}

	@Override
	public Printer<String> getPrinter(Sanear anotacion, Class<?> tipo) {
		return (texto, locale)->{
			if (anotacion.conFormato()) return this.con.sanitize(texto);
			else return sin.sanitize(texto);
		};
	}

	@Override
	public Parser<String> getParser(Sanear anotacion, Class<?> tipo) {
		return (texto, locale) -> {
			if (anotacion.conFormato()) return this.con.sanitize(texto);
			else return sin.sanitize(texto);
		};
	}

}
