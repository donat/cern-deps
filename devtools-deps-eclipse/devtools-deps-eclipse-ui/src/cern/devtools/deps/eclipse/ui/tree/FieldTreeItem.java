/*
 * File FieldTreeItem.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 31 May 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.tree;

import cern.devtools.deps.domain.Field;

/**
 * TreeItem represents an {@link Field}.
 * @author Donat Csikos <dcsikos@cern.ch>
 *
 */
public class FieldTreeItem extends GenericTreeItem<Field> {

	public FieldTreeItem(TreeItem parent, Field value) {
		super(TreeItemType.FIELD, parent, value);
	}
}
