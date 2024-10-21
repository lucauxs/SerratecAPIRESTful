package org.serratec.serratecpub.util;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(
		title = "Api para gestão de um pub",
		version = "1.0",
		description = "Documentação da Api serratecpub"
		))
public class OpenApiConfig {
	
	
}