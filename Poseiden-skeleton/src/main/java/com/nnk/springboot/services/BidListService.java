package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation du service de gestion des opérations liées aux {@link BidList}
 *
 * <p>Cette classe assure l'accès et la gestion des entrées en s'appuyant sur le repository {@link BidListRepository}.</p>
 */
@Service
public class BidListService implements BidListServiceInterface{

    /**
     * Repository de gestion des entrées.
     */
    private final BidListRepository bidListRepository;

    /**
     * Constructeur du service de gestion des Bidlist.
     *
     * @param bidListRepository repository des entrées de cotation
     */
    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BidList findById(Integer id) {
        Optional<BidList> bidList = bidListRepository.findById(id);
        return bidList.orElseThrow(()->new IllegalArgumentException("Invalid BidList id " + id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BidList save(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Integer id) {
        bidListRepository.deleteById(id);
    }
}
