package com.nnk.springboot.controllers;

import com.nnk.springboot.config.SecurityConfig;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.CustomUserDetailsService;
import com.nnk.springboot.services.TradeService;
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
 * Classe de tests MVC dédiée au contrôleur {@link TradeController}.
 *
 * <p>Cette classe vérifie le bon fonctionnement des routes HTTP, des validations,
 * des vues retournées et des règles de sécurité associées aux Trade.</p>
 */
@WebMvcTest(TradeController.class)
@Import(SecurityConfig.class)
public class TradeControllerTest {

    /**
     * Service mocké de gestion des utilisateurs.
     */
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Service mocké de gestion des Trade.
     */
    @MockBean
    private TradeService tradeService;

    /**
     * Objet MockMvc utilisé pour simuler les requêtes HTTP.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Vérifie l'affichage de la liste des transactions financières pour un utilisateur standard.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testList_User() throws Exception{
        Trade trade = new Trade();
        trade.setAccount("Trade Account");
        trade.setType("Type");

        when(tradeService.findAll()).thenReturn(List.of(trade));

        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/list"))
                .andExpect(model().attributeExists("trades"));

    }

    /**
     * Vérifie l'accès à la liste des transactions financières pour un administrateur.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testList_Admin() throws Exception {

        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk());
    }


    /**
     * Vérifie l'accès à la page d'ajout d'une transaction financière.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testAddTradePage_User() throws Exception{
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    /**
     * Vérifie l'ajout valide d'une transaction financière.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testAddTrade_User() throws Exception{
        mockMvc.perform(post("/trade/validate")
                        .with(csrf())
                        .param("account", "Trade Account")
                        .param("type", "Type"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

    /**
     * Vérifie le comportement du formulaire lors d'une validation invalide.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testAddTrade_whenInvalidData_User() throws Exception{
        mockMvc.perform(post("/trade/validate")
                        .with(csrf())
                        .param("account", "")
                        .param("type", "Type"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    /**
     * Vérifie l'accès au formulaire de modification d'une transaction financière.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testShowUpdateForm_User() throws Exception {
        Trade trade = new Trade();

        when(tradeService.findById(1)).thenReturn(trade);

        mockMvc.perform(get("/trade/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"))
                .andExpect(model().attributeExists("trade"));
    }

    /**
     * Vérifie la mise à jour valide d'une transaction financière.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testUpdateTrade_User() throws Exception {
        mockMvc.perform(post("/trade/update/1")
                        .with(csrf())
                        .param("account", "Trade Account")
                        .param("type", "Type"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

    /**
     * Vérifie le comportement du formulaire lors d'une mise à jour invalide.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testUpdateTrade_whenInvalidData_User() throws Exception {
        mockMvc.perform(post("/trade/update/1")
                        .with(csrf())
                        .param("account", "")
                        .param("type", "Type"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"));

    }

    /**
     * Vérifie la suppression d'une transaction financière.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testDeleteTradeById() throws Exception{
        mockMvc.perform(get("/trade/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

    /**
     * Vérifie qu'un utilisateur non authentifié est redirigé vers la page de connexion.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    void testTradeAccessDenied_withoutAuthentification() throws Exception{

        mockMvc.perform(get("/trade/list"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/app/login"));
    }
}
