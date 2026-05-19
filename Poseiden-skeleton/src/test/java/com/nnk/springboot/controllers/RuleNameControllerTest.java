package com.nnk.springboot.controllers;

import com.nnk.springboot.config.SecurityConfig;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.CustomUserDetailsService;
import com.nnk.springboot.services.RuleNameService;
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
 * Classe de tests MVC dédiée
 * au contrôleur {@link RuleNameController}.
 *
 * <p>Cette classe vérifie le bon fonctionnement
 * des routes HTTP, des validations,
 * des vues retournées et des règles de sécurité
 * associées aux RuleName.</p>
 */
@WebMvcTest(RuleNameController.class)
@Import(SecurityConfig.class)
public class RuleNameControllerTest {

    /**
     * Service mocké de gestion des utilisateurs.
     */
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Service mocké de gestion des RuleName.
     */
    @MockBean
    private RuleNameService ruleNameService;

    /**
     * Objet MockMvc utilisé pour simuler les requêtes HTTP.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Vérifie l'affichage de la liste des règles métier.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testList_Admin() throws Exception{
        RuleName ruleName = new RuleName();
        ruleName.setId(1);

        when(ruleNameService.findAll()).thenReturn(List.of(ruleName));

        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"))
                .andExpect(model().attributeExists("ruleNames"));
    }

    /**
     * Vérifie l'accès à la page d'ajout d'une règle métier
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddPage_Admin() throws Exception {
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    /**
     * Vérifie l'ajout valide d'une règle métier.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddRuleName_Admin() throws Exception{
        mockMvc.perform(post("/ruleName/validate")
                        .with(csrf())
                .param("name", "Rule Name")
                .param("description", "description")
                .param("json", "json")
                .param("template", "Template")
                .param("sql", "SQL")
                .param("sqlPart", "SQL Part"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    /**
     * Vérifie le comportement du formulaire lors d'une validation invalide.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddRuleName_whenInvalidData_Admin() throws Exception{
        mockMvc.perform(post("/ruleName/validate")
                        .with(csrf())
                .param("name", "")
                .param("description", "description")
                .param("json", "json")
                .param("template", "Template")
                .param("sql", "SQL")
                .param("sqlPart", "SQL Part"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    /**
     * Vérifie l'accès au formulaire de modification d'une règle métier.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testShowUpdateForm_Admin() throws Exception{

        RuleName ruleName = new RuleName();

        when(ruleNameService.findById(1)).thenReturn(ruleName);

        mockMvc.perform(get("/ruleName/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"))
                .andExpect(model().attributeExists("ruleName"));
    }

    /**
     * Vérifie la mise à jour valide d'une règle métier.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateRuleName_Admin() throws Exception{
        mockMvc.perform(post("/ruleName/update/1")
                        .with(csrf())
                        .param("name", "Rule Name")
                        .param("description", "description of the Rule Name")
                        .param("json", "json")
                        .param("template", "Template")
                        .param("sql", "SQL")
                        .param("sqlPart", "SQL Part"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    /**
     * Vérifie le comportement du formulaire lors d'une mise à jour invalide.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateRuleName_whenInvalidData_Admin() throws Exception{
        mockMvc.perform(post("/ruleName/update/1")
                        .with(csrf())
                        .param("name", "")
                        .param("description", "description")
                        .param("json", "json")
                        .param("template", "Template")
                        .param("sql", "SQL")
                        .param("sqlPart", "SQL Part"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"));
    }

    /**
     * Vérifie la suppression d'une règle métier.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteRuleName_Admin() throws Exception{
        mockMvc.perform(get("/ruleName/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    /**
     * Vérifie qu'un utilisateur standard ne peut accéder aux ressources administrateur.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testRuleNameAccessDenied_forUserRole() throws Exception{

        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isForbidden());
    }

    /**
     * Vérifie qu'un utilisateur non authentifié est redirigé vers la page de connexion.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    void testRuleNameAccessDenied_withoutAuthentification() throws Exception {

        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/app/login"));
    }

}

