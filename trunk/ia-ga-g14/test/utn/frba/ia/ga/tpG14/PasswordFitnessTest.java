package utn.frba.ia.ga.tpG14;

import java.util.*;

import org.jaga.definitions.*;
import org.jaga.exampleApplications.*;
import org.jaga.hooks.*;
import org.jaga.individualRepresentation.greycodedNumbers.*;
import org.jaga.masterAlgorithm.*;
import org.jaga.selection.*;
import org.jaga.util.*;
import org.junit.*;

import utn.frba.ia.ga.tpG14.reglas.*;


public class PasswordFitnessTest {

    @Test
    public void pruebaPasswordFitness() {
	GAParameterSet params = new DefaultParameterSet();
	params.setPopulationSize(3);
	
	Map<String, Double> palabras = new HashMap<String, Double>(3);
	palabras.put("futuro", 50d);
	palabras.put("dElOrEaN", 12d);
	palabras.put("skynet", 1000d);
	
	params.setFitnessEvaluationAlgorithm(new PasswordFitness(new Regla2(), new Regla9(), new Regla11CaseSensitive(), new PalabrasProbablesRegla(palabras)));
	
	params.setSelectionAlgorithm(new RouletteWheelSelection(-10E10));
	params.setMaxGenerationNumber(100);
	
	// TODO Cmabiar factoría
	NDecimalsIndividualSimpleFactory fact = new NDecimalsIndividualSimpleFactory(2, 6, 30);
	fact.setConstraint(0, new RangeConstraint(-6, 6));
	fact.setConstraint(1, new RangeConstraint(-4, 4));
	params.setIndividualsFactory(fact);

	ReusableSimpleGA ga = new ReusableSimpleGA(params);
	AnalysisHook hook = new AnalysisHook();
	hook.setLogStream(System.out);
	hook.setUpdateDelay(100);
	hook.setAnalyseGenMinFit(true);
	ga.addHook(hook);

	final int attempts = 1;

	GAResult [] allResults = new GAResult[attempts];
	for (int i = 0; i < attempts; i++) {
		hook.reset();
		GAResult result = ((ReusableSimpleGA) ga).exec();
		allResults[i] = result;
	}
	System.out.println("\nTODO HECHO.\n");
	for (int i = 0; i < attempts; i++) {
		System.out.println("Result " + i + " is: " + allResults[i]);
	}
    }
    
}
