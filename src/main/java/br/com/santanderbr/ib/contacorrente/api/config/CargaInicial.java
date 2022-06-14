package br.com.santanderbr.ib.contacorrente.api.config;

import org.springframework.stereotype.Component;

import br.com.santanderbr.ib.contacorrente.api.entity.Cliente;
import br.com.santanderbr.ib.contacorrente.api.repository.ClienteRepository;
import br.com.santanderbr.ib.contacorrente.api.repository.TransacaoBancariaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@Component
public class CargaInicial implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    TransacaoBancariaRepository TransacaoBancariaRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {

    	//limpar Tabelas
    	TransacaoBancariaRepository.deleteAll();
    	clienteRepository.deleteAll();
    	Cliente cliente = new Cliente("Enzo Couto", true,BigDecimal.valueOf(1000),"111111",LocalDate.of(1999, 01, 01));
		clienteRepository.save(cliente);
		
		

    }

}
