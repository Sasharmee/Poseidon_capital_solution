package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;

public interface CurvePointServiceInterface {

    //READ
    List<CurvePoint> findAll();

    CurvePoint findById(Integer id);

    List<CurvePoint> findByCurveId(Integer curveId);

    //CREATE + UPDATE
    CurvePoint save(CurvePoint curvePoint);

    //DELETE
    void deleteById(Integer id);

}
