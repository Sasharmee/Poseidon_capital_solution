package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests d'intégration dédiée
 * aux opérations CRUD sur l'entité {@link RuleName}.
 *
 * <p>Cette classe vérifie le bon fonctionnement
 * des opérations de persistance via
 * {@link RuleNameRepository}.</p>
 */
@SpringBootTest
public class RuleNameTests {

	/**
	 * Repository de gestion des règles métier.
	 */
	@Autowired
	private RuleNameRepository ruleNameRepository;

	/**
	 * Vérifie les opérations CRUD sur une règle métier.
	 */
	@Test
	public void shouldPerformCrudOperationsOnRuleName() {
		RuleName rule = new RuleName();
		rule.setName("Rule Name");
		rule.setDescription("description");
		rule.setJson("json");
		rule.setTemplate("Template");
		rule.setSql("SQL");
		rule.setSqlPart("SQL Part");

		rule = ruleNameRepository.save(rule);
		assertNotNull(rule.getId());
		assertEquals("Rule Name", rule.getName());

		rule.setName("Rule Name Updated");
		rule = ruleNameRepository.save(rule);
		assertEquals("Rule Name Updated", rule.getName());

		List<RuleName> listResult = ruleNameRepository.findAll();
		assertFalse(listResult.isEmpty());

		Integer id = rule.getId();
		ruleNameRepository.delete(rule);
		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
		assertFalse(ruleList.isPresent());
	}
}
