package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Représente une notation financière attribuée par différentes agences de notations.
 *
 * <p>Cette entité permet de stocker les différentes notations financières selon différentes agences de notations.
 * Elles sont utilisés pour évaluer le risque et la stabilité financière d'un acteur ou d'un produit financier</p>
 *
 * <p>La classe est associée à la table {@code rating} dans la base de données</p>
 */
@Entity
@Table(name = "rating")
public class Rating {

    /**
     * Identifiant unique de la notation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    /**
     * Note attribué par l'agence Moody's
     */
    @NotBlank(message = "Moodys rating is mandatory")
    @Column(name = "moodysRating", length = 125)
    private String moodysRating;

    /**
     * Note attribuée par l'agence S&P
     */
    @NotBlank(message = "S&P rating is mandatory")
    @Column(name = "sandPRating", length = 125)
    private String sandPRating;

    /**
     * Note attribuée par l'agence Fitch
     */
    @NotBlank(message = "Fitch rating is mandatory")
    @Column(name = "fitchRating", length = 125)
    private String fitchRating;

    /**
     * Ordre de classement
     */
    @NotNull(message = "Order number is mandatory")
    @Column(name = "orderNumber")
    private Integer orderNumber;

    public String getFitchRating() {
        return fitchRating;
    }

    public Integer getId() {
        return id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }
}
