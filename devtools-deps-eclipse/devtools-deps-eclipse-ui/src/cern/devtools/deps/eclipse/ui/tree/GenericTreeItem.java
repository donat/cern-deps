/*
 * File GenericTreeItem.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 31 May 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.tree;


/**
 * TreeItem with a single object contained inside. 
 * 
 * @author Donat Csikos
 *
 * @param <T> The type of the held object to return in the {@link #getValue()} method.
 */
public abstract class GenericTreeItem<T> extends TreeItem {

	protected T value;
	
	protected GenericTreeItem(TreeItemType type, TreeItem parent, T value) {
		super(type, parent);
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}

}
