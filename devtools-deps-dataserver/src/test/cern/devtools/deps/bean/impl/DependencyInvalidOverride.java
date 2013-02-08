package cern.devtools.deps.bean.impl;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;

import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.creation.DomainFactory;

/**
 * Checks if the framework ignores the same method signature if there is no inheritance relationship between the
 * container classes.
 * 
 * @author Donat Csikos
 * 
 */
public class DependencyInvalidOverride extends AbstractDependencyDiscoveryTest {

	@Override
	public Source from() {
		String source = ""
				+ "package cern.from;                                                                               \n"
				+ "import cern.to.BaseClass;                                                                        \n"
				+ "                                                                                                 \n"
				+ "public class SubClass {                                                                          \n"
				+ "    public void foo() { System.out.println(\"Sub\"); }                                           \n"
				+ "}";
		return new Source(Arrays.asList("SubClass"), Arrays.asList(source), "cern.from");
	}

	@Override
	public Source to() {

		String source = ""
				+ "package cern.to;                                                                                 \n"
				+ "                                                                                                 \n"
				+ "public class BaseClass {                                                                         \n"
				+ "    public void foo() { System.out.println(\"Base\"); }                                          \n"
				+ "}";
		return new Source(Arrays.asList("BaseClass"), Arrays.asList(source), "cern.to");
	}

	@Override
	public Source trans() {
		return null;
	}

	@Override
	public void test() throws Exception {
		Collection<Dependency> result = db.findMethodDependencies(DomainFactory.creator()
				.createMethod("cern.to.BaseClass#foo():void", EnumSet.of(Modifiers.STATIC)));
		assertEquals(0, result.size());
	}

}
