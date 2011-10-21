package utn.frba.ia.ga.tpG14.reglas;

/**
 * R11) El resultado debe contener la palabra "ann" en alguna parte de la
 * cadena.
 * 
 * @author Alme
 * 
 */
public class Regla11CaseSensitive implements Regla {
	/**
	 * Regla binaria. Retorna 10 puntos si pasa la regla, -1 si falla.
	 */
	@Override
	public double evaluar(String individuo) {
		return (individuo.indexOf("ann") != -1) ? 10 : -1;
	}

}
