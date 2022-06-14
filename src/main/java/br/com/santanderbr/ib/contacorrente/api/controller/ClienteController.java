package br.com.santanderbr.ib.contacorrente.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santanderbr.ib.contacorrente.api.entity.Cliente;
import br.com.santanderbr.ib.contacorrente.api.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	
	@PostMapping
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insereCliente(@Valid @RequestBody Cliente cliente) {
		
	    service.insereCliente(cliente);
		return ResponseEntity.ok("Cliente Cadastrado com Sucesso");
		
	}
	
	@GetMapping
	public ResponseEntity<?> listarClientes(Pageable pageable){
		
		Page<Cliente> clientes =  service.listarClientes(pageable);
		return ResponseEntity.ok(clientes);
	}

}
