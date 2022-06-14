package br.com.santanderbr.ib.contacorrente.api.enums;

import java.math.BigDecimal;

import br.com.santanderbr.ib.contacorrente.api.entity.Cliente;
import br.com.santanderbr.ib.contacorrente.api.service.ITaxaAdministracao;

public enum TaxaAdministrativaEnum implements ITaxaAdministracao {
	
	
	
	ISENTO(BigDecimal.ZERO) {
		
		@Override
		public BigDecimal calcularTaxaAdministracao(Cliente cliente, BigDecimal valorSaque) {
			
			return BigDecimal.valueOf(0.0);
		}}, 
	
	ZERO_QUATRO_PORC(BigDecimal.valueOf(100.00)){
		
		@Override
		public BigDecimal calcularTaxaAdministracao(Cliente cliente, BigDecimal valorSaque) {
			
			return valorSaque.multiply(BigDecimal.valueOf(0.004));
		}
		
		
	}, UM_PORCENT0(BigDecimal.valueOf(300.00)){
		
		@Override
		public BigDecimal calcularTaxaAdministracao(Cliente cliente, BigDecimal valorSaque) {
			
			return valorSaque.multiply(BigDecimal.valueOf(0.01));
		}
	};

    private final BigDecimal valor;

    private TaxaAdministrativaEnum(BigDecimal valor) {
        this.valor = valor;
    }

    public static TaxaAdministrativaEnum getTaxa(BigDecimal valor) {
        TaxaAdministrativaEnum found = ISENTO;
        for (TaxaAdministrativaEnum w : values())
            if (w.valor.compareTo(valor) <= 0)
                found = w;

        return found;
    }
}
