package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import com.nnk.springboot.services.CurvePointServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class CurveController {

    private final CurvePointServiceInterface curvePointService;

    public CurveController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        //Model injecte les données à la vue, car on donne à notre partie HTML {curvePoints} les données via findAll()
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint curvePoint) {
        //Spring crée un objet vide automatiquement car dans HTML on a th:object="${curvePoint}" et donc il faut un objet un binder
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        //valid donnes les éléments requis et leur format dans le formulaire (activation des validations)
        //Spring remplit automatiquement l'objet avec les données du formulaire
        //si erreur, on est renvoyé à la page d'ajout
        if (result.hasErrors()) {
            return "curvePoint/add";
        }
        //appel du service pour création/update du curvepoint
        //on envoie la liste des curvepoints (avec celle ajouté)
        //redirection vers la page liste
        curvePointService.save(curvePoint);
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        //méthode pour ouvrir le formulaire de la page mise à jour
        //ici on renseigne dans l'url l'id du curvepoint qu'on veut update avec pathvariable id
        //on récupère dans la db le curvepoint selon son id
        //on injecte dans le formulaire le curvePoint
        //on envoie vers la page update
        CurvePoint curvePoint = curvePointService.findById(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // mise en place de la mise à jour d'un curvepoint selon son id
        //on vérifie via valid (qui active les règles) que les champs respectent le format attendu
        //s'il y a une erreur on remet l'id du curvePoint (sinon perdu) et on retourne page update
        if (result.hasErrors()){
            curvePoint.setId(id);
            return "curvePoint/update";
        }
        //si modification Ok
        //on update le curvePoint avec l'id qu'il avait déjà (existant) car la méthode save reconnait id existant donc update au lieu de créer
        //on ajoute le curvepoint update à la vue
        //on redirige vers la page des listes
        curvePoint.setId(id);
        curvePointService.save(curvePoint);
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        // suppression d'un curvepoint en renseignant l'id
        //on supprime selon son id
        //on redirige vers la liste après deletion $
        //on redirige vers page liste
        curvePointService.deleteById(id);
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }
}
