package utn.frba.ia.ga.tpG14;

import java.util.*;

import org.jaga.definitions.*;
import org.jaga.selection.*;

import utn.frba.ia.ga.tpG14.reglas.*;

/**
 * Este componente permite evaluar la fitness o aptitud de un individuo
 * que responda a la poblaci�n de passwords a evaluar.
 * 
 * @author g14
 * 
 */
public class PasswordFitness implements FitnessEvaluationAlgorithm {

	private List<Regla> reglas;

	public PasswordFitness(Regla... reglas) {
	    this.reglas = Arrays.asList(reglas);
	}

	@Override
	public Class<?> getApplicableClass() {
		return StringIndividual.class;
	}

	@Override
	public Fitness evaluateFitness(Individual individual, int age,
			Population population, GAParameterSet params)
			throws ClassCastException {
		StringIndividual individuo = (StringIndividual) individual;
		
		String stringIndividuo = individuo.getString();
		double fitnessValue = 0;

		for (Regla regla : reglas) {
			double _fitness = regla.evaluar(stringIndividuo);
			if (_fitness == Double.NEGATIVE_INFINITY) {
				fitnessValue = Double.NEGATIVE_INFINITY;
				break;
			}
			fitnessValue += _fitness;
		}

		Fitness fit = new AbsoluteFitness(fitnessValue);
		return fit;
	}

}
