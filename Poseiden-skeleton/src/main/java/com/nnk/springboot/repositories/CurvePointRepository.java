package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository permettant la gestion des opérations
 * d'accès aux données pour l'entité {@link CurvePoint}.
 *
 * <p>Cette interface fournit les opérations CRUD
 * standards via Spring Data JPA.</p>
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

    /**
     * Recherche des points de courbe associé à une courbe donnée
     *
     * @param curveId l'identifiant de la courbe donnée
     * @return Liste des points de courbe associé à la courbe renseignée
     */
    List<CurvePoint> findByCurveId(Integer curveId);

}
