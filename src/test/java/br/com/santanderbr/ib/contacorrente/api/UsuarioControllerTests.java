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
import br.com.santanderbr.ib.contacorrente.api.entity.Cliente;
import br.com.santanderbr.ib.contacorrente.api.repository.ClienteRepository;
import br.com.santanderbr.ib.contacorrente.api.serializers.LocalDateDeserializer;
import br.com.santanderbr.ib.contacorrente.api.serializers.LocalDateSerializer;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UsuarioControllerTests {

	
	@Autowired
	private ClienteController controller;
	
	
	@Autowired
	private ClienteRepository repository;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void cargaInicialComSucesso() throws Exception {
		this.mockMvc.perform(get("/clientes")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Enzo Couto")));
	}
	
	@Test
	public void clienteIncluidoComSucesso() throws Exception {
		
		Cliente cliente = new Cliente("CLIENTE JUNIT", true,BigDecimal.valueOf(1000),"22222",LocalDate.of(2000, 01, 30));
		
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        
		String strCliente = gson.toJson(cliente);
		
		this.mockMvc.perform(post("/clientes")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(strCliente))
	            .andDo(print())
	            .andExpect(status().is2xxSuccessful());
	}
	
	
	/**
	 * 
	 * Tentativa de inserir cliente com conta já exietente
	 * Esperado : Httptatus 409 
	 * 			   Content: Numero da Conta já existe
	 */
	@Test
	public void clienteNumeroContaExistente() throws Exception {
		
		Cliente cliente = null;
		Optional<Cliente> optCliente = repository.findByNumeroConta("111111");
		
		if(optCliente.isPresent()) {
			cliente = optCliente.get();
		}
		
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        
		String strCliente = gson.toJson(cliente);
		
		this.mockMvc.perform(post("/clientes")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(strCliente))
	            .andDo(print())
	            .andExpect(status().is(409))
	            .andExpect(content().string("Numero da Conta já existe"));
	}
	
}


