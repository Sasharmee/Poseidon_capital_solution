package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;

import java.util.List;

/**
 * Interface de service dédiée à la gestion des opérations financières (trade).
 *
 * <p>Cette interface définit les principales opérations métier associées aux {@link Trade} dans l'application.</p>
 */
public interface TradeServiceInterface {

    /**
     * Récupère l'ensemble des opérations financières.
     *
     * @return liste des opérations financières disponibles
     */
    List<Trade> findAll();

    /**
     * Recherche une opération financière à partir de son identifiant unique.
     *
     * @param id identifiant de l'opération financière recherchée
     * @return opération financière correspondante
     */
    Trade findById(Integer id);

    /**
     * Enregistre ou met à jour une opération financière.
     *
     * @param trade opération financière à enregistrer
     * @return opération financière sauvegardée
     */
    Trade save(Trade trade);

    /**
     * Opération financière à supprimer à partir de son identifiant unique.
     *
     * @param id identifiant de l'opération financière à supprimer
     */
    void deleteById(Integer id);
}
