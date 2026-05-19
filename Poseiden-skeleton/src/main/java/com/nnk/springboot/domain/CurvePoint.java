package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

/**
 * Représente un point de courbe financière dans notre application.
 *
 * <p>Cette entité permet de stocker les valeurs associées à un point de courbe à un moment donné.
 * Les données peuvent être utilisées pour différentes analyses et manipulations.</p>
 *
 * <p>La classe est associée à la table {@code curvepoint} dans la base de données.</p>
 */
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    /**
     * Identifiant unique du point de courbe
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    /**
     * Identifiant de la courbe associé
     */
    @NotNull(message = "Curve Id is mandatory")
    @Column(name = "CurveId")
    private Integer curveId;

    /**
     * Date de référence des données financières.
     */
    @Column(name = "asOfDate")
    private Timestamp asOfDate;

    /**
     * Echéance du point de courbe
     */
    @NotNull(message = "Term is mandatory")
    @Column(name = "term")
    private Double term;

    /**
     * Valeur du point de courbe
     */
    @NotNull(message = "Value is mandatory")
    @Column(name = "value")
    private Double value;

    /**
     * Date de création du point de courbe
     */
    @Column(name = "creationDate")
    private Timestamp creationDate;

    public Integer getId() {
        return id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public Double getValue() {
        return value;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
