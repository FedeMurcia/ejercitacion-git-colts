package utn.frba.ia.ga.tpG14.util;

public class BinaryStringBuilder {

	char caracterActual = 0x00;
	String product = "";
	int offsetEnCaracterActual = 0;

	public void setNextBit(boolean bit) {
		char mask = 0x00;
		switch (offsetEnCaracterActual) {
		case 0:
			mask = 0x01;
			break;
		case 1:
			mask = 0x02;
			break;
		case 2:
			mask = 0x04;
			break;
		case 3:
			mask = 0x08;
			break;
		case 4:
			mask = 0x10;
			break;
		case 5:
			mask = 0x20;
			break;
		case 6:
			mask = 0x40;
			break;
		case 7:
			mask = 0x80;
		}

		if (bit)
			caracterActual |= mask;

		if (offsetEnCaracterActual == 7){
			offsetEnCaracterActual = 0;
			product += caracterActual;
			caracterActual = 0x00;
		}
		else
			offsetEnCaracterActual++;
	}

	public String build() {
		return product;
	}

}
