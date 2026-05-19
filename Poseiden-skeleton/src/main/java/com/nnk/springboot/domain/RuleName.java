package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Représente une règle métier configurable dans notre application.
 *
 * <p>Cette entité permet de stocker les informations nécessaires à la définition d'une règle dans l'application.
 * Une règle contient différentes informations</p>
 *
 * <p>Les règles enregistrées peuvent être utilisées pour
 *  * des traitements métiers, des validations ou des filtrages
 *  * dynamiques au sein du système financier.</p>
 *
 *  <p>La classe associée à la table {@code rulename } dans la base de données</p>
 */
@Entity
@Table(name = "rulename")
public class RuleName {

    /**
     * Identifiant unique à la règle
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    /**
     * Nom associé à la règle
     */
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name", length = 125)
    private String name;

    /**
     * Description de la règle
     */
    @NotBlank(message = "Description is mandatory")
    @Column(name = "description", length = 125)
    private String description;

    /**
     * Contenu JSON associé à la règle
     */
    @NotBlank(message = "Json is mandatory")
    @Column(name = "json", length = 125)
    private String json;

    /**
     * Modèle utilisé par la règle
     */
    @NotBlank(message = "Template is mandatory")
    @Column(name = "template", length = 512)
    private String template;

    /**
     * Requête SQL associée à la règle
     */
    @NotBlank(message = "SQL is mandatory")
    @Column(name = "sqlStr", length = 125)
    private String sql;

    /**
     * Fragment associé à la requête
     */
    @NotBlank(message = "SQL part is mandatory")
    @Column(name = "sqlPart", length = 125)
    private String sqlPart;

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public String getJson() {
        return json;
    }

    public String getName() {
        return name;
    }

    public String getSql() {
        return sql;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public String getTemplate() {
        return template;
    }

    public void setDescription(String description) {        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
