package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
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
 * Classe de tests unitaires dédiée au service {@link UserService}.
 *
 * <p>Vérifie le bon fonctionnement des opérations CRUD sur les entités {@link User}.</p>
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    /**
     * Repository mocké de gestion des User.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * Service testé avec injection du mock.
     */
    @InjectMocks
    private UserService userService;

    /**
     * Vérifie la récupération de tous les utilisateurs.
     */
    @Test
    void testFindAll(){
        User user = new User();
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> result = userService.findAll();

        assertFalse(result.isEmpty());
        verify(userRepository).findAll();
    }

    /**
     * Vérifie la récupération d'un utilisateur par son identifiant.
     */
    @Test
    void testFindById() {
        User user = new User();
        user.setId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.findById(1);

        assertEquals(1, result.getId());
        verify(userRepository).findById(1);
    }

    /**
     * Vérifie qu'une exception est levée si aucun utilisateur n'est trouvé pour l'identifiant donné.
     */
    @Test
    void testFindById_whenIdNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, ()->
                userService.findById(1));
    }

    /**
     * Vérifie la sauvegarde d'un utilisateur.
     */
    @Test
    void testSave() {
        User user = new User();

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.save(user);

        assertNotNull(result);
        verify(userRepository).save(user);
    }

    /**
     * Vérifie la suppression d'un utilisateur par son identifiant.
     */
    @Test
    void testDeleteById() {
        userService.deleteById(1);
        verify(userRepository).deleteById(1);
    }
}
