package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;

/**
 * Interface de service dédiée à la gestion des points de courbes financières.
 *
 * <p>Cette interface définit les principales opérations métier associées aux {@link CurvePoint} dans l'application.</p>
 */
public interface CurvePointServiceInterface {

    /**
     * Récupère l'ensemble des points de courbe.
     *
     * @return Liste des points de courbe
     */
    List<CurvePoint> findAll();

    /**
     * Recherche un point de courbe à partir de son identifiant.
     *
     * @param id identifiant du point recherché
     * @return point de courbe correspondant
     */
    CurvePoint findById(Integer id);

    /**
     * Recherche les points associés à une courbe donnée
     *
     * @param curveId identifiant de la courbe recherchée
     * @return Liste des points associés à la courbe donnée
     */
    List<CurvePoint> findByCurveId(Integer curveId);

    /**
     * Enregistre ou met à jour un point de courbe.
     *
     * @param curvePoint point de courbe à enregistrer
     * @return point de courbe sauvegardé
     */
    CurvePoint save(CurvePoint curvePoint);

    /**
     * Supprime un point de courbe à partir de son identifiant.
     *
     * @param id identifiant du point de courbe à supprimer
     */
    void deleteById(Integer id);

}
