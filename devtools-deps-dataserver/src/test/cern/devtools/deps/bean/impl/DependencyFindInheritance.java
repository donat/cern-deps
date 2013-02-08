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
package cern.devtools.deps.bean.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.DependencyType;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.TransitiveDependency;
import cern.devtools.deps.domain.creation.DomainFactory;

/**
 * Inheritance relationship between classes in two different product.
 * 
 * @author Donat Csikos
 */
public class DependencyFindInheritance extends AbstractDependencyDiscoveryTest {

	@Override
	public Source from() {
		String source = ""
				+ "package cern.from;                                                                               \n"
				+ "import cern.to.SubClass;                                                                         \n"
				+ "                                                                                                 \n"
				+ "public class SubSubClass extends SubClass {                                                      \n"
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
				+ "}";
		return new Source(Arrays.asList("SubClass"), Arrays.asList(source), "cern.to");
	}

	@Override
	public Source trans() {
		String source = ""
				+ "package cern.trans;                                                                              \n"
				+ "                                                                                                 \n"
				+ "public class BaseClass {                                                                         \n"
				+ "}";
		return new Source(Arrays.asList("BaseClass"), Arrays.asList(source), "cern.trans");
	}

	@Override
	public void test() throws Exception {
		Collection<Dependency> result = db.findClassDependencies(DomainFactory.creator().createApiClass(
				"cern.trans.BaseClass", EnumSet.noneOf(Modifiers.class)));
		
		// Remove class usage dependencies.
		Iterator<Dependency> usIt  = result.iterator();
		while (usIt.hasNext()) {
			if (usIt.next().getType() != DependencyType.CLASS_INHERITANCE) {
				usIt.remove();
			}
		}
		
		// Check direct inheritance.
		assertEquals(1, result.size());
		Iterator<Dependency> it = result.iterator();
		Dependency rh = it.next();
		assertEquals(DependencyType.CLASS_INHERITANCE, rh.getType());
		assertEquals("cern.to.SubClass", ((ApiClass) rh.getFrom()).getFqName());
		
		
		// Transitive dependency exists.
		Collection<Dependency> trans = ((TransitiveDependency) rh).getTransitiveFrom();
		assertEquals(1, trans.size());
		Iterator<Dependency> it2 = trans.iterator();
		Dependency rht = it2.next();
		assertEquals(DependencyType.CLASS_INHERITANCE, rh.getType());
		assertEquals("cern.from.SubSubClass", ((ApiClass) rht.getFrom()).getFqName());
		
		// No class inherited from SubSubClass.
		assertFalse(rht instanceof TransitiveDependency);

	}
}
