package utn.frba.ia.ga.tpG14.reglas;

import utn.frba.ia.ga.tpG14.FuncionAptitud;

/**
 * R11) El resultado debe contener la palabra "ann" en alguna parte de la
 * cadena.
 * 
 * @author Alme
 * 
 */
public class Regla11CaseSensitive implements Regla {
	/**
	 * Regla binaria. Retorna 0 puntos si pasa la regla,
	 * FuncionAptitud.APTITUD_INDIVIDUO_INVALIDO si falla.
	 */
	@Override
	public double evaluar(String individuo) {
		return (individuo.indexOf("ann") != -1) ? 0
				: FuncionAptitud.APTITUD_INDIVIDUO_INVALIDO;
	}

}
