package utn.frba.ia.ga.tpG14.util;

import org.jaga.util.BitString;

public class TalladorBinario {

	private final BitString representation;
	private int indiceBitActual;

	public TalladorBinario(BitString representation) {
		this.representation = representation;
		this.indiceBitActual = 0;
	}

	public void tallar(char current) {
		getRepresentation().set(indiceBitActual++,((0x01) | current) == current);
		getRepresentation().set(indiceBitActual++,((0x02) | current) == current);
		getRepresentation().set(indiceBitActual++,((0x04) | current) == current);
		getRepresentation().set(indiceBitActual++,((0x08) | current) == current);
		getRepresentation().set(indiceBitActual++,((0x10) | current) == current);
		getRepresentation().set(indiceBitActual++,((0x20) | current) == current);
		getRepresentation().set(indiceBitActual++,((0x40) | current) == current);
		getRepresentation().set(indiceBitActual++,((0x80) | current) == current);
	}

	public BitString getRepresentation() {
		return representation;
	}

}
