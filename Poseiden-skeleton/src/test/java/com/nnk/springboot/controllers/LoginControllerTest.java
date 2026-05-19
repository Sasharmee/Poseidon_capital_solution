package com.nnk.springboot.controllers;

import com.nnk.springboot.config.SecurityConfig;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.CustomUserDetailsService;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Classe de tests MVC dédiée
 * au contrôleur {@link LoginController}.
 *
 * <p>Cette classe vérifie le bon fonctionnement
 * des routes liées à l'authentification,
 * aux accès sécurisés et à la gestion
 * des erreurs d'autorisation.</p>
 */
@WebMvcTest(LoginController.class)
@Import(SecurityConfig.class)
public class LoginControllerTest {

    /**
     * Service mocké de gestion des utilisateurs.
     */
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Objet MockMvc utilisé pour simuler les requêtes HTTP.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Repository mocké de gestion des utilisateurs.
     */
    @MockBean
    private UserRepository userRepository;

    /**
     * Vérifie l'affichage de la page de connexion.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    void testLoginPage() throws Exception {
        mockMvc.perform(get("/app/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    /**
     * Vérifie qu'un administrateur authentifié peut accéder aux ressources sécurisées.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetAllUserArticles_Admin() throws Exception {

        User user = new User();
        user.setUsername("admin");

        when(userRepository.findAll()).thenReturn(List.of(user));

        mockMvc.perform(get("/app/secure/article-details"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"));
    }

    /**
     * Vérifie qu'un utilisateur standard ne peut pas accéder aux ressources administrateur.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetAllUserArticles_UserAccessDenied() throws Exception {
        mockMvc.perform(get("/app/secure/article-details"))
                .andExpect(status().isForbidden());
    }

    /**
     * Vérifie l'affichage de la page d'erreur d'autorisation.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    void testErrorPage() throws Exception{

        mockMvc.perform(get("/app/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("403"))
                .andExpect(model().attributeExists("errorMsg"));
    }

    /**
     * Vérifie qu'un utilisateur non authentifié est redirigé vers la page de connexion.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    void testGetAllUserArticles_withoutAuthentication() throws Exception {

        mockMvc.perform(get("/app/secure/article-details"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/app/login"));
    }
}
