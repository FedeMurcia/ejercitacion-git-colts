package org.sg1.tpfinal.jira;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.sg1.tpfinal.model.Sg1Issue;
import org.sg1.tpfinal.model.State;
import org.sg1.tpfinal.model.Tracker;
import org.sg1.tpfinal.model.Transition;

import com.atlassian.jira.rest.client.AuthenticationHandler;
import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.BasicUser;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;

public class Jira {

	private static final String NULL_REPORTER_USER = "REPORTER NO ASIGNADO";
	private static final String NULL_ASSIGNEE_USER = "EJECUTOR NO ASIGNADO";

	private static final String REFLEXIVE_STATE_QUERY = "status WAS \"%s\" ON \"%s\" AND project = \"%s\" AND NOT (status CHANGED ON \"%s\")";
	private static final String FROM_STATE_TO_STATE_QUERY = "status CHANGED FROM \"%s\" TO \"%s\" ON \"%s\" AND project = \"%s\"";

	// ProgressMonitor todav�a es un objeto que no tiene mucho sentido en
	// esta versi�n de la API
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

		final Tracker tracker = new Tracker();

		// Initialize calendar
		final GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fromDate);

		// For each day
		do {
			final Date on = calendar.getTime();
			System.out.println("Inspeccionado d�a " + on);

			// For each origin state
			for (final State fromState : State.values()) {

				// For each end state
				for (final State toState : State.values()) {
					System.out.println("Estado " + fromState + " a " + toState);

					final SearchResult searchResult = restClient
							.getSearchClient().searchJql(
									buildQuery(projectId, on, fromState,
											toState), PROGRESS_MONITOR);

					// For each issue
					for (final BasicIssue basicIssue : searchResult.getIssues()) {

						final String issueKey = basicIssue.getKey();

						if (!tracker.hasIssue(issueKey)) {
							final Issue issue = restClient.getIssueClient()
									.getIssue(issueKey, PROGRESS_MONITOR);

							final String asignee = getAssignee(issue);
							final String reporter = getReporter(issue);

							final Sg1Issue newIssue = new Sg1Issue(issueKey,
									asignee, reporter);

							tracker.addIssue(newIssue);
						}

						final Sg1Issue issue = tracker.findIssue(issueKey);

						issue.addTransition(new Transition(fromState, toState));

					} // for each issue

				} // for each end state

			} // for each origin state

			// Increment day
			calendar.add(Calendar.DAY_OF_YEAR, 1);

		} while (calendar.getTime().before(toDate)); // for each day

		return tracker;
	}

	private String getReporter(final Issue issue) {
		final BasicUser reporterUser = issue.getReporter();
		final String reporter = reporterUser != null ? reporterUser
				.getDisplayName() : NULL_REPORTER_USER;
		return reporter;
	}

	private String getAssignee(final Issue issue) {
		final BasicUser assigneeUser = issue.getAssignee();
		final String asignee = assigneeUser != null ? assigneeUser
				.getDisplayName() : NULL_ASSIGNEE_USER;
		return asignee;
	}

	private String buildQuery(final String projectId, final Date date,
			final State from, final State to) {

		return from.equals(to) ? buildReflexiveStateQuery(projectId, date, from)
				: buildFromToStateQuery(projectId, date, from, to);
	}

	private String buildFromToStateQuery(final String projectId,
			final Date date, final State from, final State to) {

		return String.format(FROM_STATE_TO_STATE_QUERY, //
				from.toString(), // origin state
				to.toString(), // end state
				formatDate(date), // date
				projectId); // project
	}

	/**
	 * Query para el NO cambio de estado, o sea que se mantiene en el mismo
	 * estado.
	 *
	 * @param projectId
	 * @param date
	 * @param state
	 * @return
	 */
	private String buildReflexiveStateQuery(final String projectId,
			final Date date, final State state) {

		return String.format(REFLEXIVE_STATE_QUERY, //
				state.toString(), // state
				formatDate(date), // date
				projectId, // project
				formatDate(date)); // date (again)
	}

	private String formatDate(final Date date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		final String dayOfMonth = formatDayOfMonth(calendar);
		final String month = formatMonth(calendar);
		final String year = String.valueOf(calendar.get(Calendar.YEAR));

		return String.format("%s/%s/%s", year, month, dayOfMonth);
	}

	private String formatMonth(final Calendar calendar) {
		final String monthStr = String
				.valueOf(calendar.get(Calendar.MONTH) + 1);

		// assert month string is 2 characters long
		return monthStr.length() == 1 ? "0" + monthStr : monthStr;
	}

	private String formatDayOfMonth(final Calendar calendar) {
		final String dayOfMonthStr = String.valueOf(calendar
				.get(Calendar.DAY_OF_MONTH));

		// assert day of month string is 2 characters long
		return dayOfMonthStr.length() == 1 ? "0" + dayOfMonthStr
				: dayOfMonthStr;
	}
}
