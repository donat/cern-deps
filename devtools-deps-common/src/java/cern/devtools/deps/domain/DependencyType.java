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

/**
 * Enumeration containing the defined type of dependencies.
 * 
 * @author Donat Csikos
 */
public enum DependencyType {
	/**
	 * Method call dependency.
	 */
	METHOD_CALL(1),

	/**
	 * Method override in the
	 */
	METHOD_OVERRIDE(2),

	/**
	 * Field reference dependency.
	 */
	FIELD_REFERENCE(3),

	/**
	 * Class usage dependency.
	 */
	CLASS_USAGE(4),

	/**
	 * Inheritance dependency.
	 */
	CLASS_INHERITANCE(5),

	/**
	 * Inherited dependency. This dependency occurs when the project has any kind of dependency (from above) contained
	 * inside.
	 */
	PRODUCT_DEPENDENCY(6);

	private final int value;

	DependencyType(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}

	public static DependencyType typeOf(int val) {
		switch (val) {
		case 1:
			return METHOD_CALL;
		case 2:
			return METHOD_OVERRIDE;
		case 3:
			return FIELD_REFERENCE;
		case 4:
			return CLASS_USAGE;
		case 5:
			return CLASS_INHERITANCE;
		case 6:
			return PRODUCT_DEPENDENCY;
		default:
			throw new RuntimeException("This type does not exist");
		}
	}
}
