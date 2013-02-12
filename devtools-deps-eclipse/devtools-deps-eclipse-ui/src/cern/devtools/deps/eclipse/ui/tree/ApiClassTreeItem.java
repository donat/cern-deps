/*
 * File ApiClassTreeItem.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 31 May 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.tree;

import cern.devtools.deps.domain.ApiClass;

/**
 * TreeItem represents an {@link ApiClass}.
 * 
 * @author Donat Csikos
 *
 */
public class ApiClassTreeItem extends GenericTreeItem<ApiClass> {

	private final String representedVersion;

	public ApiClassTreeItem(TreeItem parent, ApiClass value, String representedVersion) {
		super(TreeItemType.CLASS, parent, value);
		this.representedVersion = representedVersion;
	}
	
	public String getRepresentedVersion() {
		return representedVersion;
	}
}
