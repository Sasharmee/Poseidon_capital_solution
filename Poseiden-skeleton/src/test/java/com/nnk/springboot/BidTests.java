package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests d'intégration dédiée
 * aux opérations CRUD sur l'entité {@link BidList}.
 *
 * <p>Cette classe vérifie le bon fonctionnement
 * des opérations de persistance via
 * {@link BidListRepository}.</p>
 */
@SpringBootTest
public class BidTests {

	/**
	 * Repository de gestion des BidList.
	 */
	@Autowired
	private BidListRepository bidListRepository;

	/**
	 * Vérifie les opérations CRUD
	 * sur une BidList.
	 */
	@Test
	public void bidListTest() {

		BidList bid = new BidList();

		bid.setAccount("Account Test");
		bid.setType("Type Test");
		bid.setBidQuantity(10.0);

		bid = bidListRepository.save(bid);
		assertNotNull(bid.getBidListId());
		assertEquals("Account Test", bid.getAccount());

		bid.setBidQuantity(20.0);
		bid = bidListRepository.save(bid);
		assertEquals(20.0, bid.getBidQuantity());

		List<BidList> listResult = bidListRepository.findAll();
		assertFalse(listResult.isEmpty());

		Integer id = bid.getBidListId();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		assertFalse(bidList.isPresent());
	}
}