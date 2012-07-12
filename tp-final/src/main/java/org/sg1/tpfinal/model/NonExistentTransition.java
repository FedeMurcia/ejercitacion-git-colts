package org.sg1.tpfinal.model;

public class NonExistentTransition extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final Tracker tracker;
	private final State from;
	private final State to;

	public NonExistentTransition(final Tracker tracker, final State from, final State to) {
		this.tracker = tracker;
		this.from = from;
		this.to = to;
	}

	public Tracker getTracker() {
		return tracker;
	}

	public State getFrom() {
		return from;
	}

	public State getTo() {
		return to;
	}
}
