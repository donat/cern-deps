/*
 * File Images.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 15 Mar 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
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
 * @author Donat Csikos
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
