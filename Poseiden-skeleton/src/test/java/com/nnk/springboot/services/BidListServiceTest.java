package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
 * Classe de tests unitaires dédiée au service {@link BidListService}.
 *
 * <p>Cette classe vérifie le bon fonctionnement des opérations métier associées aux BidList.</p>
 */
@ExtendWith(MockitoExtension.class)
public class BidListServiceTest {

    /**
     * Service testé avec injection des mocks.
     */
    @Mock
    private BidListRepository bidListRepository;

    /**
     * Service testé avec injection des mocks.
     */
    @InjectMocks
    private BidListService bidListService;

    /**
     * Vérifie la récupération de toutes les BidList.
     */
    @Test
    void testFindAll() {
        BidList bidList = new BidList();
        when(bidListRepository.findAll()).thenReturn(List.of(bidList));

        List<BidList> result = bidListService.findAll();

        assertFalse(result.isEmpty());
        verify(bidListRepository).findAll();

    }

    /**
     * Vérifie la récupération d'une BidList par son identifiant unique.
     */
    @Test
    void testFindById() {
        BidList bidList = new BidList();
        bidList.setBidListId(1);

        when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));

        BidList result = bidListService.findById(1);

        assertEquals(1, result.getBidListId());
        verify(bidListRepository).findById(1);
    }

    /**
     * Vérifie qu'une exception est levée lorsqu'une BidList est introuvable.
     */
    @Test
    void testFindById_whenIdNotFound() {
        when(bidListRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, ()->
                bidListService.findById(1));
    }

    /**
     * Vérifie la sauvegarde d'une BidList.
     */
    @Test
    void testSave() {
        BidList bidList = new BidList();

        when(bidListRepository.save(bidList)).thenReturn(bidList);

        BidList result = bidListService.save(bidList);

        assertNotNull(result);
        verify(bidListRepository).save(bidList);
    }

    /**
     * Vérifie la suppression d'une BidList par son identifiant.
     */
    @Test
    void testDeleteBidListById() {
        bidListService.deleteById(1);
        verify(bidListRepository).deleteById(1);
    }
}
