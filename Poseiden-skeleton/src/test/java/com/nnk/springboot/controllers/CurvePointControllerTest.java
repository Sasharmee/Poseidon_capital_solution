package com.nnk.springboot.controllers;

import com.nnk.springboot.config.SecurityConfig;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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

/**
 * Classe de tests MVC dédiée au contrôleur {@link CurvePointController}.
 *
 * <p>Cette classe vérifie le bon fonctionnement des routes HTTP, des validations,
 * des vues retournées et des règles de sécurité associées aux CurvePoint.</p>
 */
@WebMvcTest(CurvePointController.class)
@Import(SecurityConfig.class)
public class CurvePointControllerTest {

    /**
     * Service mocké de gestion des CurvePoint.
     */
    @MockBean
    private CurvePointService curvePointService;

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
     * Vérifie l'affichage de la liste des points de courbe pour un administrateur.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testList_Admin() throws Exception{
        CurvePoint cp = new CurvePoint();
        cp.setId(1);
        cp.setCurveId(10);
        cp.setTerm(10.0);
        cp.setValue(30.0);

        when(curvePointService.findAll()).thenReturn(List.of(cp));

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attributeExists("curvePoints"));
    }

    /**
     * Vérifie l'accès à la page d'ajout d'une CurvePoint.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddPage_Admin() throws Exception{
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    /**
     * Vérifie l'ajout valide d'un point de courbe.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testValidate_Admin() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                        .with(csrf())
                        .param("curveId", "10")
                        .param("term", "20.0")
                        .param("value", "30.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    /**
     * Vérifie le comportement du formulaire lors d'une validation invalide.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testValidate_withInvalidData_Admin() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                        .with(csrf())
                        .param("curveId", "")
                        .param("term", "20.0")
                        .param("value", "30.0"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    /**
     * Vérifie l'accès au formulaire
     * de modification d'un CurvePoint.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testShowUpdateForm_Admin() throws Exception{
        CurvePoint cp = new CurvePoint();
        cp.setId(1);

        when(curvePointService.findById(1)).thenReturn(cp);

        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeExists("curvePoint"));
    }

    /**
     * Vérifie la mise à jour valide
     * d'un point de courbe.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateCurvePoint_Admin() throws Exception{
        mockMvc.perform(post("/curvePoint/update/1")
                        .with(csrf())
                        .param("curveId", "10")
                        .param("term", "50.0")
                        .param("value", "30.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    /**
     * Vérifie le comportement du formulaire
     * lors d'une mise à jour invalide.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateCurvePoint_whenInvalidData_Admin() throws Exception {
        mockMvc.perform(post("/curvePoint/update/1")
                        .with(csrf())
                        .param("curveId", "")
                        .param("term", "50.0")
                        .param("value", "30.0"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }

    /**
     * Vérifie la suppression
     * d'un point de courbe.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteCurvePoint_Admin() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    /**
     * Vérifie qu'un utilisateur standard peut accéder aux courbes.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testAccessAccepted_forUserRole() throws Exception {
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk());
    }

    /**
     * Vérifie qu'un utilisateur non authentifié
     * est redirigé vers la page de connexion.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    void testAccessDenied_withoutAuthentication() throws Exception {
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/app/login"));
    }
}
