package utn.frba.ia.ga.tpG14;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import utn.frba.ia.ga.tpG14.reglas.PalabrasProbablesRegla;

public class PalabrasProbablesReglaTest {

	private PalabrasProbablesRegla regla;

	@Before
	public void setUp(){
		HashMap<String, Double> palabras = new HashMap<String, Double>(3);
		palabras.put("hola", 10d);
		palabras.put("1234", 10d);
		palabras.put("foobar", 10d);
		regla = new PalabrasProbablesRegla(palabras);
	}
	
	@Test
	public void test() {
		assertTrue(regla.evaluar("ceropuntos") == 0);
		assertTrue(regla.evaluar("holaceropuntos") == 10);
		assertTrue(regla.evaluar("ceroholapuntos") == 10);
		assertTrue(regla.evaluar("ceropuntoshola") == 10);
		assertTrue(regla.evaluar("hola") == 10);
		assertTrue(regla.evaluar("") == 0);
		
		assertTrue(regla.evaluar("hola1234") == 20);
		assertTrue(regla.evaluar("asdhola1234") == 20);
		assertTrue(regla.evaluar("holaasd1234") == 20);
		assertTrue(regla.evaluar("hola1234asd") == 20);
		assertTrue(regla.evaluar("asdholaads1234asd") == 20);
		assertTrue(regla.evaluar("hola1234foobar") == 30);
		assertTrue(regla.evaluar("asdhola1234foobar") == 30);
		assertTrue(regla.evaluar("holaasd1234foobar") == 30);
		assertTrue(regla.evaluar("adsholadsa1234adsfoobarads") == 30);
	}

}
