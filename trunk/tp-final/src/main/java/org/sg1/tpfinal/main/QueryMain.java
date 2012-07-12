package org.sg1.tpfinal.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sg1.tpfinal.jira.Jira;
import org.sg1.tpfinal.model.Results;
import org.sg1.tpfinal.model.Tracker;

import com.atlassian.jira.rest.client.AuthenticationHandler;
import com.atlassian.jira.rest.client.auth.AnonymousAuthenticationHandler;
import com.thoughtworks.xstream.XStream;

public class QueryMain {

	private static final String JIRA_SERVER_URI = "http://www.mulesoft.org/jira";

	private static final String PROJECT_ID = "MULE";

	private static final String FROM_DATE_STRING = "01/01/2011";
	private static final String TO_DATE_STRING = "11/07/2012";

	public static final String FILE_PATH = "issues-20110101a20120711.xml";

	private static Date FROM_DATE;
	private static Date TO_DATE;

	public static void main(final String... args) throws URISyntaxException,
			FileNotFoundException {
		initializeDates();
		runTracker();
	}

	protected static void runTracker() throws URISyntaxException,
			FileNotFoundException {
		final Tracker tracker = createTracker();
		persistTracker(tracker);
		printResults(tracker);
	}

	protected static Tracker createTracker() throws URISyntaxException {
		final Jira jira = new Jira();
		jira.connect(createAuthenticationHandler(), new URI(JIRA_SERVER_URI));

		final Tracker tracker = jira.createTracker(PROJECT_ID, FROM_DATE,
				TO_DATE);
		return tracker;
	}

	protected static void printResults(final Tracker tracker) {
		new Results(tracker).printResults();
	}

	private static void persistTracker(final Tracker tracker)
			throws FileNotFoundException {
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
