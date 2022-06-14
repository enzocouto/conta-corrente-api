package br.com.santanderbr.ib.contacorrente.api.service;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import br.com.santanderbr.ib.contacorrente.api.TransacaoDTO;
import br.com.santanderbr.ib.contacorrente.api.entity.TransacaoBancaria;

public interface ITransacaoBancaria {

	
	public ResponseEntity<String> realizarTransacao(TransacaoDTO saque);
	
	public default TransacaoBancaria registrarTransacao(TransacaoDTO transacao) {
		
		TransacaoBancaria transacaBancaria = new TransacaoBancaria(transacao.getTipoTransacao(),
				transacao.getValor(),
				transacao.getNumeroConta(),
				LocalDate.now());
		
		return transacaBancaria;
	}; 
		
		


		
		
	
	
}
