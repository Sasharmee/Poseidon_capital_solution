package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Classe de tests unitaires dédiée au service {@link RatingService}.
 *
 * <p>Cette classe vérifie le bon fonctionnement des opérations CRUD associées aux entités {@link Rating}.</p>
 */
@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    /**
     * Repository mocké de gestion des Rating.
     */
    @Mock
    private RatingRepository ratingRepository;

    /**
     * Service testé avec injections des mocks.
     */
    @InjectMocks
    private RatingService ratingService;

    /**
     * Vérifie la récupération de tous les Ratings.
     */
    @Test
    void testFindAll() {
        Rating rating = new Rating();
        when(ratingRepository.findAll()).thenReturn(List.of(rating));

        List<Rating> result = ratingService.findAll();

        assertFalse(result.isEmpty());
        verify(ratingRepository).findAll();
    }

    /**
     * Vérifie la récupération d'un Rating via son identifiant unique.
     */
    @Test
    void testFindById() {
        Rating rating = new Rating();
        rating.setId(1);

        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        Rating result = ratingService.findById(1);

        assertEquals(1, result.getId());
        verify(ratingRepository).findById(1);
    }

    /**
     * Vérifie qu'une exception est levée lorsqu'un Rating n'est pas trouvé via l'identifiant donné.
     */
    @Test
    void testFindById_whenIdNotFound() {
        when(ratingRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                ratingService.findById(1));
    }

    /**
     * Vérifie la sauvegarde d'un Rating.
     */
    @Test
    void testSave() {
        Rating rating = new Rating();

        when(ratingRepository.save(rating)).thenReturn(rating);

        Rating result = ratingService.save(rating);

        assertNotNull(result);
        verify(ratingRepository).save(rating);
    }

    /**
     * Vérifie la suppression d'un Rating via son identifiant.
     */
    @Test
    void testDeleteById() {
        ratingService.deleteById(1);
        verify(ratingRepository).deleteById(1);
    }
}
