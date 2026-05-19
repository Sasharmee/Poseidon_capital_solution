package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository permettant la gestion des opérations
 * d'accès aux données pour l'entité {@link User}.
 *
 * <p>Cette interface fournit les opérations CRUD
 * standards ainsi que les fonctionnalités de recherche
 * avancée via les spécifications Spring Data JPA.</p>
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * Recherche un utilisateur à partir de son nom d'utilisateur.
     *
     * @param username nom de l'utilisateur recherché
     * @return l'utilisateur correspond au nom renseigné ou {@code null} si aucun utilisateur n'est retrouvé
     */
    User findByUsername(String username);

}
