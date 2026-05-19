package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
 * Classe de tests unitaires dédiée au service {@link RuleNameService}.
 *
 * <p>Vérifie le bon fonctionnement des opérations CRUD sur les entités {@link RuleName}.</p>
 */
@ExtendWith(MockitoExtension.class)
public class RuleNameServiceTest {

    /**
     * Repository mocké de gestion des RuleName.
     */
    @Mock
    private RuleNameRepository ruleNameRepository;

    /**
     * Service testé avec injection du mock.
     */
    @InjectMocks
    private RuleNameService ruleNameService;

    /**
     * Vérifie la récupération de toutes les RuleNames.
     */
    @Test
    void testFindAll(){
        RuleName ruleName = new RuleName();
        when(ruleNameRepository.findAll()).thenReturn(List.of(ruleName));

        List<RuleName> result = ruleNameService.findAll();

        assertFalse(result.isEmpty());
        verify(ruleNameRepository).findAll();
    }

    /**
     * Vérifie la récupération d'une RuleName via son identifiant unique.
     */
    @Test
    void testFindById(){
        RuleName ruleName = new RuleName();
        ruleName.setId(1);

        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));

        RuleName result = ruleNameService.findById(1);

        assertEquals(1, result.getId());
        verify(ruleNameRepository).findById(1);
    }

    /**
     * Vérifie qu'une exception est levée lorsqu'une RuleName n'est pas trouvé via l'identifiant donné.
     */
    @Test
    void testFindById_whenIdNotFound(){
        when(ruleNameRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, ()->
                ruleNameService.findById(1));
    }

    /**
     * Vérifie la sauvegarde d'une RuleName.
     */
    @Test
    void testSave() {
        RuleName ruleName = new RuleName();

        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);

        RuleName result = ruleNameService.save(ruleName);

        assertNotNull(result);
        verify(ruleNameRepository).save(ruleName);
    }

    /**
     * Vérifie la suppression d'une RuleName via son identifiant.
     */
    @Test
    void testDeleteById() {
        ruleNameService.deleteById(1);
        verify(ruleNameRepository).deleteById(1);
    }
}
