package utn.frba.ia.ga.tpG14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.PrintStream;
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
		params.setPopulationSize(250);
		params.setMaxGenerationNumber(100);

		List<Regla> reglas = this.initializeReglas();

		PasswordFitness passwordFitness = new PasswordFitness(reglas);
		params.setFitnessEvaluationAlgorithm(passwordFitness);

		// Seteamos el algoritmo de selección
		params.setSelectionAlgorithm(new RouletteWheelSelection(-1));
//		 params.setSelectionAlgorithm(new TournamentSelection());
		// params.setSelectionAlgorithm(new
		// TwoTournamentProbabalisticSelection());

		StringIndividualFactory fact = initializeIndividualsFactory(true, true);
		params.setIndividualsFactory(fact);

		ReusableSimpleGA ga = new ReusableSimpleGA(params);

		AnalysisHook hook = initializeAnalysisHook(ga);

		hook.reset();
		GAResult result = (ga).exec();

		System.out.println("\nTODO HECHO.\n");
		System.out.println("Result is: " + result);
	}

	private AnalysisHook initializeAnalysisHook(ReusableSimpleGA ga) {
		// Propiedades de la interfaz gráfica
		AnalysisHook hook = new AnalysisHook();
		hook.setAnalyseGenMaxFit(true);
		hook.setAnalyseGenMinFit(false);
		hook.setAnalyseGenFitStdDeviation(false);
		hook.setPlotFrameTitle("Trabajo Práctico AG - Grupo 14");
		// hook.setLogStream(System.out);
		try {
			hook.setLogStream(new PrintStream(new File("analisis.log")));
		} catch (FileNotFoundException e) {
			System.err.println("Error al crear archivo de salida: " + e);
			System.err.println("Redirigiendo a STDOUT");
			hook.setLogStream(System.out);
		}
		hook.setUpdateDelay(10);
		ga.addHook(hook);
		return hook;
	}

	private StringIndividualFactory initializeIndividualsFactory(boolean adHoc, boolean inyectaAnn) {
		if (adHoc && !inyectaAnn)
			return new StringIndividualFactory(12, // Longitud
					48, // Codepoint_0
					122 // Codepoint_z
			);else 
		if (adHoc && inyectaAnn)
			return new InyectaPatronStringIndividualFactory(12, // Longitud
					48, // Codepoint_0
					122 // Codepoint_z
			).addPatron("ann", 0.1);
		else
			return new StringIndividualFactory(12);
	}

	private List<Regla> initializeReglas() {
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
		reglas.add(new Regla11CaseSensitiveFlexibilizada(500));

		// Armamos un map de palabras probables : puntos por cada una
		Map<String, Double> palabras = new HashMap<String, Double>(3);
		palabras.put("ia", 35d);
		palabras.put("john", 40d);
		palabras.put("inco", 50d);
		palabras.put("skynet", 60d);
		palabras.put("darpa", 70d);
		reglas.add(new PalabrasProbablesRegla(palabras));

		DistribucionAlfanumericaRegla distribucionAlfanumericaRegla = new DistribucionAlfanumericaRegla();
		distribucionAlfanumericaRegla.setPuntajeMaximo(80);
		reglas.add(distribucionAlfanumericaRegla);
		MayusMinRegla mayusMinRegla = new MayusMinRegla();
		mayusMinRegla.setPuntajeMaximo(120);
		reglas.add(mayusMinRegla);
		return reglas;
	}

	public static void main(String[] unusedArgs) {
		PasswordCracker demo = new PasswordCracker();
		demo.exec();
	}
}