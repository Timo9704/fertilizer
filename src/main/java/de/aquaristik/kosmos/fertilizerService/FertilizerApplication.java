package de.aquaristik.kosmos.fertilizerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class FertilizerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(FertilizerApplication.class, args);

	}
}
