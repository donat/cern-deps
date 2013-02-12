/*
 * File MethodTreeItem.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 31 May 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.tree;

import cern.devtools.deps.domain.Method;

/**
 * TreeItem represents an {@link Method}.
 * 
 * @author Donat Csikos
 */
public class MethodTreeItem extends GenericTreeItem<Method> {
	private final String representedVersion;

	public MethodTreeItem(TreeItem parent, Method value, String representedVersion) {
		super(TreeItemType.METHOD, parent, value);
		this.representedVersion = representedVersion;
	}
	
	public String getRepresentedVersion() {
		return representedVersion;
	}
}
