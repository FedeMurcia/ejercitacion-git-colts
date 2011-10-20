package utn.frba.ia.ga.tpG14;

import org.jaga.definitions.*;
import org.jaga.exampleApplications.Example1Fitness;
import org.jaga.util.*;
import org.jaga.masterAlgorithm.*;
import org.jaga.individualRepresentation.greycodedNumbers.*;
import org.jaga.hooks.*;
import org.jaga.selection.*;

public class PasswordCracker {

	public PasswordCracker() {
	}

	public void exec() {

		GAParameterSet params = new DefaultParameterSet();
		params.setPopulationSize(0);
		params.setFitnessEvaluationAlgorithm(new Example1Fitness());
		params.setSelectionAlgorithm(new RouletteWheelSelection(-10E10));
		params.setMaxGenerationNumber(100);
		NDecimalsIndividualSimpleFactory fact = new NDecimalsIndividualSimpleFactory(2, 6, 30);
		fact.setConstraint(0, new RangeConstraint(-6, 6));
		fact.setConstraint(1, new RangeConstraint(-4, 4));
		params.setIndividualsFactory(fact);

		ReusableSimpleGA ga = new ReusableSimpleGA(params);
		AnalysisHook hook = new AnalysisHook();
		hook.setLogStream(System.out);
		hook.setUpdateDelay(100);
//		hook.setAnalyseGenMinFit(true);
		hook.setAnalyseGenMaxFit(true);
		ga.addHook(hook);

		final int attempts = 5;

		GAResult [] allResults = new GAResult[attempts];
		for (int i = 0; i < attempts; i++) {
			hook.reset();
			GAResult result = ((ReusableSimpleGA) ga).exec();
			allResults[i] = result;
		}
		System.out.println("\nALL DONE.\n");
		for (int i = 0; i < attempts; i++) {
			System.out.println("Result " + i + " is: " + allResults[i]);
		}

	}

	public static void main(String[] unusedArgs) {
		PasswordCracker demo = new PasswordCracker();
		demo.exec();
	}
}