package utn.frba.ia.ga.tpG14;

import java.util.List;

import utn.frba.ia.ga.tpG14.reglas.Regla;

public class PasswordFuncionAptitud implements FuncionAptitud {
	private List<Regla> reglas;

	public PasswordFuncionAptitud(List<Regla> reglas) {
		this.reglas = reglas;
	}

	public double evaluar(Object individuo) {
		double aptitud = 0;
		String stringIndividuo = (String) individuo;

		for (Regla regla : reglas) {
			double _aptitud = regla.evaluar(stringIndividuo);
			if (_aptitud == FuncionAptitud.APTITUD_INDIVIDUO_INVALIDO) {
				aptitud = FuncionAptitud.APTITUD_INDIVIDUO_INVALIDO;
				break;
			}
			aptitud += _aptitud;
		}
		return aptitud;
	}
}
