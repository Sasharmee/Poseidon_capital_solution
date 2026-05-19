package com.nnk.springboot.controllers;

import com.nnk.springboot.config.SecurityConfig;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.CustomUserDetailsService;
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
 * Classe de tests MVC dédiée au contrôleur {@link BidListController}.
 *
 * <p>Cette classe vérifie le bon fonctionnement des routes HTTP, des vues retournées,
 * des validations et des règles de sécurité associées aux BidList.</p>
 */
@WebMvcTest(BidListController.class)
@Import(SecurityConfig.class)
public class BidListControllerTest {

    /**
     * Service mocké de gestion des BidList.
     */
    @MockBean
    private BidListService bidListService;

    /**
     * Service mocké de gestion des utilisateurs.
     */
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Objet MVC utilisé pour simuler les requêtes MVC.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Vérifie l'affichage de la liste des BidList pour un administrateur.
     *
     * @throws Exception en cas d'erreur lors du test MVC.
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testList_Admin() throws Exception {
        BidList bid = new BidList();
        bid.setAccount("Bid Account");
        bid.setType("Type");

        when(bidListService.findAll()).thenReturn(List.of(bid));

        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attributeExists("bidLists"));
    }

    /**
     * Vérifie l'accès à la page d'ajout d'une BidList pour un administrateur.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddBidListPage_Admin() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    /**
     * Vérifie l'ajout valide d'une BidList.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddBidList_Admin() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                        .with(csrf())
                        .param("account", "Account")
                        .param("type", "Type"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    /**
     * Vérifie le comportement du formulaire lors d'une validation invalide.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddBidList_whenInvalidData_Admin() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                        .with(csrf())
                        .param("account", "")
                        .param("type", "Type"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    /**
     * Vérifie l'accès au formulaire de modification d'une BidList.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testShowUpdateForm_Admin() throws Exception {
        BidList bid = new BidList();

        when(bidListService.findById(1)).thenReturn(bid);

        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"))
                .andExpect(model().attributeExists("bidList"));
    }

    /**
     * Vérifie la mise à jour valide d'une BidList.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateBidList_Admin() throws Exception {
        mockMvc.perform(post("/bidList/update/1")
                        .with(csrf())
                        .param("account", "Account updated")
                        .param("type", "Type"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    /**
     * Vérifie le comportement du formulaire lors d'une mise à jour invalide.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateBidList_whenInvalidData_Admin() throws Exception {
        mockMvc.perform(post("/bidList/update/1")
                        .with(csrf())
                        .param("account", "")
                        .param("type", "Type"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }

    /**
     * Vérifie la suppression d'une BidList.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteBidListById_Admin() throws Exception {
        mockMvc.perform(get("/bidList/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    /**
     * Vérifie qu'un utilisateur standard peut accéder aux entrées.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testAccessAccepted_forUserRole() throws Exception {
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk());
    }

    /**
     * Vérifie qu'un utilisateur non authentifié est redirigé vers la page de connexion.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    void testAccessDenied_withoutAuthentification() throws Exception{
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/app/login"));
    }
}