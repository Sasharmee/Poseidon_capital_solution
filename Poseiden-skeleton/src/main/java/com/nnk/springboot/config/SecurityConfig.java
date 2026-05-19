package com.nnk.springboot.config;

import com.nnk.springboot.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * Configuration de la sécurité de l'application.
 *
 * <p>Cette classe configure les mécanismes d'authentification et d'autorisation à l'aide de Spring Security.</p>
 *
 * <p>Elle définit notamment :</p>
 * <ul>
 *     <li>Le système d'authentification des utilisateurs</li>
 *     <li>Le chiffrement des mots de passe</li>
 *     <li>Les règles d'accès selon les rôles</li>
 *     <li>La gestion des sessions utilisateur</li>
 *     <li>Les pages de connexion et de déconnexion</li>
 * </ul>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Service personnalisé de chargement des utilisateurs.
     */
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Constructeur de la configuration de sécurité.
     *
     * @param customUserDetailsService service de gestion des utilisateurs
     */
    public SecurityConfig(CustomUserDetailsService customUserDetailsService){
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * Définit l'encodeur de mot de passe utilisé
     * @return encodeur Bcrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure le fournisseur d'authentification utilisation le service utilisateur personnalisé.
     *
     * @return fournisseur d'authentification DAO
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(customUserDetailsService);

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /**
     * Configure les règles de sécurité HTTP de l'application.
     *
     * <p>Cette configuration définit :</p>
     * <ul>
     *     <li>Les autorisations d'accès selon les rôles</li>
     *     <li>Le formulaire de connexion personnalisé</li>
     *     <li>Le mécanisme de déconnexion</li>
     *     <li>La gestion des erreurs d'accès</li>
     *     <li>La gestion des sessions utilisateur</li>
     * </ul>
     *
     * @param http objet de configuration HTTP Spring Security
     * @return chaîne de filtres de sécurité configurée
     * @throws Exception en cas d'erreur de configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/", "/app/login", "/app/error", "/css/**", "/assets/**").permitAll()
                        .requestMatchers("/user/**").hasRole("ADMIN")
                        .requestMatchers("/ruleName/**").hasRole("ADMIN")
                        .requestMatchers("/app/secure/**").hasRole("ADMIN")
                        .anyRequest().hasAnyRole("USER", "ADMIN")
                )

                .formLogin(form->form
                        .loginPage("/app/login")
                        .loginProcessingUrl("/app/login")
                        .defaultSuccessUrl("/bidList/list", true)
                        .failureUrl("/app/login?error=true")
                        .permitAll()
                )

                .logout(logout->logout
                        .logoutUrl("/app-logout")
                        .logoutSuccessUrl("/app/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(
                                new LoginUrlAuthenticationEntryPoint("/app/login")
                        )
                        .accessDeniedPage("/app/error")
                )

                .sessionManagement(session->session
                        .sessionFixation().migrateSession()
                        .maximumSessions(1)
                )

                ;

        return http.build();
    }
}
