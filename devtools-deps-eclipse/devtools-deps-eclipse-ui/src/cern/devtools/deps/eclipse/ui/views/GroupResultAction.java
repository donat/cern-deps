/***********************************************************************************************************************
 * © Copyright 2013 CERN. This software is distributed under the terms of the GNU General Public Licence version 3 (GPL
 * Version 3), copied verbatim in the file "COPYING". In applying this licence, CERN does not waive the privileges and
 * immunities granted to it by virtue of its status as an Intergovernmental Organization or submit itself to any
 * jurisdiction.
 * 
 * If you modify this Program, or any covered work, by linking or combining it with the Eclipse Integrated Development
 * Environment Plugin libraries (or a modified version of these libraries), containing parts covered by the terms of EPL
 * (Eclipse Public licence), the licensors of this Program grant you additional permission to convey the resulting work.
 * Corresponding Source for a non-source form of such a combination shall include the source code for the parts of
 * Eclipse Integrated Development Environment Plugin libraries used as well as that of the covered work.
 **********************************************************************************************************************/
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
