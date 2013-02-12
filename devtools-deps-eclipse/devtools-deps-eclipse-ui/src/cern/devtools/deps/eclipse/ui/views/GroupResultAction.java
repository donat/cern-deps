/*
 * File GroupResultAction.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 14 Mar 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import cern.devtools.deps.eclipse.ui.Images;

/**
 * Action provided by the view to change the result's grouping.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 * 
 */
public class GroupResultAction extends Action {

	/**
	 * Default grouping to use.
	 */
	private GroupingOptions grouping = DependencyView.DEFAULT_GROUPING;

	/**
	 * Reference to the view to manipulate the grouping.
	 */
	private final DependencyView view;

	public GroupResultAction(DependencyView view) {
		super("Change grouping");
		this.view = view;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return Images.ICON_GROUPING.descriptor();
	}

	@Override
	public void run() {
		// Calculate the next grouping strategy and apply it on the view.
		grouping = grouping.next();
		view.changeGrouping(grouping);
	}
}
