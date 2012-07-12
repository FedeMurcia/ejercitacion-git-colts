package org.sg1.tpfinal.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

public class Tracker implements Serializable {
	private static final long serialVersionUID = 1L;

	private final Collection<Sg1Issue> issues = new HashSet<Sg1Issue>();

	public void addIssue(final Sg1Issue issue) {
		issues.add(issue);
	}

	public double transitionProbability(final State from, final State to) {
		final int countTransitionsFrom = getTransitionsFromCount(from);

		if (countTransitionsFrom == 0)
			throw new NonExistentTransition(this, from, to);

		return (double) getCountTransitionsFromTo(from, to) / (double) countTransitionsFrom;
	}

	/* ***************************************** */
	/* ********* Issues ************************ */
	/* ***************************************** */

	public boolean hasIssue(final String issueKey) {
		try {
			findIssue(issueKey);

			// Si no rompe, es porque encontro el issue, entonces tenemos el
			// issue
			return true;
		} catch (final RuntimeException ex) {
			// Si rompe es porque no tengo el issue
			return false;
		}
	}

	public Sg1Issue findIssue(final String issueKey) {
		for (final Sg1Issue issue : issues)
			if (issue.getKey().equalsIgnoreCase(issueKey))
				return issue;

		throw new RuntimeException("no existe el issue con key: " + issueKey);
	}

	/* ***************************************** */
	/* ********* Transitions ******************* */
	/* ***************************************** */

	public int getTransitionsFromCount(final State from) {
		return getAllTransitionsFrom(from).size();
	}

	private int getCountTransitionsFromTo(final State from, final State to) {
		return getAllTransitionsFromTo(from, to).size();
	}

	private Collection<Transition> getAllTransitionsFrom(final State from) {
		return Collections2.filter(getAllTransitions(), transitionFrom(from));
	}

	private Predicate<Transition> transitionFrom(final State from) {
		return new Predicate<Transition>() {
			@Override
			public boolean apply(final Transition input) {
				return from.equals(input.getFromState());
			}
		};
	}

	private Collection<Transition> getAllTransitionsFromTo( //
			final State from, //
			final State to) {

		return Collections2.filter(getAllTransitions(), Predicates.equalTo(new Transition(from, to)));
	}

	private Collection<Transition> getAllTransitions() {
		final Collection<Transition> transitions = new HashSet<Transition>();

		for (final Sg1Issue issue : issues)
			transitions.addAll(issue.getTransitions());

		return transitions;
	}

	public int getAllTranstitionsCount() {
		return getAllTransitions().size();
	}

	public int getIssuesCount() {
		return issues.size();
	}

}
