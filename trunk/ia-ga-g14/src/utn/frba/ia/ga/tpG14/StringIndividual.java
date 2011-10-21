package utn.frba.ia.ga.tpG14;

import org.jaga.definitions.Fitness;
import org.jaga.individualRepresentation.greycodedNumbers.BinaryEncodedIndividual;
import org.jaga.util.BitString;

/**
 * Define la estructura del individuo (cromosoma)
 * 
 * @author g14
 * 
 */
public class StringIndividual implements BinaryEncodedIndividual {

	private Fitness fitness;
	private BitString representation;

	private final String string;

	public StringIndividual(String string) {
		this.string = string;
		this.representation = new BitString(string.length());
		initializeRepresentation();
	}

	private void initializeRepresentation() {
	}

	@Override
	public Fitness getFitness() {
		return fitness;
	}

	@Override
	public void setFitness(Fitness fitness) {
		this.fitness = fitness;
	}

	/**
	 * Devuelve el string contenido por el individuo.
	 * 
	 * @return string
	 */
	public String getString() {
		return string;
	}

	@Override
	public BitString getBitStringRepresentation() {
		return representation;
	}

	@Override
	public void setBitStringRepresentation(BitString bits) {
		this.representation = bits;
	}

}
