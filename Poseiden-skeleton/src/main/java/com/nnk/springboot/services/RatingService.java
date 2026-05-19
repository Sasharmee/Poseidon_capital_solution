package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation du service de gestion des notations financières.
 *
 * <p>Cette classe assure les opérations de gestion et d'accès aux données des {@link Rating}
 * via le repository {@link RatingRepository}.</p>
 */
@Service
public class RatingService implements RatingServiceInterface{

    /**
     * Repository de gestion des notations financières.
     */
    private final RatingRepository ratingRepository;

    /**
     * Constructeur du service de gestion des notations.
     *
     * @param ratingRepository repository des notations financières
     */
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rating findById(Integer id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        return rating.orElseThrow(()->new IllegalArgumentException("Invalid rating id " + id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Integer id) {
        ratingRepository.deleteById(id);
    }
}
