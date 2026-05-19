package com.nnk.springboot.domain;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Représente un utilisateur de l'application.
 *
 * <p>Cette entité permet de stocker les informations relatives à un utilisateur de notre application</p>
 *
 * <p>Chaque utilisateur possède un identifiant, des informations de connexion ainsi qu'un rôle
 * définissant ses droits d'accès dans l'application.</p>
 *
 * <p>La classe est associé à la table {@code users} dans la base de données.</p>
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Identifiant unique de l'utilisateur
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    /**
     * Nom d'utilisateur utilisé pour la connexion
     */
    @NotBlank(message = "Username is mandatory")
    @Column(name = "username", length = 125)
    private String username;

    /**
     * Mot de passe associé au compte de l'utilisateur
     */
    @NotBlank(message = "Password is mandatory")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",
            message = "Password must be at least 8 characters long and include one uppercase letter, one number, and one symbol"
    )
    @Column(name = "password", length = 125)
    private String password;

    /**
     * Nom complet de l'utilisateur
     */
    @NotBlank(message = "Full name is mandatory")
    @Column(name = "fullname", length = 125)
    private String fullname;

    /**
     * Rôle attribué à l'utilisateur
     */
    @NotBlank(message = "Role is mandatory")
    @Column(name = "role", length = 125)
    private String role;

    public Integer getId() {
        return id;
    }

    public @NotBlank(message = "Username is mandatory") String getUsername() {
        return username;
    }

    public @NotBlank(message = "Password is mandatory") String getPassword() {
        return password;
    }

    public @NotBlank(message = "FullName is mandatory") String getFullname() {
        return fullname;
    }

    public @NotBlank(message = "Role is mandatory") String getRole() {
        return role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(@NotBlank(message = "Username is mandatory") String username) {
        this.username = username;
    }

    public void setPassword(@NotBlank(message = "Password is mandatory") String password) {
        this.password = password;
    }

    public void setFullname(@NotBlank(message = "FullName is mandatory") String fullname) {
        this.fullname = fullname;
    }

    public void setRole(@NotBlank(message = "Role is mandatory") String role) {
        this.role = role;
    }
}
