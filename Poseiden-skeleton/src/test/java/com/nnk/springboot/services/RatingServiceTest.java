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

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    @Test
    void testFindAll() {
        Rating rating = new Rating();
        when(ratingRepository.findAll()).thenReturn(List.of(rating));

        List<Rating> result = ratingService.findAll();

        assertFalse(result.isEmpty());
        verify(ratingRepository).findAll();
    }

    @Test
    void testFindById() {
        Rating rating = new Rating();
        rating.setId(1);

        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        Rating result = ratingService.findById(1);

        assertEquals(1, result.getId());
        verify(ratingRepository).findById(1);
    }

    //Test findById lorsqu'on ne retrouve pas dans la db une cp avec cet id
    @Test
    void testFindById_whenIdNotFound() {
        when(ratingRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                ratingService.findById(1));
    }

    @Test
    void testSave() {
        Rating rating = new Rating();

        when(ratingRepository.save(rating)).thenReturn(rating);

        Rating result = ratingService.save(rating);

        assertNotNull(result);
        verify(ratingRepository).save(rating);
    }

    @Test
    void testDeleteById() {
        ratingService.deleteById(1);
        verify(ratingRepository).deleteById(1);
    }
}
