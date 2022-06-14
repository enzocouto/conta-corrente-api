package br.com.santanderbr.ib.contacorrente.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.santanderbr.ib.contacorrente.api.controller.ClienteController;
import br.com.santanderbr.ib.contacorrente.api.controller.TransacaoController;
import br.com.santanderbr.ib.contacorrente.api.entity.Cliente;
import br.com.santanderbr.ib.contacorrente.api.repository.ClienteRepository;
import br.com.santanderbr.ib.contacorrente.api.serializers.LocalDateDeserializer;
import br.com.santanderbr.ib.contacorrente.api.serializers.LocalDateSerializer;
import br.com.santanderbr.ib.contacorrente.api.service.ClienteService;
import junit.framework.Assert;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TransacaoControllerTests {

	
	@Autowired
	private TransacaoController controller;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteRepository repository;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		
		assertThat(controller).isNotNull();
	}
	
	
	@Test
	public void saqueTaxaIsentaRealizadoComSucesso() throws Exception {
		
		Optional<Cliente> findClienteAntesSaque = clienteRepository.findByNumeroConta("111111");
		Cliente clienteAntesSaque = findClienteAntesSaque.get();
		
		BigDecimal valorSaque = BigDecimal.valueOf(10);
		
		TransacaoDTO saque = new TransacaoDTO("S","111111",valorSaque);
		
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        
		String strSaque = gson.toJson(saque);
		
		this.mockMvc.perform(post("/transacoes")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(strSaque))
	            .andDo(print())
	            .andExpect(status().is2xxSuccessful());
		
		BigDecimal saldoAntesSaque = clienteAntesSaque.getSaldo().subtract(valorSaque);
		
		Optional<Cliente> findclienteDepoisSaque = clienteRepository.findByNumeroConta("111111");
		Cliente clienteDepoisSaque = findclienteDepoisSaque.get();
		BigDecimal saldoDepoisSaque = clienteDepoisSaque.getSaldo();
		
		Assert.assertEquals(saldoAntesSaque, saldoDepoisSaque);
		
	}
	
	
	@Test
	public void depositoRealizadoComSucesso() throws Exception {
		
		Optional<Cliente> findClienteAntesSaque = clienteRepository.findByNumeroConta("111111");
		Cliente clienteAntesSaque = findClienteAntesSaque.get();
		
		BigDecimal valorDeposito = BigDecimal.valueOf(10);
		
		TransacaoDTO saque = new TransacaoDTO("D","111111",valorDeposito);
		
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        
		String strSaque = gson.toJson(saque);
		
		this.mockMvc.perform(post("/transacoes")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(strSaque))
	            .andDo(print())
	            .andExpect(status().is2xxSuccessful());
		
		BigDecimal saldoAntesSaque = clienteAntesSaque.getSaldo().add(valorDeposito);
		
		Optional<Cliente> findclienteDepoisSaque = clienteRepository.findByNumeroConta("111111");
		Cliente clienteDepoisSaque = findclienteDepoisSaque.get();
		BigDecimal saldoDepoisSaque = clienteDepoisSaque.getSaldo();
		
		Assert.assertEquals(saldoAntesSaque, saldoDepoisSaque);
		
	}
	
	
	
	@Test
	public void saqueSaldoInsuficiente() throws Exception {
		
		Optional<Cliente> findClienteAntesSaque = clienteRepository.findByNumeroConta("111111");
		Cliente clienteAntesSaque = findClienteAntesSaque.get();
		
		BigDecimal valorSaque = BigDecimal.valueOf(1001.00);
		
		TransacaoDTO saque = new TransacaoDTO("S","111111",valorSaque);
		
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        
		String strSaque = gson.toJson(saque);
		
		this.mockMvc.perform(post("/transacoes")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(strSaque))
	            .andDo(print())
	            .andExpect(status().is(400))
	            .andExpect(content().string("Saldo insuficiente"));
		
	}
	
	@Test
	public void clienteNumeroContaExistente() throws Exception {
		
		Optional<Cliente> findClienteAntesSaque = clienteRepository.findByNumeroConta("111111");
		Cliente clienteAntesSaque = findClienteAntesSaque.get();
		
		BigDecimal valorSaque = BigDecimal.valueOf(10);
		
		//Numero da conta inexistente na Base de dados
		TransacaoDTO saque = new TransacaoDTO("S","5664871244",valorSaque);
		
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        
		String strSaque = gson.toJson(saque);
		
		this.mockMvc.perform(post("/transacoes")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(strSaque))
	            .andDo(print())
	            .andExpect(status().is(409))
	            .andExpect(content().string("Cliente não encontrado"));
	}
	
	
	
	
}


