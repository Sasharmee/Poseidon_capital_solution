package com.nnk.springboot.controllers;

import com.nnk.springboot.config.SecurityConfig;
import com.nnk.springboot.services.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Classe de tests MVC dédiée
 * au contrôleur {@link HomeController}.
 *
 * <p>Cette classe vérifie le bon fonctionnement
 * des routes publiques de l'application.</p>
 */
@WebMvcTest(HomeController.class)
@Import(SecurityConfig.class)
public class HomeControllerTest {

    /**
     * Service mocké de gestion des utilisateurs.
     */
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Objet MockMvc utilisé pour simuler
     * les requêtes HTTP.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Vérifie l'affichage
     * de la page d'accueil.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    void shouldDisplayHomePage() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}
