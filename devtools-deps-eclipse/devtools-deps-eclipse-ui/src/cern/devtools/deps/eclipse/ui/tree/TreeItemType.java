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
package cern.devtools.deps.eclipse.ui.tree;

import cern.devtools.deps.domain.Product;

/**
 * The available node types for building custom tree of {@link TreeItem} instances. The node label and image are
 * depending on the type of this class.
 * <p>
 * More information about the working mechanism is described in the {@link TreeItem}.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public enum TreeItemType {

	/**
	 * Root of the tree which won't be visible in the viewer.
	 */
	ROOT,

	/**
	 * The node represents a sorting by the type of the dependencies.
	 */
	DEPENDENCY_TYPE,

	/**
	 * Product node type. Underlying data: a {@link Product} instance.
	 * @see TreeItem#getData()
	 */
	PRODUCT,

	/**
	 * Class.
	 */
	CLASS,

	/**
	 * Method.
	 */
	METHOD,

	/**
	 * Field.
	 */
	FIELD,

	/**
	 * Version..
	 */
	VERSION,

	/**
	 * Package. 
	 */
	PACKAGE, 
	/**
	 * This node should be present, when no dependencies are associated for the selected code element.
	 */
	NO_DEPENDENCIES,
	
	

}
