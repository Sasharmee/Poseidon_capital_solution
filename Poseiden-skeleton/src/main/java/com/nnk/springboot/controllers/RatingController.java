package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class RatingController {

    private final RatingServiceInterface ratingService;

    public RatingController(RatingServiceInterface ratingService) {
        this.ratingService = ratingService;
    }

    //Model injecte les données à la vue directement via findAll()
    @RequestMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute("ratings", ratingService.findAll());
        return "rating/list";
    }

    //Spring crée un objet vide automatiquement car dans HTML on a th:object="${rating}" et donc il faut un objet un binder
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    //Valid donne les élements requis et leur format dans leur formulaire (valid active les validations)
    //Spring remplit automatiquement notre objet via les informations renseignés dans le formulaire
    //si erreur, on est envoyé vers la page d'ajout
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/add";
        }
        //appel de service pour ajouter le rating avec la méthode save
        //on renvoie la liste à la view grâce au model (avec le nouveau rating)
        //redirection vers la page list
        ratingService.save(rating);
        model.addAttribute("ratings", ratingService.findAll());
        return "redirect:/rating/list";
    }

    //méthode pour accéder à la page update de rating
    //on renseigne l'id du rating à modifier dans l'url avec pathVariable
    //on récupère dans la db le rating via son id
    //on injecte dans la view
    //on envoie vers la page update
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

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

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.deleteById(id);
        model.addAttribute("ratings", ratingService.findAll());
        return "redirect:/rating/list";
    }
}
