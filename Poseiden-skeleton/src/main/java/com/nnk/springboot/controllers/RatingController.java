package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * Contrôleur MVC dédié à la gestion des notations financières.
 *
 * <p>Cette classe permet de gérer les opérations CRUD associées aux {@link Rating} dans l'application :</p>
 *
 * <ul>
 *     <li>Affichage de la liste des notations</li>
 *     <li>Ajout d'une nouvelle notation</li>
 *     <li>Mise à jour d'une notation existante</li>
 *     <li>Suppression d'une notation</li>
 * </ul>
 */
@Controller
public class RatingController {

    /**
     * Service de gestion des notations financières.
     */
    private final RatingService ratingService;

    /**
     * Constructeur du contrôleur des ratings.
     *
     * @param ratingService service de gestion des notations financières
     */
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    /**
     * Affiche la liste des notations financières.
     *
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return vue de la liste des notations
     */
    @RequestMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute("ratings", ratingService.findAll());
        return "rating/list";
    }

    /**
     * Affiche le formulaire d'ajout d'une notation.
     *
     * @param rating objet utilisé par le formulaire
     * @return vue du formulaire d'ajout
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    /**
     * Valide et enregistre une nouvelle notation financière.
     *
     * @param rating notation validée
     * @param result résultat de la validation
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste des notations ou vers le formulaire en cas d'erreur
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/add";
        }
        ratingService.save(rating);
        model.addAttribute("ratings", ratingService.findAll());
        return "redirect:/rating/list";
    }

    /**
     * Affiche le formulaire de modification d'une notation.
     *
     * @param id identifiant de la notation à modifier
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return vue du formulaire de mise à jour
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    /**
     * Mise à jour d'une notation financière.
     *
     * @param id identifiant unique de la notation à modifier
     * @param rating données mise à jour
     * @param result résultat de la validation
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste ou retour au formulaire en cas d'erreur
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (result.hasErrors()){
            ratingService.findById(id);
            rating.setId(id);
            return "rating/update";
        }
        rating.setId(id);
        ratingService.save(rating);
        model.addAttribute("ratings", ratingService.findAll());
        return "redirect:/rating/list";
    }

    /**
     * Supprime une notation financière existante.
     *
     * @param id identifiant de la notation à supprimer
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste des notations
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.deleteById(id);
        model.addAttribute("ratings", ratingService.findAll());
        return "redirect:/rating/list";
    }
}
