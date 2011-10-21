package utn.frba.ia.ga.tpG14;

import static org.junit.Assert.*;

import org.jaga.definitions.Individual;
import org.jaga.util.BitString;
import org.junit.Test;

public class BinaryStringsThingies {

	@Test
	public void test() {
		StringIndividual individual = new StringIndividual("foobar");
		BitString bitStringRepresentation = individual.getBitStringRepresentation();
		StringIndividualFactory stringIndividualFactory = new StringIndividualFactory(6);
		StringIndividual individual2 = (StringIndividual) stringIndividualFactory.createSpecificIndividual(bitStringRepresentation, null);
		System.out.println(individual.getString());
		System.out.println(individual2.getString());
		assertEquals(individual.getString(), individual2.getString());
	}

}
