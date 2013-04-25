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
package cern.devtools.deps.eclipse.ui.tree;

import cern.devtools.deps.domain.Product;
import cern.devtools.deps.eclipse.ui.Activator;
import cern.devtools.deps.eclipse.ui.LoggingUtil;

/**
 * TreeItem represents a specific version of a product. Must have a {@link Product} instance in the parent node. The
 * {@link #getValue()} should return the version string.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
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
				releaser = Activator.getDefault().getDependencyRMIService().findReleaser(product, value);
			} catch (Exception e) {
				LoggingUtil.logWarn(e.getMessage());
				releaser = null;
				return "";
			}
		}
		return releaser;
	}
}
