package br.com.santanderbr.ib.contacorrente.api.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.santanderbr.ib.contacorrente.api.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente , Long>{
	
	
	public Optional<Cliente> findByNumeroConta(String numeroConta);

	@Modifying
	@Transactional
	@Query("update Cliente c set c.saldo = :saldo WHERE c.id = :id")
	public void atualizarSaldo(Long id, BigDecimal saldo);
	
}
