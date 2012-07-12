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

	public Sg1Issue(final String key, final String asignee,
			final String reporter) {
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

	/**
	 * Answers the name of the person assigned to solve this issue.
	 *
	 * @return String representative of the name o the person or
	 *         <code>null</code> in case no one is
	 */
	public String getAsignee() {
		return asignee;
	}

	/**
	 * Answers the name of the person assigned to report this issue.
	 *
	 * @return String representative of the name o the person or
	 *         <code>null</code> in case no one is
	 */
	public String getReporter() {
		return reporter;
	}

	/**
	 * Answers the unique ID for this issue. Should never be null. Usually its
	 * just the name of the project concatenated with a number.
	 *
	 * @return ID
	 */
	public String getKey() {
		return key;
	}

}
