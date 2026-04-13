package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@WebMvcTest(RatingController.class)
public class RatingControllerTest {

    @MockBean
    private RatingServiceInterface ratingService;

    @Autowired
    private MockMvc mockMvc;

    //GET List: on crée dans un premier temps un objet de test
    //On se rend sur l'url /curvePoint/list
    //On vérifie que la requête est ok, qu'on a la bonne view et qu'on a bien la liste
    @Test
    void testList() throws Exception{
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

    //TEST GET page add, on vérifie que lorsqu'on fait la requête tout est ok et on récupère la bonne vue
    @Test
    void testAddPage() throws Exception{
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    //TEST ADD une nouvelle cp avec respect des champs et redirection vers list après ajout
    @Test
    void testValidate() throws Exception {
        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "moodys rating")
                        .param("sandPRating", "sandP rating")
                        .param("fitchRating", "fitch eating")
                        .param("order", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }

    //TEST ADD avec non-respect d'un champ et retour vers la page d'ajout
    @Test
    void testValidate_withInvalidData() throws Exception {
        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "")
                        .param("sandPRating", "sandP rating")
                        .param("fitchRating", "fitch eating")
                        .param("order", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }
    //TEST GET formulaire de mise à jour d'une cp
    @Test
    void testShowUpdateForm() throws Exception{
        //On crée un rating afin de pouvoir en avoir une qu'on puisse modifier
        Rating rating = new Rating();

        when(ratingService.findById(1)).thenReturn(rating);

        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"))
                .andExpect(model().attributeExists("rating"));
    }

    //TEST POST mise à jour réussie d'une cp
    @Test
    void testUpdateRating() throws Exception{
        mockMvc.perform(post("/rating/update/1")
                        .param("moodysRating", "moodys rating")
                        .param("sandPRating", "sandP rating")
                        .param("fitchRating", "fitch eating")
                        .param("order", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }

    //TEST POST lors que les données mise à jour ne respectent pas le format donc on retourne vers le formulaire
    @Test
    void testUpdateRating_whenInvalidData() throws Exception {
        mockMvc.perform(post("/rating/update/1")
                        .param("moodysRating", "")
                        .param("sandPRating", "sandP rating")
                        .param("fitchRating", "fitch eating"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"));
    }

    //TEST GET lorsqu'on supprime un rating
    @Test
    void testDeleteRating() throws Exception {
        mockMvc.perform(get("/rating/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }
}
