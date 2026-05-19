package com.nnk.springboot.controllers;

import com.nnk.springboot.config.SecurityConfig;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.CustomUserDetailsService;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

/**
 * Classe de tests MVC dédiée au contrôleur {@link RatingController}.
 *
 * <p>Cette classe vérifie le bon fonctionnement des routes HTTP, des validations,
 * des vues retournées et des règles de sécurité associées aux Ratings.</p>
 */
@WebMvcTest(RatingController.class)
@Import(SecurityConfig.class)
public class RatingControllerTest {

    /**
     * Service mocké de gestion des utilisateurs.
     */
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Service mocké de gestion des Rating.
     */
    @MockBean
    private RatingService ratingService;

    /**
     * Objet MockMvc utilisé pour simuler les requêtes HTTP.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Vérifie l'affichage de la liste des notations financières.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testList_User() throws Exception{

        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("Moodys Rating");
        rating.setSandPRating("SandP Rating");
        rating.setFitchRating("Fitch Rating");
        rating.setOrderNumber(1);

        when(ratingService.findAll()).thenReturn(List.of(rating));

        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/list"))
                .andExpect(model().attributeExists("ratings"));
    }

    /**
     * Vérifie l'affichage d'ajout d'une notation financière.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testAddPage_User() throws Exception{

        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    /**
     * Vérifie l'ajout valide d'une notation financière.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testValidate_User() throws Exception {

        mockMvc.perform(post("/rating/validate")
                        .with(csrf())
                        .param("moodysRating", "moodys rating")
                        .param("sandPRating", "sandP rating")
                        .param("fitchRating", "fitch rating")
                        .param("orderNumber", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }

    /**
     * Vérifie le comportement du formulaire lors d'une validation invalide.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testValidate_withInvalidData_User() throws Exception {
        mockMvc.perform(post("/rating/validate")
                        .with(csrf())
                        .param("moodysRating", "")
                        .param("sandPRating", "sandP rating")
                        .param("fitchRating", "fitch eating")
                        .param("orderNumber", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    /**
     * Vérifie l'accès au formulaire de modification d'une notation financière.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testShowUpdateForm_User() throws Exception{
        Rating rating = new Rating();

        when(ratingService.findById(1)).thenReturn(rating);

        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"))
                .andExpect(model().attributeExists("rating"));
    }

    /**
     * Vérifie la mise à jour valide d'une notation financière.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testUpdateRating_User() throws Exception{
        mockMvc.perform(post("/rating/update/1")
                        .with(csrf())
                        .param("moodysRating", "moodys rating")
                        .param("sandPRating", "sandP rating")
                        .param("fitchRating", "fitch eating")
                        .param("orderNumber", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }

    /**
     * Vérifie le comportement du formulaire lors d'une mise à jour invalide.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testUpdateRating_whenInvalidData_User() throws Exception {
        mockMvc.perform(post("/rating/update/1")
                        .with(csrf())
                        .param("moodysRating", "")
                        .param("sandPRating", "sandP rating")
                        .param("fitchRating", "fitch eating"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"));
    }

    /**
     * Vérifie la suppression d'une notation financière.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testDeleteRating_User() throws Exception {

        mockMvc.perform(get("/rating/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }

    /**
     * Vérifie qu'un utilisateur non authentifié
     * est redirigé vers la page de connexion.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    void testRatingAccessDenied_withoutAuthentication() throws Exception {

        mockMvc.perform(get("/rating/list"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/app/login"));
    }
}
