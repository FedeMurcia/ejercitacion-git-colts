package utn.frba.ia.ga.tpG14;

import static org.junit.Assert.*;

import java.util.*;

import org.hamcrest.*;
import org.jaga.definitions.*;
import org.jaga.util.*;
import org.junit.*;

public class StringIndividualFactoryTest {

	private StringIndividualFactory factory;
	private GAParameterSet defaultParams = new DefaultParameterSet();

	@Before
	public void setUp() {
		factory = new StringIndividualFactory(12);
	}

	@Test
	public void factoryTeCreaElIndividuoPorDefecto() {
		GAParameterSet params = new DefaultParameterSet();
		StringIndividual individuo = (StringIndividual) factory
				.createDefaultIndividual(params);

		Assert.assertEquals("aaaaaaaaaaaa", individuo.getString());
		System.out.println(individuo.getBitStringRepresentation());
	}

	@Test
	public void factoryTeCreaRandoms() {
		GAParameterSet params = new DefaultParameterSet();

		for (int i = 0; i < 5; i++) {
			StringIndividual individuo = (StringIndividual) factory
					.createRandomIndividual(params);
			System.out.println(individuo.getString());
		}
	}

	@Test
	public void factoryTeCreaRandomsDeSoloMinusculas() {
		GAParameterSet params = new DefaultParameterSet();
		factory.setMinCodePoint(97);
		factory.setMaxCodePoint(123);

		for (int i = 0; i < 5; i++) {
			StringIndividual individuo = (StringIndividual) factory
					.createRandomIndividual(params);
			System.out.println(individuo.getString());
			Assert.assertThat(individuo.getString(), soloContieneMinusculas());
		}
	}

	@Test
	public void inyectaPatronStringIndivFactory() {
		Individual randomIndividual = new InyectaPatronStringIndividualFactory(10, '0', 'z').addPatron("patron", 1d)
				.createRandomIndividual(defaultParams);
		String string = ((StringIndividual) randomIndividual).getString();
		System.out.println(string);
		assertTrue(string.indexOf("patron") != -1);
	}

	private Matcher<String> soloContieneMinusculas() {
		return new BaseMatcher<String>() {

			@Override
			public boolean matches(Object arg0) {
				String string = (String) arg0;

				boolean todasMinusculas = true;
				int length = string.length();
				for (int i = 0; i < length; i++)
					todasMinusculas &= Character.isLowerCase(string.charAt(i));

				return todasMinusculas;
			}

			@Override
			public void describeTo(Description arg0) {
				arg0.appendText("chequea minúsculas");
			}

		};
	}
}
