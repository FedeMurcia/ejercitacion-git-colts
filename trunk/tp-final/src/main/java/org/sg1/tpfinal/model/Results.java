package org.sg1.tpfinal.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Results {

	private static final Logger logger = LoggerFactory.getLogger(Results.class);

	private final Tracker tracker;

	public Results(final Tracker tracker) {
		this.tracker = tracker;
	}

	public void printResults() {
		// For each origin state
		for (final State from : State.values())
			// For each end state
			for (final State to : State.values())
				printTransitionProbability(tracker, from, to);

		// Print issues count
		logger.info("Total issues: " + tracker.getIssuesCount());

		// Print transitions count
		logger.info("Total transiciones: " + tracker.getAllTranstitionsCount());

		// For each state
		for (final State from : State.values())
			// Print the number of transitions from the state
			logger.info("Total transiciones desde " + from + ": "
					+ tracker.getTransitionsFromCount(from));
	}

	private void printTransitionProbability(final Tracker tracker,
			final State from, final State to) {
		try {
			final double transitionProbability = tracker
					.getTransitionProbability(from, to);

			logger.info("La probabilidad de ir de {} a {} es de "
					+ transitionProbability, from, to);
		} catch (final NonExistentTransition ex) {
			logger.info("No hay ocurrencias de probabilidades desde {} a {}",
					from, to);
		}
	}
}
