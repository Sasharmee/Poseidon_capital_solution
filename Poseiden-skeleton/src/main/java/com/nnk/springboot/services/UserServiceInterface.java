package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;

import java.util.List;

/**
 * Interface de service dédiée à la gestion des utilisateurs de l'application.
 *
 * <p>Cette interface définit les principales opérations métier associées aux {@link User} dans l'application.</p>
 */
public interface UserServiceInterface {

    /**
     * Récupère l'ensemble des utilisateurs.
     *
     * @return liste des utilisateurs disponibles
     */
    List<User> findAll();

    /**
     * Récupère un utilisateur à partir de son identifiant unique.
     *
     * @param id identifiant unique de l'utilisateur recherché
     * @return l'utilisateur correspondant
     */
    User findById(Integer id);

    /**
     * Enregistre ou met à jour un utilisateur.
     *
     * @param user utilisateur à enregistrer
     * @return utilisateur sauvegardé
     */
    User save(User user);

    /**
     * Supprime un utilisateur à partir de son identifiant unique.
     *
     * @param id identifiant unique de l'utilisateur à supprimer
     */
    void deleteById(Integer id);
}
