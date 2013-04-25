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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

/**
 * Small utility class holding logging functions. Provides unified way of logging exceptions in the client code.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public class LoggingUtil {

	/**
	 * Logs a message and shows it in a warning dialog.
	 * 
	 * @param message The message to display.
	 * @param e The exception to log.
	 */
	public static void warnAndLog(final String message, final Exception e) {
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				warnAndLogThreadUnsafe(message, e);
			}
		});
	}

	private static void warnAndLogThreadUnsafe(String message, Exception e) {
		// Print the trace for debugging.
		e.printStackTrace();

		// Display a warning message
		MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Error",
				message);

		// Store the error message in the eclipse log.
		Activator
				.getDefault()
				.getLog()
				.log(new Status(IStatus.WARNING, Activator.PLUGIN_ID, message + "\nReason is: " + e.getMessage() + "."));
	}

	public static void logWarn(String message) {
		Activator.getDefault().getLog().log(new Status(IStatus.WARNING, Activator.PLUGIN_ID, message));
	}
	
	public static void logWarn(Exception e) {
		Activator.getDefault().getLog().log(new Status(IStatus.WARNING, Activator.PLUGIN_ID, e.getMessage()));
		e.printStackTrace();
	}

	/**
	 * Logs a message and shows it in a warning dialog.
	 * 
	 * @param e The exception to log.
	 */
	public static void warnAndLog(Exception e) {
		warnAndLog(e.getClass().getSimpleName(), e);
	}
}
