package utn.frba.ia.ga.tpG14.reglas;

import java.util.Map;
import java.util.Set;

/**
 * Se me había ocurrido, pensando en el enunciado, en cómo sacar una password,
 * en dar una lista de posibles palabras que podríaan estar incluídas en la
 * password, por ejemplo: futuro, conocimiento, 18/7/2042 (fecha de nacimiento
 * del agente enviado desde el futuro), DARPA, resistencia, etc. Y definir, para
 * mantenerlo realista, que no puede tener más de 2 de esas palabras (relleno de
 * caracteres random que cumplan las otras reglas).
 * 
 * @author Alme
 * 
 */
public class PalabrasProbablesRegla implements Regla {

	private Map<String, Double> puntajePorPalabra;

	public PalabrasProbablesRegla(Map<String, Double> puntajePorPalabra) {
		super();
		this.puntajePorPalabra = puntajePorPalabra;
	}

	@Override
	public double evaluar(String individuo) {
		Double puntaje = 0d;
		Set<String> palabras = puntajePorPalabra.keySet();
		for(String palabra : palabras){
			if(individuo.indexOf(palabra)!=-1){
				puntaje += puntajePorPalabra.get(palabra);
			}
		}
		return puntaje;
	}
}
