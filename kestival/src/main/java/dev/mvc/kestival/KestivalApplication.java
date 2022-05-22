package dev.mvc.kestival;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.mvc"})
public class KestivalApplication {

	public static void main(String[] args) {
		SpringApplication.run(KestivalApplication.class, args);
	}

}
