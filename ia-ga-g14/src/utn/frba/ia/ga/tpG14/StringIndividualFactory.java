package utn.frba.ia.ga.tpG14;

import java.util.*;

import javax.swing.text.html.*;

import org.jaga.definitions.GAParameterSet;
import org.jaga.definitions.Individual;
import org.jaga.definitions.IndividualsFactory;
import org.jaga.individualRepresentation.greycodedNumbers.*;
import org.jaga.util.*;

import utn.frba.ia.ga.tpG14.reglas.Regla2;
import utn.frba.ia.ga.tpG14.util.BinaryStringBuilder;

public class StringIndividualFactory implements IndividualsFactory {

	private Random random = new Random();

	private int stringLength;
	private int minCodePoint;
	private int maxCodePoint;

	/**
	 * Construye una factory que genera strings del tama�o indicado en el
	 * par�metro.
	 * 
	 * @param stringLength
	 *            el tama�o (en caracteres) que tendr�n los individuos
	 *            generados.
	 */
	public StringIndividualFactory(int stringLength) {
		this(stringLength, 0, 256);
	}

	public StringIndividualFactory(int stringLength, int minCodePoint,
			int maxCodePoint) {
		this.stringLength = stringLength;
		this.setMinCodePoint(minCodePoint);
		this.setMaxCodePoint(maxCodePoint);
	}

	@Override
	public Individual createDefaultIndividual(GAParameterSet params) {
		StringBuilder stringBuilder = new StringBuilder(getStringLength());

		for (int i = 0; i < getStringLength(); i++)
			stringBuilder.append("a");

		String string = stringBuilder.toString();
		return new StringIndividual(string);
	}

	@Override
	public Individual createRandomIndividual(GAParameterSet params) {
		StringBuilder stringBuilder = new StringBuilder(getStringLength());

		for (int i = 0; i < getStringLength(); i++)
			stringBuilder.append(this.proximoCaracterRandom(params));

		String string = stringBuilder.toString();
		return new StringIndividual(string);
	}

	protected char proximoCaracterRandom(GAParameterSet params) {
		return (char) params.getRandomGenerator().nextInt(minCodePoint,
				maxCodePoint);
	}

	@Override
	public Individual createSpecificIndividual(Object init,
			GAParameterSet params) throws NullPointerException,
			ClassCastException {
		if (null == init)
			throw new NullPointerException(
					"Initialisation value for StringIndividual may not be null");

		if (init instanceof StringIndividual)
			return createSpecificIndividual((StringIndividual) init);

		if (init instanceof String)
			return createSpecificIndividual((String) init);

		if (init instanceof BitString)
			return createSpecificIndividual((BitString) init);

		throw new ClassCastException(
				"Initialisation value for StringIndividual "
						+ "must be of type StringIndividual or String (but is "
						+ init.getClass() + ")");

	}

	private Individual createSpecificIndividual(BitString init) {
		BinaryStringBuilder binaryStringBuilder = new BinaryStringBuilder();
		for (int i = 0; i < init.getLength(); i++)
			binaryStringBuilder.setNextBit(init.get(i));
		return new StringIndividual(binaryStringBuilder.build());
	}

	private Individual createSpecificIndividual(String init) {
		return new StringIndividual(init);
	}

	private Individual createSpecificIndividual(StringIndividual init) {
		return new StringIndividual(init.getString());
	}

	public void setMinCodePoint(int minCodePoint) {
		this.minCodePoint = minCodePoint;
	}

	public int getMinCodePoint() {
		return minCodePoint;
	}

	public void setMaxCodePoint(int maxCodePoint) {
		this.maxCodePoint = maxCodePoint;
	}

	public int getMaxCodePoint() {
		return maxCodePoint;
	}

	public boolean valid(Individual tst) {
//		return new Regla2().evaluar(((StringIndividual) tst).getString()) != FuncionAptitud.APTITUD_INDIVIDUO_INVALIDO;
		return true;
	}

	protected int getStringLength() {
		return stringLength;
	}

}
