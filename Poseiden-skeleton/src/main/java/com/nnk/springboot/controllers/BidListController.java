package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * Contrôleur MVC dédié à la gestion des entrées.
 *
 * <p>Cette classe permet de gérer les opérations CRUD associées aux {@link BidList} dans l'application :</p>
 *
 * <ul>
 *     <li>Affichage de la liste des entrées</li>
 *     <li>Ajout d'une nouvelle entrée</li>
 *     <li>Mise à jour d'une entrée existante</li>
 *     <li>Suppression d'une entrée</li>
 * </ul>
 */
@Controller
public class BidListController {

    /**
     * Service de gestions des entrées.
     */
    private final BidListService bidListService;

    /**
     * Constructeur du contrôleur des Bidlist.
     *
     * @param bidListService service de gestion des entrées
     */
    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    /**
     * Affiche la liste des entrées.
     *
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return vue de la liste des entrées
     */
    @RequestMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute("bidLists", bidListService.findAll());
        return "bidList/list";
    }

    /**
     * Affiche le formulaire d'ajout d'une entrée.
     *
     * @param bid objet Bidlist utilisé pour le formulaire
     * @return vue du formulaire d'ajout
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /**
     * Valide et ajoute une nouvelle entrée.
     *
     * @param bid entrée valide
     * @param result résultat de la validation
     * @param model modèle utilisé pour transmettre les données à vue
     * @return redirection vers la liste ou vers le formulaire en cas d'erreur
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.save(bid);
        model.addAttribute("bidLists", bidListService.findAll());
        return "redirect:/bidList/list";
    }

    /**
     * Affiche le formulaire de modification d'une entrée.
     *
     * @param id identifiant unique de l'entrée à modifier
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return vue du formulaire de mise à jour
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bid = bidListService.findById(id);
        model.addAttribute("bidList", bidListService.findById(id));
        return "bidList/update";
    }

    /**
     * Met à jour une entrée existante.
     *
     * @param id identifiant de l'entrée à modifier
     * @param bidList données mise à jour
     * @param result résultat de la validation
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste ou retour au formulaire en cas d'erreur
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {

        if (result.hasErrors()) {
            bidListService.findById(id);
            bidList.setBidListId(id);
            return "bidList/update";
        }
        bidList.setBidListId(id);
        bidListService.save(bidList);
        model.addAttribute("bidList", bidList);

        return "redirect:/bidList/list";
    }

    /**
     * Supprime une entrée existante.
     *
     * @param id identifiant unique de l'entrée à supprimer
     * @param model modèle utilisé pour transmettre les données à la vue
     * @return redirection vers la liste des entrées
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteById(id);
        model.addAttribute("bidLists", bidListService.findAll());
        return "redirect:/bidList/list";
    }
}
