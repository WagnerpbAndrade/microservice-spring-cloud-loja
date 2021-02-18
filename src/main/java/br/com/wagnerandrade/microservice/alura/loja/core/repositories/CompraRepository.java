package br.com.wagnerandrade.microservice.alura.loja.core.repositories;

import br.com.wagnerandrade.microservice.alura.loja.core.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
