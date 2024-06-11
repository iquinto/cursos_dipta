package com.javi.tiendaprofe;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.javi.tiendaprofe.modelo.entidad.Usuario;
import com.javi.tiendaprofe.modelo.entidad.Usuario.Grupo;
import com.javi.tiendaprofe.modelo.repositorio.RepositorioUsuario;



@Component
public class SistemaDeUsuarios implements UserDetailsService {
	
	@Autowired private RepositorioUsuario ru;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> caja=ru.findById(username);
		if (caja.isEmpty()) throw new UsernameNotFoundException("No existe");
		
		Usuario encontrado=caja.get();
		
		Set<GrantedAuthority> grupos=new HashSet<>();
		for (Grupo g: encontrado.getGrupos())
			grupos.add(new SimpleGrantedAuthority("ROLE_" + g.name()));
		
		return new User(encontrado.getLogin(), encontrado.getClave(),grupos);
	}

}
