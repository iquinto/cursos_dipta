package com.javi.tiendaprofe;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.javi.tiendaprofe.vista.formateador.SanearFactory;

@Configuration
public class ConfigurarWeb implements WebMvcConfigurer{
	
	 @Bean 
	 @Qualifier("Ninguna")
	 PolicyFactory sanitizerSin() { 
      System.out.println("------> El método se ejecuta");
	  return new HtmlPolicyBuilder().toFactory(); 
	 } 
	  
	 @Bean 
	 @Qualifier("Formato")
	 PolicyFactory sanitizerCon() { 
	  return Sanitizers.FORMATTING; 
	 } 
	 
	@Override
	public void addFormatters(FormatterRegistry r) {
		
		/*System.out.println("-------- el registrador de formateadores comienza");
		
		PolicyFactory eso=sanitizerSin();
		System.out.println(eso);
	
		eso=sanitizerSin();
		System.out.println(eso);
	
		eso=sanitizerSin();
		System.out.println(eso);
	
		eso=sanitizerSin();
		System.out.println(eso);*/
		
		r.addFormatterForFieldAnnotation(new SanearFactory(sanitizerSin(), sanitizerCon()));
	}

	@Override
	public void addViewControllers(ViewControllerRegistry r) {
		r.addViewController("/").setViewName("redirect:/entrada/inicio");
		r.addViewController("/entrada/inicio").setViewName("/WEB-INF/entrada/inicio.jsp");
		r.addViewController("/entrada/login").setViewName( "/WEB-INF/entrada/login.jsp");
	}
}
