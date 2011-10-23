package utn.frba.ia.ga.tpG14;

import static org.junit.Assert.*;
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
		assertEquals(regla.evaluar("HoLa"),
				(regla.getPuntajeMaximo() / "HoLa".length()) * 3, 0.001);
		assertEquals(regla.evaluar("HOLA"),
				(regla.getPuntajeMaximo() / "HOLA".length()) * 0, 0.0);
		assertEquals(regla.evaluar("hola"),
				(regla.getPuntajeMaximo() / "hola".length()) * 0, 0.0);
		assertEquals(regla.evaluar(""), 0, 0.0);
		assertEquals(regla.evaluar("holaChau"),
				(regla.getPuntajeMaximo() / "holaChau".length()) * 2, 0.001);
		assertEquals(
				regla.evaluar("MuChAsTrAnSiCiOnEs"),
				(regla.getPuntajeMaximo() / "MuChAsTrAnSiCiOnEs".length()) * 17,
				0.001);

	}
}
