package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository permettant la gestion des opérations d'accès aux données de l'entité {@link BidList}.
 *
 * <p>Cette interface fournit les opérations CRUD standards via Spring Data JPA.</p>
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
