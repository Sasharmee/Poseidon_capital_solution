package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation du service de gestion des règles métier configurables.
 *
 * <p>Cette classe assure les opérations d'accès et de gestion des {@link RuleName}
 * via le repository {@link RuleNameRepository}.</p>
 */
@Service
public class RuleNameService implements  RuleNameServiceInterface{

    /**
     * Repository de gestion des règles métier.
     */
    private final RuleNameRepository ruleNameRepository;

    /**
     * Constructeur du service de gestion des règles métier.
     *
     * @param ruleNameRepository repository des règles métier
     */
    public RuleNameService(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RuleName findById(Integer id) {
        Optional<RuleName> ruleName = ruleNameRepository.findById(id);
        return ruleName.orElseThrow(()->new IllegalArgumentException("Invalid ruleName id " + id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RuleName save(RuleName ruleName) {

        return ruleNameRepository.save(ruleName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Integer id) {
        ruleNameRepository.deleteById(id);
    }
}
