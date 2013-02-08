package cern.devtools.deps.bean.impl;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;

import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.creation.DomainFactory;

/**
 * Checks if the framework stores the internal references in the database. 
 * 
 * @author Donat Csikos
 *
 */
public class DependencyInternalUsage extends AbstractDependencyDiscoveryTest {

	@Override
	public Source from() {
		String s1 = ""
				+ "package cern.from;                                                                               \n"
				+ "                                                                                                 \n"
				+ "public class From {                                                                              \n"
				+ "    public void from() { To to = new To(); to.to(); }                                            \n"
				+ "}";
		String s2 = ""
				+ "package cern.from;                                                                               \n"
				+ "                                                                                                 \n"
				+ "public class To {                                                                                \n"
				+ "    public void to() { System.out.println(\"to\"); }                                             \n"
				+ "}";
		return new Source(Arrays.asList("From", "To"), Arrays.asList(s1, s2), "cern.from");
	}

	@Override
	public Source to() {
		String source = ""
				+ "package cern.to;                                                                                 \n"
				+ "                                                                                                 \n"
				+ "public class Foo {                                                                               \n"
				+ "}";
		return new Source(Arrays.asList("Foo"), Arrays.asList(source), "cern.to");
	}

	@Override
	public Source trans() {
		return null;
	}

	@Override
	public void test() throws Exception {
		Collection<Dependency> result = db.findClassDependencies(DomainFactory.creator()
				.createApiClass("cern.from.To", EnumSet.noneOf(Modifiers.class)));
		assertEquals(0, result.size());
	}

}
