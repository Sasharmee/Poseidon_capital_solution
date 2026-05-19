package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;

import java.util.List;

/**
 * Interface de service dédiée à la gestion des notations financières.
 *
 * <p>Cette interface définit les opérations métiers principales liées aux {@link Rating} dans le système financier.</p>
 */
public interface RatingServiceInterface {

    /**
     * Récupère l'ensemble des notations financières.
     *
     * @return Liste des notations financières disponibles
     */
    List<Rating> findAll();

    /**
     * Récupère une notation financière selon un identifiant donné
     *
     * @param id identifiant unique de la notation financière recherchée
     * @return notation financière recherchée
     */
    Rating findById(Integer id);

    /**
     * Enregistre ou met à jour une notation financière.
     *
     * @param rating notation à enregistrer
     * @return notation sauvegarder
     */
    Rating save(Rating rating);

    /**
     * Supprime une notation financière à partir de son identifiant.
     *
     * @param id identifiant de la notation à supprimer
     */
    void deleteById(Integer id);
}
