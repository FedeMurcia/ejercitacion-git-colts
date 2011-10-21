package utn.frba.ia.ga.tpG14;

import org.jaga.definitions.*;

/**
 * Define la estructura del individuo (cromosoma)
 * 
 * @author g14
 *
 */
public class StringIndividual implements Individual {

    private Fitness fitness;
    private final String string;

    public StringIndividual(String string) {
	this.string = string;
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

}
