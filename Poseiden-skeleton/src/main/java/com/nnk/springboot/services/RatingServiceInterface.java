package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;

import java.util.List;

public interface RatingServiceInterface {

    //READ
    List<Rating> findAll();

    Rating findById(Integer id);

    //CREATE + UPDATE
    Rating save(Rating rating);

    //DELETE
    void deleteById(Integer id);
}
