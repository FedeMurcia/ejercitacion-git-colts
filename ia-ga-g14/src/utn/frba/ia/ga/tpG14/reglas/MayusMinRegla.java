package utn.frba.ia.ga.tpG14.reglas;

import java.text.StringCharacterIterator;

public class MayusMinRegla implements Regla {

	public static final double PUNTAJE_POR_TRANSICION = 2.5;

	/**
	 * Regla propia que otorga mayor valuación a una password con alternancia de
	 * mayúsculas y minúsculas que a una plana.
	 */
	@Override
	public double evaluar(String individuo) {
		if (individuo.isEmpty())
			return 0;

		StringCharacterIterator iterator = new StringCharacterIterator(
				individuo);
		char anterior = StringCharacterIterator.DONE;
		char actual = iterator.current();
		double puntaje = 0;
		do {
			/*
			 * recorreremos al individuo buscando transiciones entre uppercase y
			 * lowercase, con un caracter anterior y uno actual
			 */
			anterior = actual;
			actual = iterator.next();
			if (Character.isLetter(actual) && Character.isLetter(anterior)) {
				// sólo si los dos son letras evaluaremos si ocurre una
				// transición
				boolean uppercaseAnterior, uppercaseActual;
				uppercaseAnterior = Character.isUpperCase(anterior);
				uppercaseActual = Character.isUpperCase(actual);
				if (uppercaseActual ^ uppercaseAnterior) {
					/*
					 * ^ = operador de XOR, es decir, evaluará true cuando no
					 * sean simultáneamente uppercase o lowercase
					 */
					puntaje += PUNTAJE_POR_TRANSICION;
				}
			}
		} while (iterator.current() != StringCharacterIterator.DONE);

		return puntaje;
	}

}
