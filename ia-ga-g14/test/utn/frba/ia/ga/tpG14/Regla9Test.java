package utn.frba.ia.ga.tpG14;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import utn.frba.ia.ga.tpG14.reglas.Regla;
import utn.frba.ia.ga.tpG14.reglas.Regla9;

public class Regla9Test {

        @Before
        public void setUp() throws Exception {
        }

        @Test
        public void test() {
                Regla regla9 = new Regla9();
                assertTrue(regla9.evaluar("aab") > 0); // ok
                assertTrue(regla9.evaluar("aaab") > 0); // ok
                assertTrue(regla9.evaluar("aaabc") > 0); // ok
                assertTrue(regla9.evaluar("cdaab") > 0); // ok
                assertTrue(regla9.evaluar("cdaaab") > 0); // ok
                assertTrue(regla9.evaluar("cdaaabc") > 0); // ok
                assertTrue(regla9.evaluar("cdaabcde") > 0); // ok
                assertTrue(regla9.evaluar("cdaaabcde") > 0); // ok
                assertTrue(regla9.evaluar("cdaaabccde") > 0); // ok
                assertTrue(regla9.evaluar("cdaabcdeb") > 0); // ok
                assertTrue(regla9.evaluar("cdaaabcdeb") > 0); // ok
                assertTrue(regla9.evaluar("cdaaabccdeb") > 0); // ok

                assertFalse(regla9.evaluar("aaaa") > 0); // no ok
                assertFalse(regla9.evaluar("cdeaaaa") > 0); // no ok
                assertFalse(regla9.evaluar("aaaacde") > 0); // no ok
                assertFalse(regla9.evaluar("cdeaaaacde") > 0); // no ok
                assertFalse(regla9.evaluar("aabb") > 0); // no ok
                assertFalse(regla9.evaluar("cdeaabb") > 0); // no ok
                assertFalse(regla9.evaluar("aabbcde") > 0); // no ok
                assertFalse(regla9.evaluar("cdeaabbcde") > 0); // no ok
                assertFalse(regla9.evaluar("aaabb") > 0); // no ok
                assertFalse(regla9.evaluar("cdeaaabb") > 0); // no ok
                assertFalse(regla9.evaluar("aaabbcde") > 0); // no ok
                assertFalse(regla9.evaluar("cdeaaabbcde") > 0); // no ok
        }


}