package utn.frba.ia.ga.tpG14.reglas;

import java.text.StringCharacterIterator;

/**
 * R9) Puede haber juntos dos caracteres iguales siempre y cuando el siguiente
 * car�cter aparezca s�lo una �nica vez (por ejemplo, la cadena �..23345..� es
 * v�lida, pero la cadena �..2334445..� es inv�lida).
 * 
 * @author Alme
 * 
 */
public class Regla9 implements Regla {

	/**
	 * Regla binaria. Retorna 10 puntos si pasa la regla, -1 si falla.
	 */
	@Override
	public double evaluar(String individuo) {
		/*
		 * Puede haber juntos dos caracteres iguales siempre y cuando el
		 * siguiente car�cter aparezca s�lo una �nica vez (por ejemplo, la
		 * cadena �..23345..� es v�lida, pero la cadena �..2334445..� es
		 * inv�lida).
		 * 
		 * alme: asumo que es v�lido 123345674, es decir, que el 'aparece s�lo
		 * una �nica vez' vale para la repetici�n inmediata, pero puede
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
				 * Considero 3 repeticiones (4 ocurrencias) una violaci�n de la
				 * regla ya que
				 * "dos caracteres iguales siempre y cuando el siguiente car�cter aparezca s�lo una �nica vez"
				 * no establece que "el siguiente" tiene que ser distinto al
				 * anterior. Entonces "aaab" ser�a v�lido porque el siguiente a
				 * una repetici�n de dos no se repite inmediatamente, pero
				 * "aaaab" ser�a inv�lido porque el siguiente a una repetici�n
				 * de dos es "aa", se repite.
				 */
				return -1;
			if ((repeticiones > 0) && (current != previous)) {
				/*
				 * si ven�a de una repetici�n y el actual es distinto al
				 * anterior, voy a *pispear* adelante para ver si tambi�n es una
				 * repetici�n
				 */
				char next = iterator.next();
				if (next == current)
					return -1;
				else {
					/*
					 * si no violaba la regla, vuelvo para atr�s y reinicio las
					 * repeticiones
					 */
					iterator.previous();
					repeticiones = 0;
				}
			}
			repeticiones = current == previous ? repeticiones + 1 : 0;
			iterator.next();
		}
		return repeticiones < 3 ? 10 : -1;
	}
}
