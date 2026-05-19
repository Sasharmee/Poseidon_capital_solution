package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;

import java.util.List;

/**
 * Interface de service dédiée à la gestion des opérations métier liées aux {@link BidList}.
 *
 * <p>Cette interface définit les opérations principales de gestion des entrées d'enchères dans l'application</p>
 */
public interface BidListServiceInterface {

    /**
     * Récupère l'ensemble des entrées.
     *
     * @return Liste des entrées disponibles
     */
    List<BidList> findAll();

    /**
     * Recherche une entrée de cotation à partir de son identifiant.
     *
     * @param id identifiant de l'entrée recherchée
     * @return l'entrée correspondante
     */
    BidList findById(Integer id);

    /**
     * Enregistre ou met à jour une entrée.
     *
     * @param bidList entrée à enregistrer
     * @return entrée sauvegardée
     */
    BidList save(BidList bidList);

    /**
     * Supprime une entrée à partir de son identifiant
     *
     * @param id identifiant de l'entrée à supprimer
     */
    void deleteById(Integer id);
}

