package utn.frba.ia.ga.tpG14.reglas;

import java.text.StringCharacterIterator;

import utn.frba.ia.ga.tpG14.FuncionAptitud;

/**
 * R2) El resultado es una cadena de 12 caracteres alfanuméricos (0-9 + A-Z +
 * a-z) donde importan si son mayúsculas o minúsculas ("A" es distinto de "a")
 * 
 * @author Alme
 * 
 */
public class Regla2 implements Regla {

	/**
	 * Regla binaria. Retorna 0 puntos si pasa la regla,
	 * FuncionAptitud.APTITUD_INDIVIDUO_INVALIDO si falla.
	 */
	@Override
	public double evaluar(String individuo) {
		boolean condicionLength = individuo.length() == 12;
		StringCharacterIterator iterator = new StringCharacterIterator(
				individuo);
		while (iterator.current() != StringCharacterIterator.DONE) {
			if (!this.isLetterOrDigit(iterator.current()))
				return FuncionAptitud.APTITUD_INDIVIDUO_INVALIDO;
			iterator.next();
		}
		return condicionLength ? 0 : FuncionAptitud.APTITUD_INDIVIDUO_INVALIDO;
	}

	private boolean isLetterOrDigit(char caracter) {
		if(caracter >= 48 && caracter <= 57) return true;
		if(caracter >= 65 && caracter <= 90) return true;
		if(caracter >= 97 && caracter <= 122) return true;
		return false;
	}

}
