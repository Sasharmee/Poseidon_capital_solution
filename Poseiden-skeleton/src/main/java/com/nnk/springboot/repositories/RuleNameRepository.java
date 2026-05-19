package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository permettant la gestion des opérations
 * d'accès aux données pour l'entité {@link RuleName}.
 *
 * <p>Cette interface fournit les opérations CRUD
 * standards via Spring Data JPA.</p>
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
