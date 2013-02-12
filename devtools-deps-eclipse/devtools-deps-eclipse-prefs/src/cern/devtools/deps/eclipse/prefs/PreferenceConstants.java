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
package cern.devtools.deps.eclipse.prefs;

import cern.devtools.deps.eclipse.internal.Activator;

/**
 * Constant definitions for plug-in preferences
 * 
 * @author Donat Csikos <dcsikos@cern.ch> <dcsikos@cern.ch>
 */
public class PreferenceConstants {

    /**
     * Server part's host name.
     */
    public static final String P_SERVER_HOSTNAME = Activator.PLUGIN_ID + ".hostname";

    /**
     * Opened RMI port.
     */
    public static final String P_SERVER_PORT = Activator.PLUGIN_ID + ".port";

    /**
     * Name of the RMI service.
     */
    public static final String P_SERVICE_NAME = Activator.PLUGIN_ID + ".servicename";

}
