package br.com.fiap.safequake_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@OpenAPIDefinition(
	info = 
	@Info(
		title = "API SafeQuake", 
		version = "v1", 
		description = "API de SaaS de alertas de terremotos", 
		contact = 
		@Contact(
			name = "SafeQuake", 
			email = "contato@safequake.com.br")))
			
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
}

}