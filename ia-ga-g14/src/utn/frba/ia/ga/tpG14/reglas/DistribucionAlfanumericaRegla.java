package utn.frba.ia.ga.tpG14.reglas;

import java.text.StringCharacterIterator;

public class DistribucionAlfanumericaRegla implements Regla {

	private double puntajeMaximo = 15;

	@Override
	public double evaluar(String individuo) {
		if (individuo.isEmpty())
			return 0;

		StringCharacterIterator iterator = new StringCharacterIterator(
				individuo);
		double letras = 0, digitos = 0;
		while (iterator.current() != StringCharacterIterator.DONE) {
			/*
			 * recorreremos al individuo contando letras y números
			 */
			char caracter = iterator.current();
			if (Character.isLetter(caracter))
				letras++;
			else if (Character.isDigit(caracter))
				digitos++;
			iterator.next();
		}

		/*
		 * Armamos la proporción de letras (o números) sobre el total de letras
		 * y números (no sobre el total del string, ya que evaluaremos sólo la
		 * distribución alfanumérica) y convertimos ese valor en una puntuación
		 * 0-1 tal que el 1 represente la mejor distribución (50-50)
		 */
		double proporcionLetras = letras / (letras + digitos);
		double proporcionDistribucionAlfanumerica = 1 - 2 * Math
				.abs(0.5 - proporcionLetras);

		return proporcionDistribucionAlfanumerica * this.getPuntajeMaximo();
	}

	public double getPuntajeMaximo() {
		return puntajeMaximo;
	}

	public void setPuntajeMaximo(double puntajeMaximo) {
		this.puntajeMaximo = puntajeMaximo;
	}
}
