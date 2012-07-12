package org.sg1.tpfinal.model;

public class Results {

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

		System.out.println("Total issues: " + tracker.getIssuesCount());
		System.out.println("Total transiciones: "
				+ tracker.getAllTranstitionsCount());

		for (final State from : State.values())
			System.out.println("Total transiciones desde " + from + ": "
					+ tracker.getTransitionsFromCount(from));
	}

	private void printTransitionProbability(final Tracker tracker,
			final State from, final State to) {
		try {
			final double transitionProbability = tracker.transitionProbability(
					from, to);

			System.out.println("La probabilidad de ir de " + from + " a " + to
					+ " es de " + transitionProbability);
		} catch (final NonExistentTransition ex) {
			System.out.println("No hay ocurrencias de probabilidades desde "
					+ from + " a " + to);
		}
	}
}
