package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests d'intégration dédiée aux opérations CRUD sur l'entité {@link Rating}.
 *
 * <p>Cette classe vérifie le bon fonctionnement des opérations de persistance via {@link RatingRepository}.</p>
 */
@SpringBootTest
public class RatingTests {

	/**
	 * Repository de gestion des notations financières.
	 */
	@Autowired
	private RatingRepository ratingRepository;

	/**
	 * Vérifie les opérations CRUD sur une notation financière.
	 */
	@Test
	public void ratingTest() {
		Rating rating = new Rating();
		rating.setMoodysRating("Moodys Rating");
		rating.setSandPRating("Sand PRating");
		rating.setFitchRating("Fitch Rating");
		rating.setOrderNumber(20);

		rating = ratingRepository.save(rating);
		assertNotNull(rating.getId());

		rating.setOrderNumber(20);
		rating = ratingRepository.save(rating);
		assertEquals(20, rating.getOrderNumber());

		List<Rating> listResult = ratingRepository.findAll();
		assertFalse(listResult.isEmpty());

		Integer id = rating.getId();
		ratingRepository.delete(rating);
		Optional<Rating> ratingList = ratingRepository.findById(id);
		assertFalse(ratingList.isPresent());
	}
}
