/*
 * File TreeItemType.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 20 Mar 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.tree;

import cern.devtools.deps.domain.Product;

/**
 * <p>
 * The available node types for building custom tree of {@link TreeItem} instances. The node label and image are
 * depending on the type of this class.
 * </p>
 * <p>
 * More information about the working mechanism is described in the {@link TreeItem}.
 * </p>
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