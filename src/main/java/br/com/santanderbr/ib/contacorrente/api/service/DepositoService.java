package br.com.santanderbr.ib.contacorrente.api.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.santanderbr.ib.contacorrente.api.TransacaoDTO;
import br.com.santanderbr.ib.contacorrente.api.entity.Cliente;
import br.com.santanderbr.ib.contacorrente.api.entity.TransacaoBancaria;
import br.com.santanderbr.ib.contacorrente.api.repository.ClienteRepository;
import br.com.santanderbr.ib.contacorrente.api.repository.TransacaoBancariaRepository;
import br.com.santanderbr.ib.contacorrente.api.service.exception.NumeroContaInexistenteException;
import br.com.santanderbr.ib.contacorrente.api.service.exception.ValorDepositoInvalidoException;

@Service
public class DepositoService implements ITransacaoBancaria {

	@Autowired
	ClienteService clienteService;
	
	@Autowired 
	TransacaoBancariaRepository transacaoBancariaRepository;
	
	@Autowired 
	ClienteRepository clienteRepository;
	
	@Override
	@Transactional
	public ResponseEntity<String> realizarTransacao(TransacaoDTO transacao) {
		
		Optional<Cliente> optCliente = clienteRepository.findByNumeroConta(transacao.getNumeroConta());
		if(!optCliente.isPresent()){
			throw new NumeroContaInexistenteException("Cliente não encontrado");
		}
		
		if(transacao.getValor().compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValorDepositoInvalidoException("Valor de depósito inválido");
		}
		
		Cliente cliente = optCliente.get();
		//Atualizar saldo do cliente
		BigDecimal novoSaldo = cliente.getSaldo().add(transacao.getValor());
		clienteRepository.atualizarSaldo(cliente.getId(), novoSaldo);
		
		//Registrar transacao bancaria
		TransacaoBancaria registrarTransacao = registrarTransacao(transacao);
		transacaoBancariaRepository.saveAndFlush(registrarTransacao);
		
		return ResponseEntity.ok("Deposito realizado com sucesso");
	}
	
}
