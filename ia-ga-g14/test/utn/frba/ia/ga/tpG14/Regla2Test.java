package utn.frba.ia.ga.tpG14;

import static org.junit.Assert.*;

import org.junit.Test;

import utn.frba.ia.ga.tpG14.reglas.Regla2;

public class Regla2Test {

	@Test
	public void test() {
		Regla2 regla2 = new Regla2();
		
		assertTrue(regla2.evaluar("abc123ABC123") == 0);
		assertTrue(regla2.evaluar("abcdefghijkl") == 0);
		assertTrue(regla2.evaluar("123456789123") == 0);
		assertTrue(regla2.evaluar("ABCDEFGHIJKL") == 0);
		
		assertFalse(regla2.evaluar("@bc123ABC123") == 0);
		assertFalse(regla2.evaluar("bc123ABC123") == 0);
		assertFalse(regla2.evaluar("aabc123ABC123") == 0);
		assertFalse(regla2.evaluar("-bc123ABC123") == 0);
		assertFalse(regla2.evaluar("?bc123ABC123") == 0);
		assertFalse(regla2.evaluar("") == 0);
	}

}
