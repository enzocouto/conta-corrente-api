package br.com.santanderbr.ib.contacorrente.api.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.santanderbr.ib.contacorrente.api.entity.TransacaoBancaria;
import br.com.santanderbr.ib.contacorrente.api.repository.TransacaoBancariaRepository;

@Service
public class ExtratoService {
	
	
	@Autowired 
	TransacaoBancariaRepository transacaoBancariaRepository;

	public Page<TransacaoBancaria> consultarPorData(String numeroConta, LocalDate dataTransacao,Pageable pageable) {
			
		return transacaoBancariaRepository.findByNumeroContaAndDataTransacao(numeroConta, dataTransacao ,pageable);
		
	}
	
}
