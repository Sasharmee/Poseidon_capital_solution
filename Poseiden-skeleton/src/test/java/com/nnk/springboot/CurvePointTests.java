package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests d'intégration dédiée aux opérations CRUD sur l'entité {@link CurvePoint}.
 *
 * <p>Cette classe vérifie le bon fonctionnement des opérations de persistance via {@link CurvePointRepository}.</p>
 */
@SpringBootTest
public class CurvePointTests {

	/**
	 * Repository de gestion des CurvePoint.
	 */
	@Autowired
	private CurvePointRepository curvePointRepository;

	/**
	 * Vérifie les opérations CRUD
	 * sur un point de courbe financière.
	 */
	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint();
		curvePoint.setCurveId(10);
		curvePoint.setTerm(10.0);
		curvePoint.setValue(30.0);

		curvePoint = curvePointRepository.save(curvePoint);
		assertNotNull(curvePoint.getId());
		assertEquals(10, curvePoint.getCurveId());

		curvePoint.setCurveId(20);
		curvePoint = curvePointRepository.save(curvePoint);
		assertEquals(20, curvePoint.getCurveId());

		List<CurvePoint> listResult = curvePointRepository.findAll();
		assertFalse(listResult.isEmpty());

		Integer id = curvePoint.getId();
		curvePointRepository.deleteById(id);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		assertFalse(curvePointList.isPresent());
	}

}
