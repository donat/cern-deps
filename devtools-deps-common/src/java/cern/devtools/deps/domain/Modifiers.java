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
package cern.devtools.deps.domain;

import cern.devtools.deps.domain.creation.DomainObjectCreator;

/**
 * The available modifiers for the {@link CodeElement} instances. Used in the {@link DomainObjectCreator} for
 * instantiating different {@link CodeElement} entities.
 *
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public enum Modifiers {

	PRIVATE(1), ANONYMOUS(2), STATIC(4);

	private final int value;

	Modifiers(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	/**
	 * Returns if an integer holding a bitpattern contains the specified modifier.
	 *
	 * @param m the modifier what we are looking for.
	 * @param bitPattern the bitpattern
	 * @return
	 */
	public static boolean is(Modifiers m, int bitPattern) {
		return (bitPattern & m.getValue()) != 0;
	}
}
