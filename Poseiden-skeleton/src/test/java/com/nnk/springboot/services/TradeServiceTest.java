package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
 * Classe de tests unitaires dédiée au service {@link TradeService}.
 *
 * <p>Vérifie le bon fonctionnement des opérations CRUD sur les entités {@link Trade}.</p>
 */
@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {

    /**
     * Repository mocké de gestion des Trade.
     */
    @Mock
    private TradeRepository tradeRepository;

    /**
     * Service testé avec injection du mock.
     */
    @InjectMocks
    private TradeService tradeService;

    /**
     * Vérifie la récupération de tous les Trades.
     */
    @Test
    void testFindAll() {
        Trade trade = new Trade();
        when(tradeRepository.findAll()).thenReturn(List.of(trade));

        List<Trade> result = tradeService.findAll();

        assertFalse(result.isEmpty());
        verify(tradeRepository).findAll();
    }

    /**
     * Vérifie la récupération d'un Trade via son identifiant.
     */
    @Test
    void testFindById() {
        Trade trade = new Trade();
        trade.setTradeId(1);

        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        Trade result = tradeService.findById(1);

        assertEquals(1, result.getTradeId());
        verify(tradeRepository).findById(1);
    }

    /**
     * Vérifie qu'une exception est levée si aucun Trade n'est trouvé pour l'identifiant donné.
     */
    @Test
    void testFindById_whenIdNotFound() {
        when(tradeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, ()->
                tradeService.findById(1));
    }

    /**
     * Vérifie la sauvegarde d'un Trade.
     */
    @Test
    void testSave() {
        Trade trade = new Trade();

        when(tradeRepository.save(trade)).thenReturn(trade);

        Trade result = tradeService.save(trade);

        assertNotNull(result);
        verify(tradeRepository).save(trade);
    }

    /**
     * Vérifie la suppression d'un Trade par son identifiant.
     */
    @Test
    void testDeleteById() {
        tradeService.deleteById(1);
        verify(tradeRepository).deleteById(1);
    }
}
