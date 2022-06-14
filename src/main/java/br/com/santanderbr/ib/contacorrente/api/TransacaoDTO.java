package br.com.santanderbr.ib.contacorrente.api;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TransacaoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "O Numero da Conta é obrigatório")
	private String numeroConta;
	
	@NotNull(message = "O valor é obrigatório")
	private BigDecimal valor;
	
	
	@NotBlank(message = "Tipo de Transação é Obrigatório")
	private String tipoTransacao;
	
	
	public TransacaoDTO() {}

	public TransacaoDTO(String tipoTransacao, String numeroConta, BigDecimal valor) {
		super();
		this.tipoTransacao = tipoTransacao;
		this.numeroConta = numeroConta;
		this.valor = valor;
	}

}
