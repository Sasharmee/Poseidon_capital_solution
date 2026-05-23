package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
 * Classe de tests unitaires dédiée au service {@link CurvePointService}.
 *
 * <p>Cette classe vérifie le bon fonctionnement des opérations métier associées aux CurvePoint.</p>
 */
@ExtendWith(MockitoExtension.class)
public class CurvePointServiceTest {

    /**
     * Repository mocké de gestion des CurvePoint.
     */
    @Mock
    private CurvePointRepository curvePointRepository;

    /**
     * Service testé avec injections des mocks.
     */
    @InjectMocks
    private CurvePointService curvePointService;

    /**
     * Vérifie la récupération de toutes les courbes.
     */
    @Test
    void testFindAll() {
        CurvePoint cp = new CurvePoint();
        when(curvePointRepository.findAll()).thenReturn(List.of(cp));

        List<CurvePoint> result = curvePointService.findAll();

        assertFalse(result.isEmpty());
        verify(curvePointRepository).findAll();
    }

    /**
     * Vérifie la récupération d'une courbe via son identifiant unique.
     */
    @Test
    void testFindById() {
        CurvePoint cp = new CurvePoint();
        cp.setId(1);

        when(curvePointRepository.findById(1)).thenReturn(Optional.of(cp));

        CurvePoint result = curvePointService.findById(1);

        assertEquals(1, result.getId());
        verify(curvePointRepository).findById(1);
    }

    /**
     * Vérifie la récupération des CurvePoint
     * à partir d'un curveId spécifique.
     */
    @Test
    void testFindByCurveId() {

        CurvePoint curvePoint1 = new CurvePoint();
        curvePoint1.setCurveId(10);
        curvePoint1.setTerm(10.0);
        curvePoint1.setValue(30.0);

        CurvePoint curvePoint2 = new CurvePoint();
        curvePoint2.setCurveId(10);
        curvePoint2.setTerm(20.0);
        curvePoint2.setValue(40.0);

        when(curvePointRepository.findByCurveId(10))
                .thenReturn(List.of(curvePoint1, curvePoint2));

        List<CurvePoint> result =
                curvePointService.findByCurveId(10);

        assertEquals(2, result.size());

        assertTrue(
                result.stream()
                        .allMatch(cp -> cp.getCurveId().equals(10))
        );

        verify(curvePointRepository).findByCurveId(10);
    }

    /**
     * Vérifie le comportement lorsque
     * aucun CurvePoint n'est trouvé
     * pour un curveId donné.
     */
    @Test
    void testFindByCurveId_whenNoCurvePointFound() {

        when(curvePointRepository.findByCurveId(99))
                .thenReturn(List.of());

        List<CurvePoint> result =
                curvePointService.findByCurveId(99);

        assertTrue(result.isEmpty());

        verify(curvePointRepository).findByCurveId(99);
    }


    /**
     * Vérifie qu'une exception est levée lorsque aucune courbe n'est trouvée via l'identifiant donné.
     */
    @Test
    void testFindById_whenIdNotFound() {
        when(curvePointRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
        curvePointService.findById(1));
    }

    /**
     * Vérifie la sauvegarde d'une courbe.
     */
    @Test
    void testSave() {
        CurvePoint cp = new CurvePoint();

        when(curvePointRepository.save(cp)).thenReturn(cp);

        CurvePoint result = curvePointService.save(cp);

        assertNotNull(result);
        verify(curvePointRepository).save(cp);
    }

    /**
     * Vérifie la suppression d'une courbe via son identifiant.
     */
    @Test
    void testDeleteById() {
        curvePointService.deleteById(1);
        verify(curvePointRepository).deleteById(1);
    }
}
