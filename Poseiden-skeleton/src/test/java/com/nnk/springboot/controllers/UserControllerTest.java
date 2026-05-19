package com.nnk.springboot.controllers;

import com.nnk.springboot.config.SecurityConfig;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.CustomUserDetailsService;
import com.nnk.springboot.services.UserService;
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
 * Classe de tests MVC dédiée au contrôleur {@link UserController}.
 *
 * <p>Cette classe vérifie le bon fonctionnement des routes HTTP, des validations,
 * des vues retournées et des règles de sécurité
 * associées aux utilisateurs.</p>
 */
@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
public class UserControllerTest {

    /**
     * Service mocké de gestion des utilisateurs.
     */
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Service mocké de gestion des utilisateurs.
     */
    @MockBean
    private UserService userService;

    /**
     * Objet MockMvc utilisé pour simuler les requêtes HTTP.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Vérifie l'affichage de la liste des utilisateurs pour un administrateur.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testList_Admin() throws Exception {

        User user = new User();

        user.setId(1);
        user.setUsername("username");
        user.setPassword("Password1!");
        user.setFullname("user name");
        user.setRole("ADMIN");

        when(userService.findAll()).thenReturn(List.of(user));

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"));
    }

    /**
     * Vérifie l'accès à la page d'ajout d'un utilisateur.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddUserPage_Admin() throws Exception {

        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    /**
     * Vérifie l'ajout valide d'un utilisateur.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddUser_Admin() throws Exception {

        mockMvc.perform(post("/user/validate")
                        .with(csrf())
                        .param("username", "username")
                        .param("password", "Password1!")
                        .param("fullname", "user name")
                        .param("role", "ADMIN"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

    /**
     * Vérifie le comportement du formulaire lors d'une validation invalide.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddUser_whenInvalidData_Admin() throws Exception {

        mockMvc.perform(post("/user/validate")
                        .with(csrf())
                        .param("username", "")
                        .param("password", "Password1!")
                        .param("fullname", "user name")
                        .param("role", "ADMIN"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    /**
     * Vérifie l'accès au formulaire de modification d'un utilisateur.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testShowUpdateForm_Admin() throws Exception {

        User user = new User();

        when(userService.findById(1)).thenReturn(user);

        mockMvc.perform(get("/user/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"))
                .andExpect(model().attributeExists("user"));
    }

    /**
     * Vérifie la mise à jour valide d'un utilisateur.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateUser_Admin() throws Exception {

        mockMvc.perform(post("/user/update/1")
                        .with(csrf())
                        .param("username", "username")
                        .param("password", "Password1!")
                        .param("fullname", "user name")
                        .param("role", "ADMIN"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

    /**
     * Vérifie le comportement du formulaire lors d'une mise à jour invalide.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateUser_whenInvalidData_Admin() throws Exception {

        mockMvc.perform(post("/user/update/1")
                        .with(csrf())
                        .param("username", "")
                        .param("password", "Password1!")
                        .param("fullname", "user name")
                        .param("role", "ADMIN"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"));
    }

    /**
     * Vérifie la suppression d'un utilisateur.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteUserById_Admin() throws Exception {

        mockMvc.perform(get("/user/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

    /**
     * Vérifie qu'un utilisateur standard ne peut pas accéder aux ressources administrateur.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    @WithMockUser(roles = "USER")
    void testUserAccessDenied_forUserRole() throws Exception {

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isForbidden());
    }

    /**
     * Vérifie qu'un utilisateur non authentifié est redirigé vers la page de connexion.
     *
     * @throws Exception en cas d'erreur lors du test MVC
     */
    @Test
    void testUserAccessDenied_withoutAuthentication() throws Exception {

        mockMvc.perform(get("/user/list"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/app/login"));
    }
}