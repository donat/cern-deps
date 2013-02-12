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
 * Override method relationship between two different product.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 *
 */
public class DependencyFindOverride extends AbstractDependencyDiscoveryTest {

	@Override
	public Source from() {
		String source = ""
				+ "package cern.from;                                                                               \n"
				+ "import cern.to.SubClass;                                                                         \n"
				+ "                                                                                                 \n"
				+ "public class SubSubClass extends SubClass {                                                      \n"
				+ "    public void foo() { System.out.println(\"SSub\"); }                                          \n"
				+ "}";
		return new Source(Arrays.asList("SubSubClass"), Arrays.asList(source), "cern.from");
	}

	@Override
	public Source to() {
		String source = ""
				+ "package cern.to;                                                                                 \n"
				+ "import cern.trans.BaseClass;                                                                     \n"
				+ "                                                                                                 \n"
				+ "public class SubClass extends BaseClass {                                                        \n"
				+ "    public void foo() { System.out.println(\"Sub\"); }                                           \n"
				+ "}";
		return new Source(Arrays.asList("SubClass"), Arrays.asList(source), "cern.to");
	}

	@Override
	public Source trans() {
		String source = ""
				+ "package cern.trans;                                                                              \n"
				+ "                                                                                                 \n"
				+ "public class BaseClass {                                                                         \n"
				+ "    public void foo() { System.out.println(\"Base\"); }                                          \n"
				+ "}";
		return new Source(Arrays.asList("BaseClass"), Arrays.asList(source), "cern.trans");
	}
	@Override
	public void test() throws Exception {
		Collection<Dependency> result = db.findMethodDependencies(DomainFactory.creator().createMethod("cern.trans.BaseClass#foo():void", EnumSet.noneOf(Modifiers.class)));
		assertEquals(1, result.size());
		Iterator<Dependency> it = result.iterator();
		Dependency rh = it.next();
		assertEquals(DependencyType.METHOD_OVERRIDE, rh.getType());
		assertEquals("cern.to.SubClass#foo():void", ((Method)rh.getFrom()).getSignature());
		Collection<Dependency> trans = ((TransitiveDependency)rh).getTransitiveFrom();
		assertEquals(1, trans.size());
		Iterator<Dependency> it2 = trans.iterator();
		Dependency rht = it2.next();
		assertEquals(DependencyType.METHOD_OVERRIDE, rh.getType());
		assertEquals("cern.from.SubSubClass#foo():void", ((Method)rht.getFrom()).getSignature());
		System.out.println(rht.getFrom().getClass());
		assertFalse(rht instanceof TransitiveDependency);
	}

}
