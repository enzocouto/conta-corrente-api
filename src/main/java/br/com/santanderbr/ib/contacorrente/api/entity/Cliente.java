package br.com.santanderbr.ib.contacorrente.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
@Table(name="Cliente")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;


		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
		@NotBlank(message = "O Nome é obrigatório")
	    private String nome;
	    
		@NotNull(message = "A Flag isExclusive é obrigatória")
	    @Type(type="yes_no")
	    private Boolean isExclusive;

		@NotNull(message = "O saldo é obrigatório")
		private BigDecimal saldo;
		
		@Column( name = "NUMERO_CONTA")
		@NotBlank(message = "O Numero da conta é obrigatório")
		private String numeroConta;
	    
		@Column(name = "DATA_NASCIMENTO")
		@NotNull(message = "A Data de Nascimento é obrigatória")
	    private LocalDate dataNascimento;
	    
		public Cliente() {}

		public Cliente(String nome, Boolean isExclusive, BigDecimal saldo,
				String numeroConta,LocalDate dataNascimento) {
			super();
			this.nome = nome;
			this.isExclusive = isExclusive;
			this.saldo = saldo;
			this.numeroConta = numeroConta;
			this.dataNascimento = dataNascimento;
		}
		
		public Long getId() {
			return id;
		}
		
		public String getNome() {
			return nome;
		}
		
		public Boolean getIsExclusive() {
			return isExclusive;
		}

		public BigDecimal getSaldo() {
			return saldo;
		}

		public String getNumeroConta() {
			return numeroConta;
		}
		
		public LocalDate getDataNascimento() {
			return dataNascimento;
		}
    
}
