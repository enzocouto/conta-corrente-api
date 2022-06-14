package br.com.santanderbr.ib.contacorrente.api.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.santanderbr.ib.contacorrente.api.entity.TransacaoBancaria;

@Repository
public interface TransacaoBancariaRepository extends JpaRepository<TransacaoBancaria , Long>{

	Page<TransacaoBancaria> findByNumeroContaAndDataTransacao(String numeroConta, LocalDate dataTransacao, Pageable pageable);
	
	
}
