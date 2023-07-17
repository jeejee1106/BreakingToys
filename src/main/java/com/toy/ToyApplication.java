package com.toy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@EnableJpaAuditing
@SpringBootApplication
public class ToyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToyApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routerFunction() {
		return route(GET("/"), req ->
				ServerResponse.temporaryRedirect(URI.create("swagger-ui/index.html")).build());
	}
}
