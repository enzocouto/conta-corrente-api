package br.com.santanderbr.ib.contacorrente.api.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.santanderbr.ib.contacorrente.api.entity.Cliente;
import br.com.santanderbr.ib.contacorrente.api.repository.ClienteRepository;
import br.com.santanderbr.ib.contacorrente.api.service.exception.NumeroContaJaCadastradoException;


@Service
public class ClienteService {

	 @Autowired
	 private ClienteRepository repository;

	 
	 public void insereCliente(Cliente cliente) {
		 
		 Optional<Cliente> clienteExistente = repository.findByNumeroConta(cliente.getNumeroConta());
		 if(clienteExistente.isPresent()) {
			throw new NumeroContaJaCadastradoException("Numero da Conta já existe");
		 }
			
		 repository.saveAndFlush(cliente);	
	 }


	public Page<Cliente> listarClientes(Pageable pageable) {
		
		return repository.findAll(pageable);	
	}

	protected void atualizarSaldo(Cliente cliente, BigDecimal novoSaldo) {
		
		repository.atualizarSaldo(cliente.getId(),novoSaldo);
	}

		 
}
