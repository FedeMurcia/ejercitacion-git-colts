package org.sg1.tpfinal.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Sg1Issue implements Serializable {
	private static final long serialVersionUID = 1L;

	private final List<Transition> transitions = new LinkedList<Transition>();

	private final String asignee;
	private final String reporter;

	private final String key;

	public Sg1Issue(final String key, final String asignee, final String reporter) {
		this.key = key;
		this.asignee = asignee;
		this.reporter = reporter;
	}

	public void addTransition(final Transition transition) {
		transitions.add(transition);
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public String getAsignee() {
		return asignee;
	}

	public String getReporter() {
		return reporter;
	}

	public String getKey() {
		return key;
	}

}
