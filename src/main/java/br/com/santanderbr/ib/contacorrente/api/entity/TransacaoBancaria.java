package br.com.santanderbr.ib.contacorrente.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Transacao_Bancaria")
public class TransacaoBancaria implements Serializable{


	private static final long serialVersionUID = 1L;


		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String tipoTransacao;
	    
		private BigDecimal valor;
		
		private String numeroConta;
	    
	    private LocalDate dataTransacao;
	    
		
		public TransacaoBancaria() {}
				
		
		public TransacaoBancaria(String tipoTransacao, BigDecimal valor, String numeroConta, LocalDate dataTransacao) {
			super();
			this.tipoTransacao = tipoTransacao;
			this.valor = valor;
			this.numeroConta = numeroConta;
			this.dataTransacao = dataTransacao;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}


		public Long getId() {
			return id;
		}


		public String getTipoTransacao() {
			return tipoTransacao;
		}


		public BigDecimal getValor() {
			return valor;
		}


		public String getNumeroConta() {
			return numeroConta;
		}


		public LocalDate getDataTransacao() {
			return dataTransacao;
		}

}
