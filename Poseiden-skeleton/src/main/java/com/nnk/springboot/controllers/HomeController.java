package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Contrôleur MVC gérant les pages d'accueil et les redirections principales de l'application.
 *
 * <p>Cette classe permet notamment :</p>
 * <ul>
 *     <li>L'affichage de la page d'accueil</li>
 *     <li>La redirection vers les fonctionnalités sécurisées</li>
 * </ul>
 */
@Controller
public class HomeController {

	/**
	 * Affiche la page d'accueil de l'application.
	 *
	 * @return vue de la page d'accueil
	 */
	@RequestMapping("/")
	public String home()
	{
		return "home";
	}

	/**
	 * Redirige l'utilisateur administrateur vers la liste des entrées.
	 *
	 * @return redirection vers la page des BidList
	 */
	@RequestMapping("/admin/home")
	public String adminHome()
	{
		return "redirect:/bidList/list";
	}


}
