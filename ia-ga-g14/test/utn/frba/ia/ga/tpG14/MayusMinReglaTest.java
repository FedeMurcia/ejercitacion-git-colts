package utn.frba.ia.ga.tpG14;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import utn.frba.ia.ga.tpG14.reglas.MayusMinRegla;

public class MayusMinReglaTest {

	private MayusMinRegla regla;

	@Before
	public void setUp() throws Exception {
		this.regla = new MayusMinRegla();
	}

	@Test
	public void test() {
		assertTrue(regla.evaluar("HoLa") == MayusMinRegla.PUNTAJE_POR_TRANSICION * 3);
		assertTrue(regla.evaluar("HOLA") == MayusMinRegla.PUNTAJE_POR_TRANSICION * 0);
		assertTrue(regla.evaluar("hola") == MayusMinRegla.PUNTAJE_POR_TRANSICION * 0);
		assertTrue(regla.evaluar("") == MayusMinRegla.PUNTAJE_POR_TRANSICION * 0);
		assertTrue(regla.evaluar("holaChau") == MayusMinRegla.PUNTAJE_POR_TRANSICION * 2);
		assertTrue(regla.evaluar("MuChAsTrAnSiCiOnEs") == MayusMinRegla.PUNTAJE_POR_TRANSICION * 17);

	}
}
