package com.nnk.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de tests dédiée au chiffrement des mots de passe.
 *
 * <p>Cette classe vérifie le bon fonctionnement du mécanisme BCrypt utilisé pour sécuriser
 * les mots de passe utilisateurs.</p>
 */
public class PasswordEncodeTest {

    /**
     * Encodeur BCrypt utilisé pour le chiffrement des mots de passe.
     */
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Vérifie qu'un mot de passe crypté peut être correctement validé.
     */
    @Test
    void shouldEncodeAndVerifyPassword() {
        String rawPassword = "123456";

        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Encoded password: " + encodedPassword);

        assertTrue(encoder.matches(rawPassword, encodedPassword));


    }

}
