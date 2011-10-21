package utn.frba.ia.ga.tpG14;

import java.util.*;

import javax.swing.text.html.*;

import org.jaga.definitions.GAParameterSet;
import org.jaga.definitions.Individual;
import org.jaga.definitions.IndividualsFactory;

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

    public StringIndividualFactory(int stringLength, int minCodePoint, int maxCodePoint) {
	this.stringLength = stringLength;
	this.setMinCodePoint(minCodePoint);
	this.setMaxCodePoint(maxCodePoint);
    }


    @Override
    public Individual createDefaultIndividual(GAParameterSet params) {
	StringBuilder stringBuilder = new StringBuilder(stringLength);

	for (int i = 0; i < stringLength; i++)
	    stringBuilder.append("a");

	String string = stringBuilder.toString();
	return new StringIndividual(string);
    }

    @Override
    public Individual createRandomIndividual(GAParameterSet params) {
	StringBuilder stringBuilder = new StringBuilder(stringLength);

	for (int i = 0; i < stringLength; i++)
	    stringBuilder.append(this.proximoCaracterRandom(params));

	String string = stringBuilder.toString();
	return new StringIndividual(string);
    }

    private char proximoCaracterRandom(GAParameterSet params) {
	// TODO ¿Radix re inválido?
	return Character.forDigit(params.getRandomGenerator().nextInt(minCodePoint, maxCodePoint), 10);
    }

    @Override
    public Individual createSpecificIndividual(Object init, GAParameterSet params) throws NullPointerException,
	    ClassCastException {
	// TODO Auto-generated method stub
	return null;
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

}
