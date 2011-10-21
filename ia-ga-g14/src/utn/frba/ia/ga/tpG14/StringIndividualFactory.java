package utn.frba.ia.ga.tpG14;

import org.jaga.definitions.GAParameterSet;
import org.jaga.definitions.Individual;
import org.jaga.definitions.IndividualsFactory;

public class StringIndividualFactory implements IndividualsFactory {

	private int stringLength;

	/**
	 * Construye una factory que no limita el tamaño de los strings que genera
	 * (generaría strings de tamaño random? bueno, igual, no lo vamos a
	 * implementar, lala)
	 */
	public StringIndividualFactory() {
	}

	/**
	 * Construye una factory que genera strings del tamaño indicado en el
	 * parámetro.
	 * 
	 * @param stringLength
	 *            el tamaño (en caracteres) que tendrán los individuos
	 *            generados.
	 */
	public StringIndividualFactory(int stringLength) {
		this.stringLength = stringLength;
	}

	@Override
	public Individual createDefaultIndividual(GAParameterSet params) {
		return new StringIndividual("aaaaaaaaaaaa");
	}

	@Override
	public Individual createRandomIndividual(GAParameterSet params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Individual createSpecificIndividual(Object init,
			GAParameterSet params) throws NullPointerException,
			ClassCastException {
		// TODO Auto-generated method stub
		return null;
	}

}
