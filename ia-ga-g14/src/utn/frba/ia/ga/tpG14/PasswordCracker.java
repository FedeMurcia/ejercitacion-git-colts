package utn.frba.ia.ga.tpG14;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jaga.definitions.*;
import org.jaga.exampleApplications.Example1Fitness;
import org.jaga.util.*;
import org.jaga.masterAlgorithm.*;
import org.jaga.individualRepresentation.greycodedNumbers.*;
import org.jaga.hooks.*;
import org.jaga.selection.*;

import utn.frba.ia.ga.tpG14.reglas.DistribucionAlfanumericaRegla;
import utn.frba.ia.ga.tpG14.reglas.MayusMinRegla;
import utn.frba.ia.ga.tpG14.reglas.PalabrasProbablesRegla;
import utn.frba.ia.ga.tpG14.reglas.Regla;
import utn.frba.ia.ga.tpG14.reglas.Regla11CaseSensitive;
import utn.frba.ia.ga.tpG14.reglas.Regla11CaseSensitiveFlexibilizada;
import utn.frba.ia.ga.tpG14.reglas.Regla2;
import utn.frba.ia.ga.tpG14.reglas.Regla9;

public class PasswordCracker {

	public PasswordCracker() {
	}

	public void exec() {
		GAParameterSet params = new DefaultParameterSet();
		params.setPopulationSize(500);
		params.setMaxGenerationNumber(100);

		// Armamos un map de palabras probables : puntos por cada una
		Map<String, Double> palabras = new HashMap<String, Double>(3);
		// palabras.put("futuro", 50d);
		// palabras.put("dElOrEaN", 12d);
		// palabras.put("skynet", 1000d);
		palabras.put("ia", 35d);
		palabras.put("john", 50d);
		palabras.put("inco", 100d);
		palabras.put("skynet", 100d);
		palabras.put("darpa", 150d);

		// Seteamos el algoritmo de fitness de password
		List<Regla> reglas = new LinkedList<Regla>();
		reglas.add(new Regla2());
		reglas.add(new Regla9());
		/*
		 * Las experiencias muestran que si usáramos la regla 11 sin
		 * flexibilizar, dado que no es muy probable que aparezca la cadena
		 * 'ann' por defecto en una cadena, si marcamos a éstos individuos como
		 * inválidos la población no se logrará formar bien y nunca 'despegará'.
		 * En cambio, si usamos la regla flexibilizada, damos la posibilidad a
		 * que la población arranque sin ésta condición. Así, a través de todas 
		 * las generaciones, seguro en alguna aparecerá la cadena 'ann', y dado 
		 * que su puntaje es alto, ésta característica se mantendrá.
		 */
		// reglas.add(new Regla11CaseSensitive());
		reglas.add(new Regla11CaseSensitiveFlexibilizada());
		reglas.add(new PalabrasProbablesRegla(palabras));
		reglas.add(new DistribucionAlfanumericaRegla());
		reglas.add(new MayusMinRegla());

		PasswordFitness passwordFitness = new PasswordFitness(reglas);
		params.setFitnessEvaluationAlgorithm(passwordFitness);

		// Seteamos el algoritmo de selección
		params.setSelectionAlgorithm(new RouletteWheelSelection(-1));
		// params.setSelectionAlgorithm(new TournamentSelection());
		// params.setSelectionAlgorithm(new
		// TwoTournamentProbabalisticSelection());

		// Seteamos la factory de individuos
		StringIndividualFactory fact = new StringIndividualFactory(12, // Longitud
				48, // Codepoint_0
				122 // Codepoint_z
		);
		params.setIndividualsFactory(fact);

		ReusableSimpleGA ga = new ReusableSimpleGA(params);
		
		// Propiedades de la interfaz gráfica
		AnalysisHook hook = new AnalysisHook();
		hook.setAnalyseGenMaxFit(true);
		hook.setAnalyseGenMinFit(false);
		hook.setAnalyseGenFitStdDeviation(false);
		hook.setPlotFrameTitle("Trabajo Práctico AG - Grupo 14");
		hook.setLogStream(System.out);
		hook.setUpdateDelay(100);
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

	public static void main(String[] unusedArgs) {
		PasswordCracker demo = new PasswordCracker();
		demo.exec();
	}
}