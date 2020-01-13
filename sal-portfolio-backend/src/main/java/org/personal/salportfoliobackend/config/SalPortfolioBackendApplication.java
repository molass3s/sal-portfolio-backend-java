package org.personal.salportfoliobackend.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.personal.salportfoliobackend")
public class SalPortfolioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalPortfolioBackendApplication.class, args);
	}

}
