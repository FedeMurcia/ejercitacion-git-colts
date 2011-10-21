package utn.frba.ia.ga.tpG14;

import java.util.Arrays;
import java.util.List;

import org.jaga.definitions.Fitness;
import org.jaga.definitions.FitnessEvaluationAlgorithm;
import org.jaga.definitions.GAParameterSet;
import org.jaga.definitions.Individual;
import org.jaga.definitions.Population;
import org.jaga.selection.AbsoluteFitness;

import utn.frba.ia.ga.tpG14.reglas.Regla;

/**
 * Este componente permite evaluar la fitness o aptitud de un individuo que
 * responda a la poblaci�n de passwords a evaluar.
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

		double fitnessValue = new PasswordFuncionAptitud(reglas)
				.evaluar(individuo.getString());

		Fitness fit = new AbsoluteFitness(fitnessValue);
		return fit;
	}

}
