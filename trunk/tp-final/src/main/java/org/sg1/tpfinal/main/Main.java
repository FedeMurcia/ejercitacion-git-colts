package org.sg1.tpfinal.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sg1.tpfinal.jira.Jira;
import org.sg1.tpfinal.model.NonExistentTransition;
import org.sg1.tpfinal.model.State;
import org.sg1.tpfinal.model.Tracker;

import com.atlassian.jira.rest.client.AuthenticationHandler;
import com.atlassian.jira.rest.client.auth.AnonymousAuthenticationHandler;
import com.thoughtworks.xstream.XStream;

public class Main {

	private static final String JIRA_SERVER_URI = "http://www.mulesoft.org/jira";

	private static final String PROJECT_ID = "MULE";

	private static final String FROM_DATE_STRING = "04/07/2012";
	private static final String TO_DATE_STRING = "04/07/2012";

	private static final String FILE_PATH = "issues.xml";

	private static Date FROM_DATE;
	private static Date TO_DATE;

	public static void main(final String... args) throws URISyntaxException, FileNotFoundException {
		initializeDates();

		final Jira jira = new Jira();
		jira.connect(createAuthenticationHandler(), new URI(JIRA_SERVER_URI));

		final Tracker tracker = jira.createTracker(PROJECT_ID, FROM_DATE, TO_DATE);

		persistTracker(tracker);

		// For each origin state
		for (final State from : State.values())
			// For each end state
			for (final State to : State.values())
				printTransitionProbability(tracker, from, to);

		System.out.println("Total issues: " + tracker.getIssuesCount());
		System.out.println("Total transiciones: " + tracker.getAllTranstitionsCount());

		for (final State from : State.values())
			System.out.println("Total transiciones desde " + from + ": "
					+ tracker.getTransitionsFromCount(from));
	}

	private static void printTransitionProbability(final Tracker tracker, final State from, final State to) {
		try {
			final double transitionProbability = tracker.transitionProbability(from, to);

			System.out.println("La probabilidad de ir de " + from + " a " + to + " es de "
					+ transitionProbability);
		} catch (final NonExistentTransition ex) {
			System.out.println("No hay ocurrencias de probabilidades desde " + from + " a " + to);
		}
	}

	private static void persistTracker(final Tracker tracker) throws FileNotFoundException {
		new XStream().toXML(tracker, new FileOutputStream(FILE_PATH));
	}

	private static void initializeDates() {
		try {
			final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			FROM_DATE = sdf.parse(FROM_DATE_STRING);
			TO_DATE = sdf.parse(TO_DATE_STRING);

		} catch (final ParseException e) {
			e.printStackTrace();
		}
	}

	private static AuthenticationHandler createAuthenticationHandler() {
		return new AnonymousAuthenticationHandler();
	}

}
