package org.sg1.tpfinal.main;

import java.net.URI;
import java.net.URISyntaxException;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.auth.AnonymousAuthenticationHandler;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;

public class EjemploJqlMule {
	private static final String QUERY = "status CHANGED FROM Open TO Closed AND project = MULE";

	// private static final String QUERY =
	// "status CHANGED FROM Open TO Closed ON \"2012/02/10\" AND project = STUDIO";

	public static void main(final String[] args) throws URISyntaxException {
		// Creamos factory de cliente REST JIRA
		final JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();

		// Creamos el cliente apuntando a la URI del servidor JIRA y un método
		// de auntenticación
		final URI jiraServerUri = new URI("http://www.mulesoft.org/jira");
		final JiraRestClient restClient = factory.create(jiraServerUri,
				new AnonymousAuthenticationHandler());

		// final JiraRestClient restClient = factory
		// .createWithBasicHttpAuthentication(jiraServerUri,
		// "yourusername", "yourpassword");

		// ProgressMonitor todavía es un objeto que no tiene mucho sentido en
		// esta versión de la API
		final NullProgressMonitor pm = new NullProgressMonitor();

		// Hacemos consultas JQL
		final SearchResult issues = restClient.getSearchClient().searchJql(
				QUERY, pm);

		// final Issue issue = restClient.getIssueClient().getIssue("TST-1",
		// pm);

		// Imprimimos la respuesta de la consulta
		System.out.println(issues.getTotal());

		// TODO Efecto de lado
		// now let's vote for it
		// restClient.getIssueClient().vote(issue.getVotesUri(), pm);

		// TODO Efecto de lado
		// now let's watch it
		// restClient.getIssueClient().watch(issue.getWatchers().getSelf(), pm);

		// TODO Efecto de lado
		// now let's start progress on this issue
		// final Iterable<Transition> transitions = restClient.getIssueClient()
		// .getTransitions(issue.getTransitionsUri(), pm);
		// final Transition startProgressTransition = getTransitionByName(
		// transitions, "Start Progress");
		// restClient.getIssueClient().transition(issue.getTransitionsUri(),
		// new TransitionInput(startProgressTransition.getId()), pm);

		// TODO Efecto de lado
		// and now let's resolve it as Incomplete
		// final Transition resolveIssueTransition = getTransitionByName(
		// transitions, "Resolve Issue");
		// final Collection<FieldInput> fieldInputs = Arrays
		// .asList(new FieldInput("resolution", "Incomplete"));
		// final TransitionInput transitionInput = new TransitionInput(
		// resolveIssueTransition.getId(), fieldInputs,
		// Comment.valueOf("My comment"));
		// restClient.getIssueClient().transition(issue.getTransitionsUri(),
		// transitionInput, pm);

	}

	// private static Transition getTransitionByName(
	// final Iterable<Transition> transitions, final String transitionName) {
	// for (final Transition transition : transitions) {
	// if (transition.getName().equals(transitionName)) {
	// return transition;
	// }
	// }
	// return null;
	// }

}
