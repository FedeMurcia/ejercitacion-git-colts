package org.sg1.tpfinal.model;

import java.util.LinkedList;
import java.util.List;

public class Issue {

	private final List<Transition> transitions = new LinkedList<Transition>();

	private final String asignee;
	private final String reporter;

	public Issue(final String asignee, final String reporter) {
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

}
