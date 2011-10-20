package utn.frba.ia.ga.tpG14;

import static org.junit.Assert.*;

import org.junit.Test;

import utn.frba.ia.ga.tpG14.reglas.Regla11CaseSensitive;

public class Regla11ConsiderandoRegla2Test {

	@Test
	public void test() {
		Regla11CaseSensitive regla11 = new Regla11CaseSensitive();
		assertTrue(regla11.evaluar("ann") > 0);
		assertTrue(regla11.evaluar("asdann") > 0);
		assertTrue(regla11.evaluar("annasd") > 0);
		assertTrue(regla11.evaluar("asdannads") > 0);
		
		assertFalse(regla11.evaluar("ANN") > 0);
		assertFalse(regla11.evaluar("a1nn") > 0);
		assertFalse(regla11.evaluar("anN") > 0);
		assertFalse(regla11.evaluar("asd") > 0);
		assertFalse(regla11.evaluar("") > 0);		
	}

}
