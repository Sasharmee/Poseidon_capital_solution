package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TradeTests {

	@Autowired
	private TradeRepository tradeRepository;

	@Test
	public void tradeTest() {

		Trade trade = new Trade();

		trade.setAccount("Trade Account");
		trade.setType("Type");

		trade = tradeRepository.save(trade);

		assertNotNull(trade.getTradeId());

		List<Trade> listResult = tradeRepository.findAll();

		assertFalse(listResult.isEmpty());

		tradeRepository.delete(trade);

		List<Trade> trades = tradeRepository.findAll();

		assertFalse(trades.contains(trade));
	}
}