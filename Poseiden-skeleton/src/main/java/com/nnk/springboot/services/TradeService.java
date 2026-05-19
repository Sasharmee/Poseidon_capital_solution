package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation du service de gestion des opérations financières (trade).
 *
 * <p>Cette classe assure les opérations financières d'accès et de gestion des {@link Trade}
 * via le repository {@link TradeRepository}.</p>
 */
@Service
public class TradeService implements TradeServiceInterface{

    /**
     * Repository des opérations financières.
     */
    private final TradeRepository tradeRepository;

    /**
     * Constructeur du service de gestion des opérations financières.
     *
     * @param tradeRepository repository des opérations financières
     */
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trade findById(Integer id) {
        Optional<Trade> trade = tradeRepository.findById(id);
        return trade.orElseThrow(()-> new IllegalArgumentException("Invalid trade id " + id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Integer id) {
        tradeRepository.deleteById(id);
    }
}
