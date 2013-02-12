package cern.devtools.deps.bean.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.DependencyType;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.TransitiveDependency;
import cern.devtools.deps.domain.creation.DomainFactory;

/**
 * Method call dependency starting from an anonymous class.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 * 
 */
public class DependencyMethodCallFromAnonClass extends AbstractDependencyDiscoveryTest {

	@Override
	public Source from() {
		String source = ""
				+ "package cern.from;                                                                               \n"
				+ "import cern.to.To;                                                                               \n"
				+ "                                                                                                 \n"
				+ "public class From {                                                                              \n"
				+ "    public void from() {                                                                         \n"
				+ "        new Thread() {                                                                           \n"
				+ "            public void run() { To to = new To(); to.to(); }                                     \n"
				+ "        };                                                                                       \n"
				+ "    }                                                                                            \n"
				+ "}";
		return new Source(Arrays.asList("From"), Arrays.asList(source), "cern.from");
	}

	@Override
	public Source to() {
		String source = ""
				+ "package cern.to;                                                                                 \n"
				+ "                                                                                                 \n"
				+ "public class To {                                                                                \n"
				+ "    public void to() { System.out.println(\"to\"); }                                             \n"
				+ "}";
		return new Source(Arrays.asList("To"), Arrays.asList(source), "cern.to");
	}

	@Override
	public Source trans() {
		return null;
	}

	@Override
	public void test() throws Exception {
		Collection<Dependency> result = db.findMethodDependencies(DomainFactory.creator()
				.createMethod("cern.to.To#to():void", EnumSet.noneOf(Modifiers.class)));
		assertEquals(1, result.size());
		Iterator<Dependency> it = result.iterator();
		Dependency rh = it.next();
		assertEquals(DependencyType.METHOD_CALL, rh.getType());
		assertEquals("cern.from.From$1#run():void", ((Method) rh.getFrom()).getSignature());
		assertFalse(rh instanceof TransitiveDependency);
	}
}
