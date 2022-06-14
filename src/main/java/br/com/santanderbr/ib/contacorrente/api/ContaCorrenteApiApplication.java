package br.com.santanderbr.ib.contacorrente.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableTransactionManagement
@EnableWebMvc
public class ContaCorrenteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContaCorrenteApiApplication.class, args);
	}

}
