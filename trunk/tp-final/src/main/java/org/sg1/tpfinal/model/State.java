package org.sg1.tpfinal.model;

public enum State {
	OPEN("OPEN"), // abierta
	SCOPED("SCOPED"), // en análisis
	IN_PROGRESS("IN PROGRESS"), // en curso
	RESOLVED("RESOLVED"), // resuelta
	REOPENED("REOPENED"), // reabierta
	CLOSED("CLOSED"); // cerrada

	private final String displayableName;

	private State(final String displayableName) {
		this.displayableName = displayableName;
	}

	@Override
	public String toString() {
		return displayableName;
	}
}
