/*
 * File RootTreeItem.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 31 May 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.tree;

import cern.devtools.deps.domain.CodeElement;

/**
 * Root tree item.
 * @author Donat Csikos <dcsikos@cern.ch>
 *
 */
public class RootTreeItem extends GenericTreeItem<CodeElement> {

	public RootTreeItem(TreeItem parent, CodeElement value) {
		super(TreeItemType.ROOT, parent, value);
	}

}
