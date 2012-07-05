package org.sg1.tpfinal.jira;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.sg1.tpfinal.model.State;
import org.sg1.tpfinal.model.Tracker;

import com.atlassian.jira.rest.client.AuthenticationHandler;
import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;

public class Jira {

	private static final String REFLEXIVE_STATE_QUERY = "status WAS \"%s\" AND project = \"%s\" ON \"%s\"";
	private static final String FROM_STATE_TO_STATE_QUERY = "status CHANGED FROM \"%s\" TO \"%s\" ON \"%s\" AND project = \"%s\"";

	// ProgressMonitor todavía es un objeto que no tiene mucho sentido en
	// esta versión de la API
	private static final NullProgressMonitor PROGRESS_MONITOR = new NullProgressMonitor();

	// Creamos factory de cliente REST JIRA
	private static final JerseyJiraRestClientFactory FACTORY = new JerseyJiraRestClientFactory();

	private JiraRestClient restClient;

	public void connect(final AuthenticationHandler authenticationHandler,
			final URI jiraServerUri) throws URISyntaxException {
		restClient = FACTORY.create(jiraServerUri, authenticationHandler);
	}

	public Tracker createTracker(final String projectId, final Date fromDate,
			final Date toDate) {

		// Initialize calendar
		final GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fromDate);

		// For each day
		do {
			// Add a day
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			final Date on = calendar.getTime();

			// For each origin state
			for (final State fromState : State.values()) {
				// For each end state
				for (final State toState : State.values()) {

					// XXX Continuar desde aquí. :P
					// DEBO agarrar las issues con sus transiciones y agregarlos a mi tracker (busca y agrega¿?)
					final SearchResult searchResult = restClient
							.getSearchClient().searchJql(
									buildQuery(projectId, on, fromState,
											toState), PROGRESS_MONITOR);

					searchResult.getIssues();
				}
			}

		} while (calendar.getTime().before(toDate));

		throw new UnsupportedOperationException();
	}

	private String buildQuery(final String projectId, final Date date,
			final State from, final State to) {
		return from.equals(to) ? buildReflexiveStateQuery(projectId, date, from)
				: buildFromToStateQuery(projectId, date, from, to);
	}

	private String buildFromToStateQuery(final String projectId,
			final Date date, final State from, final State to) {
		return String.format(FROM_STATE_TO_STATE_QUERY, from.toString(),
				to.toString(), formatDate(date), projectId);
	}

	private String buildReflexiveStateQuery(final String projectId,
			final Date date, final State from) {
		return String.format(REFLEXIVE_STATE_QUERY, from.toString(), projectId,
				date);
	}

	private String formatDate(final Date date) {
		final String dayOfMonth = String.valueOf(Calendar.getInstance().get(
				Calendar.DAY_OF_MONTH));
		final String month = String.valueOf(Calendar.getInstance().get(
				Calendar.MONTH) + 1);
		final String year = String.valueOf(Calendar.getInstance().get(
				Calendar.YEAR));

		return String.format("%s/%s/%s", year, month, dayOfMonth);
	}
}
