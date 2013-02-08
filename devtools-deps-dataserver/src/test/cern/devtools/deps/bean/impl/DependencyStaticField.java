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
 * Field reference dependency check, if the field is static.
 * 
 * @author Donat Csikos
 * 
 */
public class DependencyStaticField extends AbstractDependencyDiscoveryTest {

	@Override
	public Source from() {
		String source = ""
				+ "package cern.from;                                                                               \n"
				+ "import cern.to.To;                                                                               \n"
				+ "                                                                                                 \n"
				+ "public class From {                                                                              \n"
				+ "    public void from() { To to = new To(); System.out.println(to.FIELD); }                       \n"
				+ "}";
		return new Source(Arrays.asList("From"), Arrays.asList(source), "cern.from");
	}

	@Override
	public Source to() {
		String source = ""
				+ "package cern.to;                                                                                 \n"
				+ "                                                                                                 \n"
				+ "public class To {                                                                                \n"
				+ "    public static int FIELD = 0;                                                                 \n"
				+ "    public void to() { FIELD++; }                                                                \n"
				+ "}";
		return new Source(Arrays.asList("To"), Arrays.asList(source), "cern.to");
	}

	@Override
	public Source trans() {
		return null;
	}

	@Override
	public void test() throws Exception {
		Collection<Dependency> result = db
				.findFieldDependencies(DomainFactory.creator().createField("cern.to.To.FIELD", EnumSet.noneOf(Modifiers.class)));
		assertEquals(1, result.size());
		Iterator<Dependency> it = result.iterator();
		Dependency rh = it.next();
		assertEquals(DependencyType.FIELD_REFERENCE, rh.getType());
		assertEquals("cern.from.From#from():void", ((Method) rh.getFrom()).getSignature());
		assertFalse(rh instanceof TransitiveDependency);

	}

}
