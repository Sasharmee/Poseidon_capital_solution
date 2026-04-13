package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointService implements CurvePointServiceInterface {

    private final CurvePointRepository curvePointRepository;

    public CurvePointService(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    @Override
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    @Override
    public CurvePoint findById(Integer id) {
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        return curvePoint.orElseThrow(()->new IllegalArgumentException("Invalid curvePoint id" + id));
    }

    @Override
    public List<CurvePoint> findByCurveId(Integer curveId) {
        return curvePointRepository.findByCurveId(curveId);
    }

    @Override
    public CurvePoint save(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    @Override
    public void deleteById(Integer id) {
        curvePointRepository.deleteById(id);
    }
}
