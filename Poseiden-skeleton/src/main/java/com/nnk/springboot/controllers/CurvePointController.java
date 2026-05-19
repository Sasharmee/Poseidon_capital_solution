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

/**
 * Contrôleur MVC dédié à la gestion des points de courbe financière.
 *
 * <p>Cette classe permet de gérer les opérations CRUD associées aux {@link CurvePoint} dans l'application.</p>
 *
 * <ul>
 *     <li>Affichage de la liste des points de courbe</li>
 *     <li>Ajout d'un nouveau point de courbe</li>
 *     <li>Mise à jour d'un point de courbe existant</li>
 *     <li>Suppression d'un point de courbe</li>
 * </ul>
 */
@Controller
public class CurvePointController {

    /**
     * Service de gestion des points de courbe.
     */
    private final CurvePointService curvePointService;

    /**
     * Constructeur du contrôleur de CurvePoint.
     *
     * @param curvePointService service de gestion des points de courbe.
     */
    public CurvePointController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    /**
     * Affichage de la liste des points de courbe.
     *
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return vue de la liste des points de courbe
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "curvePoint/list";
    }

    /**
     * Affiche le formulaire d'ajout d'un point de courbe.
     *
     * @param curvePoint objet utilisé par le formulaire
     * @return vue du formulaire d'ajout
     */
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    /**
     * Valide et enregistre un nouveau point de courbe.
     *
     * @param curvePoint point de courbe validé
     * @param result résultat de la validation
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste ou retour au formulaire en cas d'erreur
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/add";
        }
        curvePointService.save(curvePoint);
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }

    /**
     * Affiche le formulaire de modification d'un point de courbe.
     *
     * @param id identifiant du point à modifier
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return vue du formulaire de mise à jour
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findById(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    /**
     * Met à jour un point de courbe existant.
     *
     * @param id identifiant du point de courbe
     * @param curvePoint données mises à jour
     * @param result résultat de la validation
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste ou vers le formulaire en cas d'erreur
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        if (result.hasErrors()){
            curvePoint.setId(id);
            return "curvePoint/update";
        }
        curvePoint.setId(id);
        curvePointService.save(curvePoint);
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }

    /**
     * Supprime un point de courbe existant.
     *
     * @param id identifiant du point de courbe à supprimer
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste des points de courbe
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        curvePointService.deleteById(id);
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "redirect:/curvePoint/list";
    }
}
