package utn.frba.ia.ga.tpG14.reglas;

import java.text.StringCharacterIterator;

/**
 * R9) Puede haber juntos dos caracteres iguales siempre y cuando el siguiente
 * caracter aparezca sólo una única vez (por ejemplo, la cadena "..23345.." es
 * válida, pero la cadena "..2334445.." es inválida).
 * 
 * @author Alme
 * 
 */
public class Regla9 implements Regla {

	/**
	 * Regla binaria. Retorna 0 puntos si pasa la regla, Double.NEGATIVE_INFINITY si falla.
	 */
	@Override
	public double evaluar(String individuo) {
		/*
		 * Puede haber juntos dos caracteres iguales siempre y cuando el
		 * siguiente caracter aparezca sólo una única vez (por ejemplo, la
		 * cadena ".23345.." es válida, pero la cadena "..2334445.." es
		 * inválida).
		 * 
		 * alme: asumo que es válido 123345674, es decir, que el 'aparece sólo
		 * una única vez' vale para la repetición inmediata, pero puede
		 * repetirse luego'
		 */

		StringCharacterIterator iterator = new StringCharacterIterator(
				individuo);
		int repeticiones = 0;
		while (iterator.current() != StringCharacterIterator.DONE) {
			// voy y vengo en el string, maldito iterador que no me avisa que el
			// previous realmente RETROCEDE
			char current = iterator.current();
			char previous = iterator.previous();
			if (previous != StringCharacterIterator.DONE)
				iterator.next(); // voy adelante por haber hecho un previous

			if (repeticiones == 3)
				/*
				 * Considero 3 repeticiones (4 ocurrencias) una violación de la
				 * regla ya que
				 * "dos caracteres iguales siempre y cuando el siguiente carácter aparezca sólo una única vez"
				 * no establece que "el siguiente" tiene que ser distinto al
				 * anterior. Entonces "aaab" sería válido porque el siguiente a
				 * una repetición de dos no se repite inmediatamente, pero
				 * "aaaab" sería inválido porque el siguiente a una repetición
				 * de dos es "aa", se repite.
				 */
				return Double.NEGATIVE_INFINITY;
			if ((repeticiones > 0) && (current != previous)) {
				/*
				 * si venía de una repetición y el actual es distinto al
				 * anterior, voy a *pispear* adelante para ver si también es una
				 * repetición
				 */
				char next = iterator.next();
				if (next == current)
					return Double.NEGATIVE_INFINITY;
				else {
					/*
					 * si no violaba la regla, vuelvo para atrás y reinicio las
					 * repeticiones
					 */
					iterator.previous();
					repeticiones = 0;
				}
			}
			repeticiones = current == previous ? repeticiones + 1 : 0;
			iterator.next();
		}
		return repeticiones < 3 ? 0 : Double.NEGATIVE_INFINITY;
	}
}
