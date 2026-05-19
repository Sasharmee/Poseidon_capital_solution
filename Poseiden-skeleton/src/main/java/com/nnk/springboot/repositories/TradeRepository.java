package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository permettant la gestion des opérations
 * d'accès aux données pour l'entité {@link Trade}.
 *
 * <p>Cette interface fournit les opérations CRUD
 * standards via Spring Data JPA.</p>
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
