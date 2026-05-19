package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * Contrôleur MVC dédié à la gestion des règles métier configurables.
 *
 * <p>Cette classe permet de gérer les opérations CRUD associées aux {@link RuleName} dans l'application :</p>
 * <ul>
 *     <li>Affichage de la liste des règles métier</li>
 *     <li>Ajout d'une nouvelle règle</li>
 *     <li>Mise à jour d'une règle existante</li>
 *     <li>Suppression d'une règle métier</li>
 * </ul>
 */
@Controller
public class RuleNameController {

    /**
     * Service de gestion des règles métier.
     */
    private final RuleNameService ruleNameService;

    /**
     * Constructeur du contrôleur des RuleName.
     *
     * @param ruleNameService Service de gestion des règles métier
     */
    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    /**
     * Affiche les règles métier.
     *
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return vue de la liste des règles métier
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        model.addAttribute("ruleNames", ruleNameService.findAll());
        return "ruleName/list";
    }

    /**
     * Affiche le formulaire d'ajout d'une règle métier.
     *
     * @param ruleName objet utilisé par le formulaire
     * @return vue du formulaire d'ajout
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        return "ruleName/add";
    }

    /**
     * Valide et enregistre une nouvelle règle métier.
     *
     * @param ruleName règle métier validée
     * @param result résultat de la validation
     * @param model modèle utilisé pour transmettre less données à la vue
     * @return redirection vers la liste des règles métier ou vers le formulaire d'ajout en cas d'erreur
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "ruleName/add";
        }

        ruleNameService.save(ruleName);
        model.addAttribute("ruleNames", ruleNameService.findAll());
        return "redirect:/ruleName/list";
    }

    /**
     * Affiche le formulaire d'une règle métier existante.
     *
     * @param id identifiant unique de la règle métier à modifier
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return vue du formulaire de modification d'une règle métier
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.findById(id);
        model.addAttribute("ruleName", ruleNameService.findById(id));
        return "ruleName/update";
    }

    /**
     * Met à jour une règle métier existante.
     *
     * @param id identifiant de la règle métier
     * @param ruleName données mises à jour
     * @param result résultat de la validation
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste ou retour au formulaire en cas d'erreur
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            ruleNameService.findById(id);
            ruleName.setId(id);
            return "ruleName/update";
        }
        ruleName.setId(id);
        ruleNameService.save(ruleName);
        model.addAttribute("ruleName", ruleName);
        return "redirect:/ruleName/list";
    }

    /**
     * Supprime une règle métier existante.
     *
     * @param id identifiant de la règle à supprimer
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste des règles métier
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.deleteById(id);
        model.addAttribute("ruleNames", ruleNameService.findAll());
        return "redirect:/ruleName/list";
    }
}
