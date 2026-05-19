package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * Contrôleur MVC dédié à la gestion
 * des utilisateurs de l'application.
 *
 * <p>Cette classe permet de gérer les opérations CRUD
 * associées aux {@link User} ainsi que la sécurisation
 * des mots de passe utilisateurs.</p>
 *
 * <ul>
 *     <li>Affichage de la liste des utilisateurs</li>
 *     <li>Ajout d'un nouvel utilisateur</li>
 *     <li>Mise à jour d'un utilisateur existant</li>
 *     <li>Suppression d'un utilisateur</li>
 * </ul>
 */
@Controller
public class UserController {

    /**
     * Service de gestion des utilisateurs.
     */
    private final UserService userService;

    /**
     * Constructeur du contrôleur des utilisateurs.
     *
     * @param userService service de gestion des utilisateurs
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Affiche la liste des utilisateurs.
     *
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return vue de la liste des utilisateurs
     */
    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    /**
     * Affiche le formulaire d'ajout d'un utilisateur.
     *
     * @param user objet utilisé par le formulaire
     * @return vue du formulaire d'ajout
     */
    @GetMapping("/user/add")
    public String addUser(User user) {
        return "user/add";
    }

    /**
     * Valide et enregistre un nouvel utilisateur.
     *
     * <p>Le mot de passe est automatiquement
     * chiffré avant l'enregistrement en base
     * de données.</p>
     *
     * @param user utilisateur validé
     * @param result résultat de la validation
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste ou retour au formulaire en cas d'erreur
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userService.save(user);
            model.addAttribute("users", userService.findAll());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    /**
     * Affiche le formulaire de modification d'un utilisateur.
     *
     * @param id identifiant de l'utilisateur à modifier
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return vue du formulaire de mise à jour
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Met à jour un utilisateur existant.
     *
     * <p>Le mot de passe est chiffré avant
     * l'enregistrement des modifications.</p>
     *
     * @param id identifiant de l'utilisateur
     * @param user données mises à jour
     * @param result résultat de la validation
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste ou retour au formulaire en cas d'erreur
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    /**
     * Supprime un utilisateur existant.
     *
     * @param id identifiant de l'utilisateur à supprimer
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste des utilisateurs
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.deleteById(id);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }
}
