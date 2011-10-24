package utn.frba.ia.ga.tpG14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.jaga.definitions.GAParameterSet;
import org.jaga.definitions.GAResult;
import org.jaga.hooks.AnalysisHook;
import org.jaga.masterAlgorithm.ReusableSimpleGA;
import org.jaga.selection.RouletteWheelSelection;
import org.jaga.selection.TournamentSelection;
import org.jaga.util.DefaultParameterSet;

import utn.frba.ia.ga.tpG14.reglas.DistribucionAlfanumericaRegla;
import utn.frba.ia.ga.tpG14.reglas.MayusMinRegla;
import utn.frba.ia.ga.tpG14.reglas.PalabrasProbablesRegla;
import utn.frba.ia.ga.tpG14.reglas.Regla;
import utn.frba.ia.ga.tpG14.reglas.Regla11CaseSensitiveFlexibilizada;
import utn.frba.ia.ga.tpG14.reglas.Regla2;
import utn.frba.ia.ga.tpG14.reglas.Regla9;
import utn.frba.ia.ga.tpG14.util.PropertiesConfig;

public class PasswordCracker {

	public PasswordCracker() {
	}

	public void exec() {
		PropertiesConfig config = this.cargarConfiguracion();
		String algoritmoSeleccion = config.getAlgoritmoSeleccion();
		String metodoPoblacionInicial = config.getMetodoPoblacionInicial();
		String inyeccionPatrones = config.getInyeccionPatrones(); // "si" ó "no"
		int tamanioPoblacion = config.getTamanioPoblacion();
		int cantidadGeneraciones = config.getCantidadGeneraciones();

		GAParameterSet params = new DefaultParameterSet();

		params.setPopulationSize(tamanioPoblacion);
		params.setMaxGenerationNumber(cantidadGeneraciones);

		List<Regla> reglas = this.initializeReglas();

		PasswordFitness passwordFitness = new PasswordFitness(reglas);
		params.setFitnessEvaluationAlgorithm(passwordFitness);

		// Seteamos el algoritmo de selección
		if (algoritmoSeleccion.equalsIgnoreCase("Ruleta"))
			params.setSelectionAlgorithm(new RouletteWheelSelection(-1));
		else if (algoritmoSeleccion.equalsIgnoreCase("Torneo"))
			params.setSelectionAlgorithm(new TournamentSelection());
		// params.setSelectionAlgorithm(new
		// TwoTournamentProbabalisticSelection());

		StringIndividualFactory fact = initializeIndividualsFactory(
				metodoPoblacionInicial.equalsIgnoreCase("ad-hoc"),
				inyeccionPatrones.equalsIgnoreCase("si"));
		params.setIndividualsFactory(fact);

		ReusableSimpleGA ga = new ReusableSimpleGA(params);

		AnalysisHook hook = initializeAnalysisHook(ga);

		hook.reset();
		GAResult result = (ga).exec();

		System.out.println("\nTODO HECHO.\n");
		System.out.println("Result is: " + result);
	}

	private PropertiesConfig cargarConfiguracion() {
		PropertiesConfig config = new PropertiesConfig("config.ini");
		try {
			config.cargarConfiguracion();
		} catch (FileNotFoundException e) {
			config.crearConfiguracionDefault();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Error al leer archivo de configuración", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
		return config;
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

	private StringIndividualFactory initializeIndividualsFactory(boolean adHoc,
			boolean inyectaAnn) {
		if (adHoc && !inyectaAnn)
			return new StringIndividualFactory(12, // Longitud
					48, // Codepoint_0
					122 // Codepoint_z
			);
		else if (adHoc && inyectaAnn)
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