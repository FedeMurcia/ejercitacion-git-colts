package utn.frba.ia.ga.tpG14.reglas;

import java.text.StringCharacterIterator;

/**
 * R2) El resultado es una cadena de 12 caracteres alfanuméricos (0-9 + A-Z +
 * a-z) donde importan si son mayúsculas o minúsculas ("A" es distinto de "a")
 * 
 * @author Alme
 * 
 */
public class Regla2 implements Regla {

	/**
	 * Regla binaria. Retorna 0 puntos si pasa la regla, Double.NEGATIVE_INFINITY si falla.
	 */
	@Override
	public double evaluar(String individuo) {
		boolean condicionLength = individuo.length() == 12;
		StringCharacterIterator iterator = new StringCharacterIterator(
				individuo);
		while (iterator.current() != StringCharacterIterator.DONE) {
			if (!Character.isLetterOrDigit(iterator.current()))
				return Double.NEGATIVE_INFINITY;
			iterator.next();
		}
		return condicionLength ? 0 : Double.NEGATIVE_INFINITY;
	}

}
