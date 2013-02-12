/*
 * File PackageTreeItem.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 1 Jun 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.tree;


/**
 * TreeItem represents a java source package. The {@link #getValue()} should return the package name.
 * 
 * @author Donat Csikos
 */
public class PackageTreeItem extends GenericTreeItem<String> {

	protected PackageTreeItem(TreeItem parent, String value) {
		super(TreeItemType.PACKAGE, parent, value);
	}

}
