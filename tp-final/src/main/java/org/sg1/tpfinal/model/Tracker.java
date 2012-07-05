package org.sg1.tpfinal.model;

import java.util.Collection;
import java.util.HashSet;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

public class Tracker {

	private final Collection<Issue> issues = new HashSet<Issue>();

	public void addIssue(final Issue issue) {
		issues.add(issue);
	}

	public double transitionProbability(final State from, final State to) {
		return getCountTransitionsFromTo(from, to) / getCountTransitions();
	}

	/* ***************************************** */
	/* ********* Transitions ******************* */
	/* ***************************************** */

	private int getCountTransitions() {
		return getAllTransitions().size();
	}

	private int getCountTransitionsFromTo(final State from, final State to) {
		return getAllTransitionsFromTo(from, to).size();
	}

	private Collection<Transition> getAllTransitionsFromTo( //
			final State from, //
			final State to) {

		return Collections2.filter(getAllTransitions(),
				Predicates.equalTo(new Transition(from, to)));
	}

	private Collection<Transition> getAllTransitions() {
		final Collection<Transition> transitions = new HashSet<Transition>();

		for (final Issue issue : issues)
			transitions.addAll(issue.getTransitions());

		return transitions;
	}

}
