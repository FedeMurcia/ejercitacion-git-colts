package utn.frba.ia.ga.tpG14;

import java.util.HashMap;
import java.util.Map;

import org.jaga.definitions.GAParameterSet;
import org.jaga.definitions.GAResult;
import org.jaga.hooks.AnalysisHook;
import org.jaga.masterAlgorithm.ReusableSimpleGA;
import org.jaga.selection.RouletteWheelSelection;
import org.jaga.util.DefaultParameterSet;
import org.junit.Test;

import utn.frba.ia.ga.tpG14.reglas.DistribucionAlfanumericaRegla;
import utn.frba.ia.ga.tpG14.reglas.MayusMinRegla;
import utn.frba.ia.ga.tpG14.reglas.PalabrasProbablesRegla;
import utn.frba.ia.ga.tpG14.reglas.Regla11CaseSensitive;
import utn.frba.ia.ga.tpG14.reglas.Regla11CaseSensitiveFlexibilizada;
import utn.frba.ia.ga.tpG14.reglas.Regla2;
import utn.frba.ia.ga.tpG14.reglas.Regla9;

public class PasswordFitnessTest {

	@Test
	public void pruebaPasswordFitness() {
		GAParameterSet params = new DefaultParameterSet();
		params.setPopulationSize(100);
		params.setMaxGenerationNumber(100);

		// Armamos un map de palabras probables : puntos por cada una
		Map<String, Double> palabras = new HashMap<String, Double>(3);
		// palabras.put("futuro", 50d);
		// palabras.put("dElOrEaN", 12d);
		// palabras.put("skynet", 1000d);
		palabras.put("john", 50d);
		palabras.put("inco", 75d);
		palabras.put("skynet", 100d);
		palabras.put("darpa", 150d);

		// Seteamos el algoritmo de fitness de password
		PasswordFitness passwordFitness = new PasswordFitness(new Regla2(),
				new Regla9(), new Regla11CaseSensitiveFlexibilizada(500),
				new PalabrasProbablesRegla(palabras),
				new DistribucionAlfanumericaRegla(), new MayusMinRegla());
		params.setFitnessEvaluationAlgorithm(passwordFitness);

		// Seteamos el algoritmo de selección
		// params.setSelectionAlgorithm(new RouletteWheelSelection(-10E10));
		params.setSelectionAlgorithm(new RouletteWheelSelection(-1));

		// Seteamos la factory de individuos
		StringIndividualFactory fact = new StringIndividualFactory(
				12, // Longitud 
				48, // Codepoint_0
				122 // Codepoint_z
		);
		params.setIndividualsFactory(fact);

		ReusableSimpleGA ga = new ReusableSimpleGA(params);
		AnalysisHook hook = new AnalysisHook();
//		hook.setAnalyseGenMaxFit(true);
		hook.setLogStream(System.out);
		hook.setUpdateDelay(100);
		hook.setAnalyseGenMinFit(true);
		ga.addHook(hook);

		final int attempts = 1;

		GAResult[] allResults = new GAResult[attempts];
		for (int i = 0; i < attempts; i++) {
			hook.reset();
			GAResult result = (ga).exec();
			allResults[i] = result;
		}
		System.out.println("\nTODO HECHO.\n");
		for (int i = 0; i < attempts; i++) {
			System.out.println("Result " + i + " is: " + allResults[i]);
		}
	}

}
