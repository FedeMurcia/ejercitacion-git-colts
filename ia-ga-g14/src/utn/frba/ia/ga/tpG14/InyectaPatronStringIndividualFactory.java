package utn.frba.ia.ga.tpG14;

import java.util.HashMap;
import java.util.Map;

import org.jaga.definitions.GAParameterSet;
import org.jaga.definitions.Individual;

public class InyectaPatronStringIndividualFactory extends
		StringIndividualFactory {

	private Map<String, Double> patronesMap;

	public InyectaPatronStringIndividualFactory(int stringLength,
			int minCodePoint, int maxCodePoint) {
		super(stringLength, minCodePoint, maxCodePoint);
		this.patronesMap = new HashMap<String, Double>();
	}

	public StringIndividualFactory addPatron(String patron, double probabilidad) {
		this.patronesMap.put(patron, probabilidad);
		return this;
	}
	
	@Override
	public Individual createRandomIndividual(GAParameterSet params) {
		StringBuilder stringBuilder = new StringBuilder(getStringLength());

		for (int i = 0; i < getStringLength(); i++)
			stringBuilder.append(this.proximoCaracterRandom(params));

		for(String patron : patronesMap.keySet()){
			double random = params.getRandomGenerator().nextDouble();
			if(random < patronesMap.get(patron)){
				double randomPosicionInicial = params.getRandomGenerator().nextDouble();
				int indiceInicio = (int) (randomPosicionInicial * (getStringLength() - patron.length() - 1));
				stringBuilder.replace(indiceInicio , indiceInicio+patron.length(), patron);
			}
		}
		String string = stringBuilder.toString();
		return new StringIndividual(string);
	}


}
