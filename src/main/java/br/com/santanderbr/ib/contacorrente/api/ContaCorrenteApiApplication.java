package br.com.santanderbr.ib.contacorrente.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ContaCorrenteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContaCorrenteApiApplication.class, args);
	}

}
