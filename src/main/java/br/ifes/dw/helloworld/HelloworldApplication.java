package br.ifes.dw.helloworld;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Atividade - Eduardo Henrique",
		version = "1.0",
		description = "CRUD de Produtos"))
public class HelloworldApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}
}
