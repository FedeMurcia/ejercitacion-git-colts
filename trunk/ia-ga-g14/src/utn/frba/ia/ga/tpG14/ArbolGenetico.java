package utn.frba.ia.ga.tpG14;

import java.text.StringCharacterIterator;

public class ArbolGenetico {

	public double funcionAptitud(char[] individuo) {

		String stringIndividuo = individuo.toString();
		if (!this.checkLongitud(stringIndividuo)
				|| !this.checkAnn(stringIndividuo)) {
			return -1;

		} else {

			return this.checkNumeros(stringIndividuo)
					- this.chekCaracteresRepetidos(individuo);
		}

	}

	private boolean checkLongitud(String individuo) {
		return individuo.length() == 12;
	}

	private boolean checkAnn(String individuo) {
		return individuo.contains("ann");
	}

	

	private double checkNumeros(String a) {
		if (a.contains("0") || a.contains("1") || a.contains("2")
				|| a.contains("3") || a.contains("4") || a.contains("5")
				|| a.contains("6") || a.contains("7") || a.contains("8")
				|| a.contains("9")) {
			return 0.25;
		} else {
			return 0;
		}

	}

	private double chekCaracteresRepetidos(char[] a) {
		int cont;
		double res = 0;

		for (int i = 0; i <= a.length; i++) {
			cont = 0;
			for (int j = i + 1; j <= a.length; j++) {
				if (a[i] == a[j]) {
					cont++;
				}
			}
			res += cont;
		}

		return res * 0.04;
	}
}