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
package cern.devtools.deps.bean.impl.neo4j;

import org.neo4j.graphdb.RelationshipType;

/**
 * Relationships between nodes in the graph databases.
 * @author Donat Csikos
 *
 */
public enum Relationships implements RelationshipType {
	/**
	 * Reference node -(PROOT)-> Product root -(PRODUCTS)-> p1, p2, ... , pn.
	 *                                
	 */
	PROOT,
	
	/**
	 * Product root-(PRODUCTS)-> p1, p2, ..., pn.
	 */
	PRODUCTS,
	
	/**
	 * Product1 -(VERSION)-> 1.0.0, 2.1.0. <p>
	 * ApiClass1-(VERSION)-> 1.0.0
	 */
	VERSIONS,
	
	/**
	 * Product-(CLASSES)->MyClass,AnotherClass
	 */
	CLASSES,
	
	
	METHODS,
	
	FIELDS,
	
	CLASS_USAGE_DEPENDENCY,
	
	INHERITANCE_DEPENDENCY,
	
	FIELD_REFERENCE_DEPENDENCY,
	
	METHOD_CALL_DEPENDENCY,
	
	OVERRIDE_DEPENDENCY,
	
}
