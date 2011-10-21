package utn.frba.ia.ga.tpG14;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utn.frba.ia.ga.tpG14.reglas.PalabrasProbablesRegla;
import utn.frba.ia.ga.tpG14.reglas.Regla;
import utn.frba.ia.ga.tpG14.reglas.Regla11CaseSensitive;
import utn.frba.ia.ga.tpG14.reglas.Regla2;
import utn.frba.ia.ga.tpG14.reglas.Regla9;

public class IntegracionReglasTest {

	private List<Regla> reglas;

	@Before
	public void setUp() throws Exception {
		HashMap<String, Double> palabras = new HashMap<String, Double>(3);
		palabras.put("hola", 10d);
		palabras.put("1234", 10d);
		palabras.put("foo", 10d);

		reglas = new LinkedList<Regla>();

		reglas.add(new Regla2());
		reglas.add(new Regla9());
		reglas.add(new Regla11CaseSensitive());
		reglas.add(new PalabrasProbablesRegla(palabras));

	}

	@Test
	public void test1() {
		double fitness = 0;
		String individuo = "zz1234annfoo";

		for (Regla regla : reglas) {
			double _fitness = regla.evaluar(individuo);
			if (_fitness == Double.NEGATIVE_INFINITY) {
				fitness = 0;
				break;
			}
			fitness += _fitness;
		}
		assertTrue(fitness == 20);
	}

	@Test
	public void test2() {
		double fitness = 0;
		String individuo = "zz4567annfoo";

		for (Regla regla : reglas) {
			double _fitness = regla.evaluar(individuo);
			if (_fitness == Double.NEGATIVE_INFINITY) {
				fitness = 0;
				break;
			}
			fitness += _fitness;
		}
		assertTrue(fitness == 10);
	}

	@Test
	public void test3() {
		double fitness = 0;
		String individuo = "zz4567annqwe";

		for (Regla regla : reglas) {
			double _fitness = regla.evaluar(individuo);
			if (_fitness == Double.NEGATIVE_INFINITY) {
				fitness = 0;
				break;
			}
			fitness += _fitness;
		}
		assertTrue(fitness == 0);
	}

	@Test
	public void test4() {
		double fitness = 0;
		String individuo = "zz4567ANNqwe";

		for (Regla regla : reglas) {
			double _fitness = regla.evaluar(individuo);
			if (_fitness == Double.NEGATIVE_INFINITY) {
				fitness = 0;
				break;
			}
			fitness += _fitness;
		}
		assertTrue(fitness == 0);
	}
}
