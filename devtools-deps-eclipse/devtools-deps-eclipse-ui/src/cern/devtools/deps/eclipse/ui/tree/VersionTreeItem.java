/*
 * File VersionTreeItem.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 31 May 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.tree;

import cern.devtools.deps.domain.Product;
import cern.devtools.deps.eclipse.ui.Activator;
import cern.devtools.deps.eclipse.ui.LoggingUtil;

/**
 * TreeItem represents a specific version of a product. Must have a {@link Product} instance in the parent node. The
 * {@link #getValue()} should return the version string.
 * 
 * @author Donat Csikos
 */
public class VersionTreeItem extends GenericTreeItem<String> {

	String releaser = null;

	public VersionTreeItem(TreeItem parent, String value) {
		super(TreeItemType.VERSION, parent, value);
	}

	/**
	 * Lazily loads and returns the releaser of the specific version of a product instance.
	 * 
	 * @return the name of the releaser. E.g.: "dcsikos".
	 */
	public String getReleaser() {
		if (releaser == null) {
			try {
				Product product = ((ProductTreeItem) this.getParent()).getValue();
				releaser = Activator.getDefault().getDependencyService().findReleaser(product, value);
			} catch (Exception e) {
				LoggingUtil.logWarn(e.getMessage());
				releaser = null;
				return "";
			}
		}
		return releaser;
	}
}
