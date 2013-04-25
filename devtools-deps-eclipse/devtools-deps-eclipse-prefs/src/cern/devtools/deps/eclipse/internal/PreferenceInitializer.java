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
package cern.devtools.deps.eclipse.internal;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import cern.devtools.deps.eclipse.prefs.PreferenceConstants;

/**
 * Class used to initialise default preference values.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    public void initializeDefaultPreferences() {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        store.setDefault(PreferenceConstants.P_SERVER_HOSTNAME, "cs-ccr-ap1");
        store.setDefault(PreferenceConstants.P_SERVER_PORT, "18080");
        store.setDefault(PreferenceConstants.P_SERVICE_NAME, "dependency_service");
    }
}
