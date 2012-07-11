package org.sg1.tpfinal.model;

public class Transition {

	private final State from;
	private final State to;

	public Transition(final State from, final State to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (from == null ? 0 : from.hashCode());
		result = prime * result + (to == null ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Transition other = (Transition) obj;
		if (from != other.from)
			return false;
		if (to != other.to)
			return false;
		return true;
	}

	public State getFromState() {
		return from;
	}

}
