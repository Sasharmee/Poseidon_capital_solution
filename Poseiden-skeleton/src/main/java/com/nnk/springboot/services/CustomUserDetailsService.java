package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service personnalisé de gestion des utilisateurs utilisé par Spring Security pour l'authentification.
 *
 * <p>Cette classe permet de charger les informations d'un utilisateur à partir de son nom d'utilisateur
 * afin de fournir les données nécessaires au processus d'authentification et d'autorisation.</p>
 *
 * <p>Les rôles des utilisateurs sont convertis en autorités compatibles avec Spring Security</p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Repository de gestion des utilisateurs
     */
    private final UserRepository userRepository;

    /**
     * Constructeur du service de gestion des utilisateurs.
     *
     * @param userRepository repository des utilisateurs
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Charge un utilisateur à partir de son nom d'utilisateur.
     *
     * <p>Cette méthode est utilisée par Spring Security lors du processus d'authentification</p>
     *
     * @param username nom d'utilisateur recherché
     * @return informations de sécurité de l'utilisateur
     * @throws UsernameNotFoundException si l'utilisateur n'existe pas
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException(
                    "User not found"
            );
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}
