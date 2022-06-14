package br.com.santanderbr.ib.contacorrente.api.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santanderbr.ib.contacorrente.api.entity.Cliente;
import br.com.santanderbr.ib.contacorrente.api.entity.TransacaoBancaria;
import br.com.santanderbr.ib.contacorrente.api.repository.TransacaoBancariaRepository;
import br.com.santanderbr.ib.contacorrente.api.service.ExtratoService;
import br.com.santanderbr.ib.contacorrente.api.entity.TransacaoBancaria;


@RestController
@RequestMapping("/extrato")
public class ExtratoController {

	
	@Autowired
	ExtratoService extratoService;
	
	@GetMapping("/{numeroConta}/{dataTransacao}")
	public ResponseEntity<?> extratoTransacaoPorData(@PathVariable String numeroConta, @PathVariable LocalDate dataTransacao,
			Pageable pageable) {
		
		
		
		Page<TransacaoBancaria> extrato = extratoService.consultarPorData(numeroConta,dataTransacao,pageable);
		
		return ResponseEntity.ok(extrato);
		
	}
	
}
