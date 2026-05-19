package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * Contrôleur MVC dédié à la gestion des opérations financières (trade).
 *
 * <p>Cette classe permet de gérer les opérations CRUD associées aux {@link Trade} dans l'application :</p>
 *
 * <ul>
 *     <li>Affichage de la liste des opérations</li>
 *     <li>Ajout d'une nouvelle opération financière</li>
 *     <li>Mise à jour d'une opération existante</li>
 *     <li>Suppression d'une opération financière</li>
 * </ul>
 */
@Controller
public class TradeController {

    /**
     * Service de gestion des opérations financières.
     */
    private final TradeService tradeService;

    /**
     * Constructeur du contrôleur des Trade.
     *
     * @param tradeService service de gestion des opérations financières
     */
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    /**
     * Affiche la liste des opérations financières.
     *
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return vue de la liste des opérations
     */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute("trades", tradeService.findAll());
        return "trade/list";
    }

    /**
     * Affiche le formulaire d'ajout d'opérations financières.
     *
     * @param trade objet utilisé par le formulaire
     * @return vue du formulaire d'ajout
     */
    @GetMapping("/trade/add")
    public String addTradeForm(Trade trade) {
        return "trade/add";
    }

    /**
     * Valide et enregistre une nouvelle opération financière.
     *
     * @param trade opération validée
     * @param result résultat de la validation
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste des opérations ou vers le formulaire d'ajout en cas d'erreur
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/add";
        }
        tradeService.save(trade);
        model.addAttribute("trades", tradeService.findAll());
        return "redirect:/trade/list";

    }

    /**
     * Affiche le formulaire de modification d'une opération existante.
     *
     * @param id identifiant unique de l'opération à modifier
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return vue du formulaire de mise à jour
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findById(id);
        model.addAttribute("trade", tradeService.findById(id));
        return "trade/update";
    }

    /**
     * Met à jour une opération financière existante.
     *
     * @param id identifiant unique de l'opération
     * @param trade données mises à jour
     * @param result résultat de la validation
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste ou retour vers le formulaire en cas d'erreur
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model) {

        if (result.hasErrors()) {
            tradeService.findById(id);
            trade.setTradeId(id);
            return "trade/update";
        }
        trade.setTradeId(id);
        tradeService.save(trade);
        model.addAttribute("trade", trade);
        return "redirect:/trade/list";
    }

    /**
     * Supprime une opération financière existante.
     *
     * @param id identifiant de l'opération à supprimer
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste des opérations
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.deleteById(id);
        model.addAttribute("trades", tradeService.findAll());
        return "redirect:/trade/list";
    }
}
