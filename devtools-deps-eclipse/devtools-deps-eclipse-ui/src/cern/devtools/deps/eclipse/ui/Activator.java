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

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import cern.devtools.deps.bean.DependencyService;
import cern.devtools.deps.eclipse.prefs.PreferenceStore;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends AbstractUIPlugin {
	/**
	 * Plugin id.
	 */
	public static final String PLUGIN_ID = "cern.devtools.deps.eclipse.ui";

	/**
	 * The shared instance
	 */
	private static Activator plugin;

	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		// Log if somebody use the plugin.
		reportPluginActivation();
	}

	/**
	 * Log the user's name and the mahine's hostname to the server.
	 */
	public void reportPluginActivation() {
		try {
			InetAddress localHost = InetAddress.getLocalHost();
			String host = localHost.getHostName();
			String user = System.getProperty("user.name");
			getDependencyService().reportClientActivation(user, host);
		} catch (Exception e) {
			LoggingUtil.logWarn(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Access point to the dependencyService for any plug-in class.
	 * <p>
	 * Classes in the plugin can access to the service by invoking the {@link
	 * Activator.getDefault().getDependencyService()} method.
	 *
	 * @return The service reference.
	 */
	public DependencyService getDependencyService() throws Exception {
		String rmiConnectionString = PreferenceStore.getRmiConnectionString();
		try {
			Remote r = Naming.lookup(rmiConnectionString);
			return (DependencyService) r;
		} catch (MalformedURLException e) {
			throw new RuntimeException(String.format("The url(%s) should be well-formed.", rmiConnectionString));
		}
	}
}
