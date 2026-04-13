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

@ExtendWith(MockitoExtension.class)
public class CurvePointServiceTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @InjectMocks
    private CurvePointService curvePointService;

    //TEST HAPPY PATH findAll(), on crée une cp, on crée la liste qui va contenir les cp grâce à la méthode findALl
    //On vérifie bien que la liste result n'est pas vide
    //On vérifie que le repository a bien effectué la méthode findAll
    @Test
    void testFindAll() {
        CurvePoint cp = new CurvePoint();
        when(curvePointRepository.findAll()).thenReturn(List.of(cp));

        List<CurvePoint> result = curvePointService.findAll();

        assertFalse(result.isEmpty());
        verify(curvePointRepository).findAll();
    }

    //TEST HAPPY PATH findById(), on crée une cp, on la recherche par son id grâce à son repository optional
    //On stocke dans notre objet result la cp retrouvée
    //On vérifie que l'id de la cp dans notre objet est bien 1 et on vérifie que le repository a été appelé et a utilisé la méthode findById()
    @Test
    void testFindById() {
        CurvePoint cp = new CurvePoint();
        cp.setId(1);

        when(curvePointRepository.findById(1)).thenReturn(Optional.of(cp));

        CurvePoint result = curvePointService.findById(1);

        assertEquals(1, result.getId());
        verify(curvePointRepository).findById(1);
    }

    //Test findById lorsqu'on ne retrouve pas dans la db une cp avec cet id
    @Test
    void testFindById_whenIdNotFound() {
        when(curvePointRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
        curvePointService.findById(1));
    }

    //On vérifie qu'on a bien save la nouvelle Cp créée
    @Test
    void testSave() {
        CurvePoint cp = new CurvePoint();

        when(curvePointRepository.save(cp)).thenReturn(cp);

        CurvePoint result = curvePointService.save(cp);

        assertNotNull(result);
        verify(curvePointRepository).save(cp);
    }
    //on vérifie que repository a bien delete by id 1
    @Test
    void testDeleteById() {
        curvePointService.deleteById(1);
        verify(curvePointRepository).deleteById(1);
    }
}
