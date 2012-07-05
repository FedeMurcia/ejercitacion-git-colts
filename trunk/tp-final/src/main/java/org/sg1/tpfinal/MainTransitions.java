package org.sg1.tpfinal;

import java.net.URI;
import java.net.URISyntaxException;

import com.atlassian.jira.rest.client.AuthenticationHandler;
import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.auth.AnonymousAuthenticationHandler;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.Transition;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;

public class MainTransitions {

	// ProgressMonitor todavía es un objeto que no tiene mucho sentido en
	// esta versión de la API
	private static final NullProgressMonitor PROGRESS_MONITOR = new NullProgressMonitor();

	// Creamos factory de cliente REST JIRA
	private static final JerseyJiraRestClientFactory FACTORY = new JerseyJiraRestClientFactory();

	public static void main(final String... args) throws URISyntaxException {

		// Creamos el cliente apuntando a la URI del servidor JIRA y un método
		// de auntenticación
		final URI jiraServerUri = new URI("http://www.mulesoft.org/jira");
		final JiraRestClient restClient = FACTORY.create(jiraServerUri,
				createAuthenticationHandler());

		// Hacemos consultas
		final Issue issue = restClient.getIssueClient().getIssue("STUDIO-1",
				PROGRESS_MONITOR);

		final Iterable<Transition> transitions = restClient.getIssueClient()
				.getTransitions(issue, PROGRESS_MONITOR);

		// Tratar las transitions
		for (final Transition transition : transitions) {
			transition.getFields();
		}
	}

	private static AuthenticationHandler createAuthenticationHandler() {
		return new AnonymousAuthenticationHandler();
		// return new BasicHttpAuthenticationHandler("alejoabdala",
		// "extrapera");
	}
}
