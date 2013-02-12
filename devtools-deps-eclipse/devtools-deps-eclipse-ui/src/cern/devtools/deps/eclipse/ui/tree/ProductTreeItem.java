/*
 * File ProductTreeItem.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 31 May 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.tree;

import java.util.Collection;
import java.util.Collections;

import cern.devtools.deps.domain.Product;
import cern.devtools.deps.eclipse.ui.Activator;
import cern.devtools.deps.eclipse.ui.LoggingUtil;

/**
 * TreeItem represents an {@link Product}.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public class ProductTreeItem extends GenericTreeItem<Product> {

	private Collection<String> committers = null;

	public ProductTreeItem(TreeItem parent, Product p) {
		super(TreeItemType.PRODUCT, parent, p);
	}

	/**
	 * Lazily loads and returns the committers of the represented project. E.g. {"lmestre", "vbaggiol", "eroux", "arey", "rgorbono", "vlezhebo"}.
	 * @return
	 */
	public Collection<String> getCommitters() {
		if (committers == null) {
			try {
				committers = Activator.getDefault().getDependencyService().getCommittersName(super.value);
			} catch (Exception e) {
				LoggingUtil.logWarn(e.getMessage());
				committers = null;
				return Collections.emptyList();
			}
		}
		return committers;
	}
}
