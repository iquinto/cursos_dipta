package com.javi.tiendaprofe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.javi.tiendaprofe.jwt.FiltroSeguridadJWT;
import com.javi.tiendaprofe.jwt.UtilidadesJWT;

@Configuration
public class ConfigurarSeguridad {
	
	@Autowired
	private UtilidadesJWT utilidadesJWT;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean 
	AuthenticationManager authenticationManager(AuthenticationConfiguration conf)  throws Exception { 
	    return conf.getAuthenticationManager(); 
	}
	
	@Bean
	PasswordEncoder codificador() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	
/*	@Bean
	UserDetailsService usuarios(PasswordEncoder pe) {
		
		//aunque sea para pruebas, acostúmbrate a usar hash por todos lados
//		System.out.println(pe.encode("clavejavi"));
//		System.out.println(pe.encode("clavemari"));
//		
//		 UserDetails javi=User.withUsername("javi").password(pe.encode("clavejavi")).roles("ADMINISTRADOR", "EMPLEADO").build();
//		 UserDetails mari=User.withUsername("mari").password(pe.encode("clavemari")).roles("EMPLEADO").roles("DIRECCION").authorities("LECTURA").build();
		 
		 UserDetails javi=User.withUsername("javi")
				 .password("{bcrypt}$2a$10$ix7LeUACju4Jt93CMxQXTulLwIzz.tdMx3uygL1hCBBLkSNHYuXbe")
				 .roles("ADMINISTRADOR", "EMPLEADO")
				 .build();
		 UserDetails mari=User.withUsername("mari")
				 .password("{bcrypt}$2a$10$Y4nnLBL5TnokcxpyGD3vnOfuZRgWgctXgM9AevFakLW5JQBxZht.O")
				 .authorities("ROLE_EMPLEADO", "ROLE_DIRECCION", "LECTURA")
				 .build();
		 UserDetails luis=User.withUsername("luis")
				 .password("{noop}claveluis")
				 .authorities("ROLE_EMPLEADO", "ROLE_DIRECCION", "LECTURA")
				 .build();
		 
		 InMemoryUserDetailsManager usuarios=new InMemoryUserDetailsManager();
		 usuarios.createUser(mari);
		 usuarios.createUser(javi);
		 usuarios.createUser(luis);
		 
		 return usuarios;
	}*/
	

	@Bean
	SecurityFilterChain casiTodaLaSeguridad(HttpSecurity http) throws Exception {
		//http.requiresChannel().anyRequest().requiresSecure();
		
		//por si me hacen peticiones verificadas y estoy autentificando con cookie de sesión
		//options no tiene cookies
		http.cors(c->{});
		
		//HTTPS
		http.requiresChannel(canal->canal.anyRequest().requiresSecure());
		
		http.httpBasic(Customizer.withDefaults());
		
		http.csrf(c->c
				.ignoringRequestMatchers(new AntPathRequestMatcher("/identificarme", "POST"))
				.ignoringRequestMatchers("/rest/tipos/**"));
		
		//AUTORIZACIÓN
		//http.authorizeRequests().anyRequest().authenticated();
		http.authorizeHttpRequests(a->a
				.requestMatchers(HttpMethod.POST, "/identificarme").permitAll()
				.requestMatchers("/css/**", "/img/**", "/js/**").permitAll()
				.requestMatchers("/error/**").authenticated()
				.requestMatchers("/tipo/**", "/rest/tipos/**", "/datos/**").hasRole("DIRECCION")  
				//.requestMatchers(HttpMethod.POST,"/producto/crear").hasRole("DIRECCION")
				.requestMatchers("/producto/**", "/entrada/**").hasAnyRole("EMPLEADO", "DIRECCION")
				//.anyRequest().authenticated()
				.anyRequest().denyAll());
		
		//http.formLogin();
		http.formLogin(f->f
			.usernameParameter("login")
			.passwordParameter("clave")
			.loginPage("/entrada/login")
			.loginProcessingUrl("/entrada/procesar")
			//NI IDEA de por qué me pide "true". Eso significa "quiero que vayas obligatoriamente a esa página a pesar
			//de que has pedido acceder a otra protegida": Por ejemplo, antes de identificarme solicitas "/tipo/ver". Como
			//no tienes acceso, te pide el login, y si aciertas te muestra "/tipo/ver". En mi caso, hiciera lo que hiciera
			//pasaba de mí. Es curioso, porque el mismo proyecto con maven funciona con normalidad
			.defaultSuccessUrl("/entrada/inicio", true)
			.permitAll()
		);
		
		http.logout(l->l
				//.logoutUrl("/entrada/logout")
				.logoutRequestMatcher(new AntPathRequestMatcher("/entrada/logout", "GET"))
				);
		
		http.addFilterBefore(new FiltroSeguridadJWT(this.userDetailsService, this.utilidadesJWT) , UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	WebSecurityCustomizer cosasRaras() {
		return web->web.debug(false).ignoring().requestMatchers("/WEB-INF/**", "/consola/**");
	}
}
