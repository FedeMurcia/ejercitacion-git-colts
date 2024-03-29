package utn.frba.ia.ga.tpG14;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import utn.frba.ia.ga.tpG14.reglas.DistribucionAlfanumericaRegla;

public class DistribucionAlfanumericaReglaTest {

	private DistribucionAlfanumericaRegla regla;

	@Before
	public void setUp() throws Exception {
		this.regla = new DistribucionAlfanumericaRegla();
	}

	@Test
	public void test() {
		assertTrue(regla.evaluar("a1") == regla.getPuntajeMaximo());
		assertTrue(regla.evaluar("a1b2c3") == regla.getPuntajeMaximo());
		assertTrue(regla.evaluar("abc123") == regla.getPuntajeMaximo());
		assertTrue(regla.evaluar("aa") == 0);
		assertTrue(regla.evaluar("a") == 0);
		assertTrue(regla.evaluar("") == 0);
		assertTrue(regla.evaluar("1") == 0);
		assertTrue(regla.evaluar("11") == 0);
		assertTrue(regla.evaluar("a1") > regla.evaluar("aa"));
		assertTrue(regla.evaluar("a1") > regla.evaluar("aa1"));
		assertTrue(regla.evaluar("a1b2c") > regla.evaluar("a1b2cc"));
		assertTrue(regla.evaluar("a1b2c") == regla.evaluar("A1B2C"));
		assertTrue(regla.evaluar("a1") == regla.evaluar("A1"));
		assertTrue((regla.evaluar("hola123") > regla.evaluar("hola123chau"))
				&& (regla.evaluar("hola123chau") > regla
						.evaluar("hola123chauadios")));
	}

}
