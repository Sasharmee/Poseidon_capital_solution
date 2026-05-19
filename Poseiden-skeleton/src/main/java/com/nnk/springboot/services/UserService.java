package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation du service de gestion des utilisateurs de l'application.
 *
 * <p>Cette classe assure les opérations d'accès et de gestion des {@link User}
 * via le repository {@link UserRepository}.</p>
 */
@Service
public class UserService implements UserServiceInterface{

    /**
     * Repository de gestion des utilisateurs.
     */
    private final UserRepository userRepository;

    /**
     * Constructeur du service de gestion des utilisateurs.
     *
     * @param userRepository repository des utilisateurs
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()-> new IllegalArgumentException("Invalid user id " + id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);

    }
}
