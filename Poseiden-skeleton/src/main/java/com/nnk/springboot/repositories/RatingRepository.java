package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository permettant la gestion des opérations
 * d'accès aux données pour l'entité {@link Rating}.
 *
 * <p>Cette interface fournit les opérations CRUD
 * standards via Spring Data JPA.</p>
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
