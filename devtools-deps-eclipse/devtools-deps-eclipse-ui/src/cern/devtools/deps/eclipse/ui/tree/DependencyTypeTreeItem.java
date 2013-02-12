/*
 * File DependencyTypeTreeItem.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 31 May 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.tree;

import cern.devtools.deps.domain.DependencyType;

/**
 * TreeItem represents an {@link DependencyType}.
 * @author Donat Csikos <dcsikos@cern.ch>
 *
 */
public class DependencyTypeTreeItem extends GenericTreeItem<DependencyType> {

	public DependencyTypeTreeItem(TreeItem parent, DependencyType value) {
		super(TreeItemType.DEPENDENCY_TYPE, parent, value);
	}
	
}
