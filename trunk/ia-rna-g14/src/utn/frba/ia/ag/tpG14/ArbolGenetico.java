package utn.frba.ia.ag.tpG14;

import java.text.StringCharacterIterator;

public class ArbolGenetico {

	public double funcionAptitud(char[] individuo) {

		String stringIndividuo = individuo.toString();
		if (!this.checkLongitud(stringIndividuo)
				|| !this.checkCaracteres(stringIndividuo)
				|| !this.checkAnn(stringIndividuo)) {

			return -1;

		} else {

			return this.checkMayusMins(stringIndividuo)
					+ this.checkNumeros(stringIndividuo)
					- this.chekCaracteresRepetidos(individuo);

		}

	}

	private boolean checkLongitud(String individuo) {

		return individuo.length() == 12;

	}

	private boolean checkAnn(String individuo) {

		return individuo.contains("ann");

	}

	boolean checkCaracteres(String individuo) {
		/*
		 * Puede haber juntos dos caracteres iguales siempre y cuando el
		 * siguiente carácter aparezca sólo una única vez (por ejemplo, la
		 * cadena ‘..23345..’ es válida, pero la cadena ‘..2334445..’ es
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
				return false;
			if ((repeticiones > 0) && (current != previous)) {
				/*
				 * si venía de una repetición y el actual es distinto al
				 * anterior, voy a *pispear* adelante para ver si también es una
				 * repetición
				 */
				char next = iterator.next();
				if (next == current)
					return false;
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
		return repeticiones < 3;
	}

	private double checkNumeros(String a) {

		if (a.contains("0") || a.contains("1") || a.contains("2")
				|| a.contains("3") || a.contains("4") || a.contains("5")
				|| a.contains("6") || a.contains("7") || a.contains("8")
				|| a.contains("9")) {

			return 0.25;

		} else {

			return 0;
		}

	}

	private double checkMayusMins(String a) {

		// no se como implemetarlo la idea
		// es que verifique si existen mayusculas
		// y minusculas y si es asi que devuelva 0.25

		return 0;
	}

	private double chekCaracteresRepetidos(char[] a) {

		int cont;
		double res = 0;

		for (int i = 0; i <= a.length; i++) {

			cont = 0;

			for (int j = i + 1; j <= a.length; j++) {

				if (a[i] == a[j]) {

					cont++;
				}

			}

			res += cont;
		}

		return res * 0.04;
	}
}
