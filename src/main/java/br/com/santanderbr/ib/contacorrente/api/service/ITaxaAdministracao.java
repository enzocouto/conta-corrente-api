package br.com.santanderbr.ib.contacorrente.api.service;

import java.math.BigDecimal;

import br.com.santanderbr.ib.contacorrente.api.entity.Cliente;

public interface ITaxaAdministracao {

	public BigDecimal calcularTaxaAdministracao(Cliente cliente,BigDecimal valorSaque);
}
