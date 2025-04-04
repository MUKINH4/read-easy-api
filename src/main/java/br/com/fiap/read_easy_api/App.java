package br.com.fiap.read_easy_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
	info = @Info(title = "Read Easy API", version = "v1", description = "API para organizar leitura de livros")
)
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
