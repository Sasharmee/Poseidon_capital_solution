package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Contrôleur MVC dédié à la gestion de l'authentification et des accès sécurisés.
 *
 * <p>Cette classe permet notamment :</p>
 * <ul>
 *     <li>L'affichage de la page de connexion</li>
 *     <li>L'accès aux ressources sécurisées</li>
 *     <li>La gestion des erreurs d'autorisation</li>
 * </ul>
 */
@Controller
@RequestMapping("/app")
public class LoginController {

    /**
     * Repository de la gestion des utilisateurs.
     */
    private final UserRepository userRepository;

    /**
     * Constructeur du contrôleur d'authentification.
     *
     * @param userRepository repository des utilisateurs
     */
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Affiche la page de connexion de l'application.
     *
     * @return vue de connexion
     */
    @GetMapping("/login")
    public ModelAndView login() {

        ModelAndView mav = new ModelAndView();

        mav.setViewName("login");

        return mav;
    }

    /**
     * Affiche la liste des utilisateurs via une route sécurisée.
     *
     * @return vue sécurisée contenant la liste des utilisateurs
     */
    @GetMapping("/secure/article-details")
    public ModelAndView getAllUserArticles() {

        ModelAndView mav = new ModelAndView();

        mav.addObject("users", userRepository.findAll());

        mav.setViewName("user/list");

        return mav;
    }

    /**
     * Affiche la page d'erreur d'autorisation.
     *
     * @return vue d'erreur 403
     */
    @GetMapping("/error")
    public ModelAndView error() {

        ModelAndView mav = new ModelAndView();

        String errorMessage= "You are not authorized for the requested data.";

        mav.addObject("errorMsg", errorMessage);

        mav.setViewName("403");

        return mav;
    }
}
