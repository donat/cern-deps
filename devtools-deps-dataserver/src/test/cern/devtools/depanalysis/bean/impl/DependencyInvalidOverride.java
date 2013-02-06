/***********************************************************************************************************************
 * © Copyright 2013 CERN. This software is distributed under the terms of the GNU General Public Licence version 3 (GPL
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

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;

import cern.devtools.depanalysis.domain.Dependency;
import cern.devtools.depanalysis.domain.Modifiers;
import cern.devtools.depanalysis.domain.creation.DomainFactory;

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
