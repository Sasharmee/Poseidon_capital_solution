package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Classe de tests unitaires dédiée au service {@link CustomUserDetailsService}.
 *
 * <p>Vérifie le bon fonctionnement de la récupération d'un utilisateur
 * et la conversion en {@link UserDetails} pour Spring Security.</p>
 */
@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    /**
     * Repository mocké de gestion des utilisateurs.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * Service testé avec injection du mock.
     */
    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Vérifie que l'utilisateur est correctement récupéré et que son rôle est bien converti en authorities.
     */
    @Test
    void testLoadUserByUsername() {

        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setRole("ADMIN");

        when(userRepository.findByUsername("admin")).thenReturn(user);

        UserDetails result = customUserDetailsService.loadUserByUsername("admin");

        assertEquals("admin", result.getUsername());
        assertEquals("password", result.getPassword());

        assertTrue(result.getAuthorities()
                .stream()
                .anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"))
        );

    }

    /**
     * Vérifie qu'une exception est levée lorsqu'un utilisateur n'est pas trouvé dans la base.
     */
    @Test
    void testLoadUserByUsername_whenUserNotFound() {

        when(userRepository.findByUsername("unknown")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, ()->customUserDetailsService.loadUserByUsername("unknown"));
    }


}
