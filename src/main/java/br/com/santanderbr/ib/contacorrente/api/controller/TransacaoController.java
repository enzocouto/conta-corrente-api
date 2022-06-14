package br.com.santanderbr.ib.contacorrente.api.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.santanderbr.ib.contacorrente.api.TransacaoDTO;
import br.com.santanderbr.ib.contacorrente.api.enums.TipoTransacaoEnum;
import br.com.santanderbr.ib.contacorrente.api.service.DepositoService;
import br.com.santanderbr.ib.contacorrente.api.service.ITransacaoBancaria;
import br.com.santanderbr.ib.contacorrente.api.service.SaqueService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
		
	ITransacaoBancaria itransacaoBancaria;
	
	@Autowired
	SaqueService saqueService;
	
	@Autowired
	DepositoService depositoService;
	
	
	@PutMapping
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> efetivarTransacao(@Valid @RequestBody TransacaoDTO transacao) {
		
		
		if(transacao.getTipoTransacao().equals(TipoTransacaoEnum.SAQUE.getTipo())) {
			itransacaoBancaria = saqueService;
		}else if(transacao.getTipoTransacao().equals(TipoTransacaoEnum.DEPOSITO.getTipo())) {
			itransacaoBancaria = depositoService;
		}
		
		return itransacaoBancaria.realizarTransacao(transacao);
		
	}
	
}
