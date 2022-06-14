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
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.santanderbr.ib.contacorrente.api.controller.TransacaoController;
import br.com.santanderbr.ib.contacorrente.api.entity.Cliente;
import br.com.santanderbr.ib.contacorrente.api.repository.ClienteRepository;
import br.com.santanderbr.ib.contacorrente.api.repository.TransacaoBancariaRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ExtratoControllerTests {

	
	@Autowired
	private TransacaoController controller;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Autowired
	private TransacaoBancariaRepository transacaoBancariaRepository;
	
	@Autowired
	private ClienteRepository repository;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void depositoRealizadoComSucesso() throws Exception {
		
		Optional<Cliente> findClienteAntesDeposito = clienteRepository.findByNumeroConta("111111");
		Cliente clienteAntesDeposito= findClienteAntesDeposito.get();
		
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
		
		BigDecimal saldoAntesDeposito = clienteAntesDeposito.getSaldo().add(valorDeposito).setScale(2);
		
		Optional<Cliente> findclienteDepoisDeposito = clienteRepository.findByNumeroConta("111111");
		Cliente clienteDepoisDeposito = findclienteDepoisDeposito.get();
		BigDecimal saldoDepoisDeposito = clienteDepoisDeposito.getSaldo().setScale(2);
		
		
		
	}
	/**
	 * 
	 * O contreudo do extrato deve ter 3 elementos (Os 3 depositos realizados no teste  )
	 */
	@Test
	public void extratoDoDiaComSucesso() throws Exception {
		
		transacaoBancariaRepository.deleteAll();
		
		this.depositoRealizadoComSucesso();
		this.depositoRealizadoComSucesso();
		this.depositoRealizadoComSucesso();
		
		String now = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println("DATA DE HOJE: "+now);
		this.mockMvc.perform(get("/extrato/111111/"+now)
	            .contentType(MediaType.APPLICATION_JSON))            
	            .andDo(print())
	            .andExpect(status().is2xxSuccessful())
	            .andExpect(content().string(containsString("\"numberOfElements\":3")));
		
		
		
	}
	
	
}


