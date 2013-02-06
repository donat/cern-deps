/***********************************************************************************************************************
 * � Copyright 2013 CERN. This software is distributed under the terms of the GNU General Public Licence version 3 (GPL
 * Version 3), copied verbatim in the file "COPYING". In applying this licence, CERN does not waive the privileges and
 * immunities granted to it by virtue of its status as an Intergovernmental Organization or submit itself to any
 * jurisdiction.
 * 
 * If you modify this Program, or any covered work, by linking or combining it with the Eclipse Integrated Development
 * Environment Plugin libraries (or a modified version of these libraries), containing parts covered by the terms of EPL
 * (Eclipse Public licence), the licensors of this Program grant you additional permission to convey the resulting work.
 * Corresponding Source for a non-source form of such a combination shall include the source code for the parts of
 * Eclipse Integrated Development Environment Plugin libraries used as well as that of the covered work.
 **********************************************************************************************************************/
package cern.devtools.depanalysis.bean.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

import cern.devtools.depanalysis.domain.Dependency;
import cern.devtools.depanalysis.domain.DependencyType;
import cern.devtools.depanalysis.domain.Method;
import cern.devtools.depanalysis.domain.Modifiers;
import cern.devtools.depanalysis.domain.TransitiveDependency;
import cern.devtools.depanalysis.domain.creation.DomainFactory;

/**
 * Method call dependency starting from an internal class.
 * 
 * @author Donat Csikos
 * 
 */
public class DependencyMethodCallFromInternalClass extends AbstractDependencyDiscoveryTest {

	@Override
	public Source from() {
		String source = ""
				+ "package cern.from;                                                                               \n"
				+ "import cern.to.To;                                                                               \n"
				+ "                                                                                                 \n"
				+ "public class From {                                                                              \n"
				+ "    public class Inner {                                                                         \n"
				+ "        public void from() { To to = new To(); to.to(); }                                        \n"
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
		assertEquals("cern.from.From$Inner#from():void", ((Method) rh.getFrom()).getSignature());
		assertFalse(rh instanceof TransitiveDependency);
	}
}
