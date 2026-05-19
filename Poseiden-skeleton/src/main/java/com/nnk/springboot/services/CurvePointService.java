package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation du service de gestion des points de courbe financière.
 *
 * <p>Cette classe assure les opérations d'accès et de gestion des {@link CurvePoint}
 * via le repository {@link CurvePointRepository}</p>
 */
@Service
public class CurvePointService implements CurvePointServiceInterface {

    /**
     * Repository de gestion des points de courbe.
     */
    private final CurvePointRepository curvePointRepository;

    /**
     * Constructeur du service de gestion des CurvePoint.
     *
     * @param curvePointRepository repository des points de courbe
     */
    public CurvePointService(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CurvePoint findById(Integer id) {
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        return curvePoint.orElseThrow(()->new IllegalArgumentException("Invalid curvePoint id " + id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CurvePoint> findByCurveId(Integer curveId) {
        return curvePointRepository.findByCurveId(curveId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CurvePoint save(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Integer id) {
        curvePointRepository.deleteById(id);
    }
}
