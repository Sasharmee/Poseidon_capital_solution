package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests d'intégration dédiée
 * aux opérations CRUD sur l'entité {@link Trade}.
 *
 * <p>Cette classe vérifie le bon fonctionnement
 * des opérations de persistance via
 * {@link TradeRepository}.</p>
 */
@SpringBootTest
public class TradeTests {

	/**
	 * Repository de gestion des transactions financières.
	 */
	@Autowired
	private TradeRepository tradeRepository;

	/**
	 * Vérifie les opérations CRUD
	 * sur une transaction financière.
	 */
	@Test
	public void tradeTest() {

		Trade trade = new Trade();
		trade.setAccount("Trade Account");
		trade.setType("Type");

		trade = tradeRepository.save(trade);
		assertNotNull(trade.getTradeId());
		assertEquals("Trade Account", trade.getAccount());

		trade.setAccount("Trade Account Updated");
		trade = tradeRepository.save(trade);
		assertEquals("Trade Account Updated", trade.getAccount());

		List<Trade> listResult = tradeRepository.findAll();
		assertFalse(listResult.isEmpty());

		Integer id = trade.getTradeId();
		tradeRepository.delete(trade);
		Optional<Trade> tradeList = tradeRepository.findById(id);
		assertFalse(tradeList.isPresent());
	}
}