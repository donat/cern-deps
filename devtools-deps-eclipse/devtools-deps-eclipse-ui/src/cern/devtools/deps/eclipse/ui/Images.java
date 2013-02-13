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
package cern.devtools.deps.eclipse.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * 
 * <p>
 * Enumeration for storing and retrieving images.
 * </p>
 * 
 * <p>
 * All images are present in the plugin's build path and referenced through a relative path. The enumeration provides
 * the {@link #descriptor()} method, which allows the clients to access the images itself, not just the paths.
 * </p>
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 * 
 */
public enum Images {

	/**
	 * Icon of a class.
	 */
	ICON_CLASS("src/icons/class.gif"),

	/**
	 * Icon of a method.
	 */
	ICON_METHOD("src/icons/method.gif"),

	/**
	 * Icon of a field.
	 */
	ICON_FIELD("src/icons/field.gif"),

	/**
	 * Icon of a jar file.
	 */
	ICON_PRODUCT("src/icons/program.gif"),

	/**
	 * Icon of a version node.
	 */
	ICON_VERSION("src/icons/version.gif"),

	/**
	 * Icon of a dependency type.
	 */
	ICON_DEPENDENCY("src/icons/dependency.gif"),

	/**
	 * Icon of change grouping on the dependency view's toolbar.
	 */
	ICON_GROUPING("src/icons/grouping.gif"), 
	
	/**
	 * Icon for package node in the treeviewer.
	 */
	ICON_PACKAGE("src/icons/package_obj.gif");

	private final String path;

	/**
	 * Constructor.
	 * 
	 * @param path Relative path of the images rooted from the UI plugin folder.
	 */
	Images(String path) {
		this.path = path;
	}

	/**
	 * Creates lightweight image descriptor from the file paths.
	 * 
	 * @see ImageDescriptor
	 * 
	 * @return The descriptor.
	 */
	public ImageDescriptor descriptor() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, path);
	}
}
