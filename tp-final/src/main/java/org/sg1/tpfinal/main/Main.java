package org.sg1.tpfinal.main;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sg1.tpfinal.jira.Jira;

import com.atlassian.jira.rest.client.AuthenticationHandler;
import com.atlassian.jira.rest.client.auth.AnonymousAuthenticationHandler;

public class Main {

	private static final String JIRA_SERVER_URI = "http://www.mulesoft.org/jira";

	private static final String PROJECT_ID = "MULE";

	private static final String FROM_DATE_STRING = "01/01/2011";
	private static final String TO_DATE_STRING = "04/07/2012";

	private static Date FROM_DATE;
	private static Date TO_DATE;

	public static void main(final String... args) throws URISyntaxException {
		initializeDates();

		final Jira jira = new Jira();
		jira.connect(createAuthenticationHandler(), new URI(JIRA_SERVER_URI));

		jira.createTracker(PROJECT_ID, FROM_DATE, TO_DATE);
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
