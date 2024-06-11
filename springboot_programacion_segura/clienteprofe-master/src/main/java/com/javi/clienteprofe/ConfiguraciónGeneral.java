package com.javi.clienteprofe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfiguraciónGeneral {

	@Bean
	RestTemplate restTemplate() {
		RestTemplate rt=new RestTemplate();
		//ya veremos
		return rt;
	}
}
