/*
 * File NoDependenciesTreeItem.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 31 May 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.tree;

import cern.devtools.deps.domain.CodeElement;

/**
 * TreeItem represents a node which is displayed when there is no dependency for the queried code element. The
 * constructor stores the queried object.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public class NoDependenciesTreeItem extends GenericTreeItem<CodeElement> {

	public NoDependenciesTreeItem(TreeItem parent, CodeElement value) {
		super(TreeItemType.NO_DEPENDENCIES, parent, value);
	}

}
