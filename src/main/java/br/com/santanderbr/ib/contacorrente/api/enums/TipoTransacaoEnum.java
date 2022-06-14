package br.com.santanderbr.ib.contacorrente.api.enums;

public enum TipoTransacaoEnum {

	DEPOSITO("D"), SAQUE("S");
	
	private final String tipo;
	
	
	TipoTransacaoEnum(String tipo){
	this.tipo = tipo;
	}
	
	
	public String getTipo(){
	return tipo;
	}
}
