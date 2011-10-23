package utn.frba.ia.ga.tpG14.reglas;

import utn.frba.ia.ga.tpG14.FuncionAptitud;

/**
 * R11) El resultado debe contener la palabra "ann" en alguna parte de la
 * cadena.
 * 
 * Regla flexibilizada tal que si no aparece 'ann', no clasifique al individuo
 * como inválido.
 * 
 * @author Alme
 * 
 */
public class Regla11CaseSensitiveFlexibilizada implements Regla {
	private double puntajeMaximo;

	public Regla11CaseSensitiveFlexibilizada(double puntajeMaximo) {
		this.puntajeMaximo = puntajeMaximo;
	}

	/**
	 * Retorna 100 puntos (configurable) si pasa la regla, 0 si falla.
	 */
	@Override
	public double evaluar(String individuo) {
		return (individuo.indexOf("ann") != -1) ? getPuntajeMaximo() : 0;
	}

	public double getPuntajeMaximo() {
		if (puntajeMaximo == 0)
			System.out.println("puntaje no configurado");
		return puntajeMaximo == 0d ? 100 : puntajeMaximo;
	}

	public void setPuntajeMaximo(double puntajeMaximo) {
		this.puntajeMaximo = puntajeMaximo;
	}

}
