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
package cern.devtools.deps.domain.creation;

import java.util.EnumSet;

import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.creation.impl.EmfObjectCreator;
import cern.devtools.deps.domain.creation.impl.JavaBeanObjectCreator;

/**
 * <p>
 * Class for creating domain objects.
 * </p>
 * 
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 * 
 */
public abstract class DomainFactory {

	// Default implementation.
	private static DomainObjectCreator creator = new JavaBeanObjectCreator();

	/**
	 * @return Reference of the domain creator implementation.
	 */
	public static DomainObjectCreator creator() {
		return creator;
	}

	/**
	 * Sets up new factory for domain object creation.
	 * 
	 * @param newFactory The new implementation.
	 */
	public static void setDomainObjectCreator(DomainObjectCreator newFactory) {
		creator = newFactory;
	}

	// Demo.
	public static void main(String[] args) {
		Method method = DomainFactory.creator().createMethod("foo():void", EnumSet.noneOf(Modifiers.class));
		System.out.println(method.getClass());
		DomainFactory.setDomainObjectCreator(new EmfObjectCreator());
		method = DomainFactory.creator().createMethod("foo():void", EnumSet.noneOf(Modifiers.class));
		System.out.println(method.getClass());
	}
}
