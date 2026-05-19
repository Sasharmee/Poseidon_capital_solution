package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;

import java.util.List;

/**
 * Interface de service dédiée à la gestion des règles métier configurables.
 *
 * <p>Cette interface définit les principales opérations métier associées aux {@link RuleName} dans l'application.</p>
 */
public interface RuleNameServiceInterface {

    /**
     * Récupère l'ensemble des règles métier.
     *
     * @return liste des règles métier disponibles
     */
    List<RuleName> findAll();

    /**
     * Recherche une règle métier à partir de son identifiant unique.
     *
     * @param id identifiant de la règle recherchée
     * @return règle correspondante
     */
    RuleName findById(Integer id);

    /**
     * Enregistre ou met à jour une règle métier.
     *
     * @param ruleName règle à enregistrer
     * @return règle sauvegarder
     */
    RuleName save(RuleName ruleName);

    /**
     * Supprime une règle métier à partir de son identifiant
     *
     * @param id identifiant de la règle à supprimer
     */
    void deleteById(Integer id);
}


