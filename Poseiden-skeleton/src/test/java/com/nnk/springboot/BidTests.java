package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BidTests {

	@Autowired
	private BidListRepository bidListRepository;

	@Test
	public void bidListTest() {

		BidList bid = new BidList();

		bid.setAccount("Account Test");
		bid.setType("Type Test");
		bid.setBidQuantity(10.0);

		bid = bidListRepository.save(bid);

		assertNotNull(bid.getBidListId());

		List<BidList> listResult = bidListRepository.findAll();

		assertFalse(listResult.isEmpty());

		bidListRepository.delete(bid);

		List<BidList> bidLists = bidListRepository.findAll();

		assertFalse(bidLists.contains(bid));
	}
}