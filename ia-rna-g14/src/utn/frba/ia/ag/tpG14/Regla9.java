package utn.frba.ia.ag.tpG14;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class Regla9 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		ArbolGenetico arbolGenetico = new ArbolGenetico();
		assertTrue(arbolGenetico.checkCaracteres("aab")); // ok
		assertTrue(arbolGenetico.checkCaracteres("aaab")); // ok
		assertTrue(arbolGenetico.checkCaracteres("aaabc")); // ok
		assertTrue(arbolGenetico.checkCaracteres("cdaab")); // ok
		assertTrue(arbolGenetico.checkCaracteres("cdaaab")); // ok
		assertTrue(arbolGenetico.checkCaracteres("cdaaabc")); // ok
		assertTrue(arbolGenetico.checkCaracteres("cdaabcde")); // ok
		assertTrue(arbolGenetico.checkCaracteres("cdaaabcde")); // ok
		assertTrue(arbolGenetico.checkCaracteres("cdaaabccde")); // ok
		assertTrue(arbolGenetico.checkCaracteres("cdaabcdeb")); // ok
		assertTrue(arbolGenetico.checkCaracteres("cdaaabcdeb")); // ok
		assertTrue(arbolGenetico.checkCaracteres("cdaaabccdeb")); // ok

		assertFalse(arbolGenetico.checkCaracteres("aaaa")); // no ok
		assertFalse(arbolGenetico.checkCaracteres("cdeaaaa")); // no ok
		assertFalse(arbolGenetico.checkCaracteres("aaaacde")); // no ok
		assertFalse(arbolGenetico.checkCaracteres("cdeaaaacde")); // no ok
		assertFalse(arbolGenetico.checkCaracteres("aabb")); // no ok
		assertFalse(arbolGenetico.checkCaracteres("cdeaabb")); // no ok
		assertFalse(arbolGenetico.checkCaracteres("aabbcde")); // no ok
		assertFalse(arbolGenetico.checkCaracteres("cdeaabbcde")); // no ok
		assertFalse(arbolGenetico.checkCaracteres("aaabb")); // no ok
		assertFalse(arbolGenetico.checkCaracteres("cdeaaabb")); // no ok
		assertFalse(arbolGenetico.checkCaracteres("aaabbcde")); // no ok
		assertFalse(arbolGenetico.checkCaracteres("cdeaaabbcde")); // no ok
	}

}
