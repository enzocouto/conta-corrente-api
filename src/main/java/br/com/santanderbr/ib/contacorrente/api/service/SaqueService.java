package br.com.santanderbr.ib.contacorrente.api.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.santanderbr.ib.contacorrente.api.TransacaoDTO;
import br.com.santanderbr.ib.contacorrente.api.entity.Cliente;
import br.com.santanderbr.ib.contacorrente.api.entity.TransacaoBancaria;
import br.com.santanderbr.ib.contacorrente.api.enums.TaxaAdministrativaEnum;
import br.com.santanderbr.ib.contacorrente.api.repository.ClienteRepository;
import br.com.santanderbr.ib.contacorrente.api.repository.TransacaoBancariaRepository;
import br.com.santanderbr.ib.contacorrente.api.service.exception.NumeroContaInexistenteException;
import br.com.santanderbr.ib.contacorrente.api.service.exception.ValorInsuficienteException;

@Service
public class SaqueService implements ITransacaoBancaria {

	@Autowired
	ClienteService clienteService;
	
	@Autowired 
	TransacaoBancariaRepository transacaoBancariaRepository;
	
	@Autowired 
	ClienteRepository clienteRepository;
	
	@Override
	public ResponseEntity<String> realizarTransacao(TransacaoDTO transacao) {
		
		Optional<Cliente> optCliente = clienteRepository.findByNumeroConta(transacao.getNumeroConta());
		if(!optCliente.isPresent()){
			throw new NumeroContaInexistenteException("Cliente não encontrado");
		}
		
		Cliente cliente = optCliente.get();
		BigDecimal novoSaldo = sacarValor(cliente,transacao.getValor());
		clienteService.atualizarSaldo(cliente,novoSaldo);
		
		//Registrar transacao bancaria
		TransacaoBancaria registrarTransacao = registrarTransacao(transacao);
		transacaoBancariaRepository.saveAndFlush(registrarTransacao);
		
		return ResponseEntity.ok("Saque realizado com sucesso");
	}
	
	
	/*
	 * Realiza o calculo do saque e retorna valod do novo saldo 
	 */
	private BigDecimal sacarValor(Cliente cliente, BigDecimal valorSaque) {
		
		TaxaAdministrativaEnum txAdmEnum = TaxaAdministrativaEnum.getTaxa(valorSaque);
	    BigDecimal valorTaxa = txAdmEnum.calcularTaxaAdministracao(cliente, valorSaque);
	    BigDecimal novoSaldo = cliente.getSaldo().subtract(valorSaque.add(valorTaxa));
	    
	    if(novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
	    	throw new ValorInsuficienteException("Saldo insuficiente");
	    }
	    
	    return novoSaldo.setScale(2);
	    
	}
	
}
