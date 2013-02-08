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
package cern.devtools.deps.bean;

import java.util.Collection;
import java.util.Hashtable;

import cern.devtools.deps.bean.impl.CmmnbuildParamsStore;

public interface CmmnbuildNotificationCache extends CmmnbuildParamsStore {
	public static final String CACHE_LOC = "./cmmnbuild-releases.cache";

	/**
	 * Returns all saved entries in a String[][] array.
	 * 
	 * @return the list of saved entries in a key-value format. The available keys: releaserName, productName,
	 *         oldVersion, newVersion, releaseType (PRO, NEXT, PREV DEV, Qfix).
	 */
	public Collection<Hashtable<String, String>> popAllEntries();

	public void addEntry(String releaserName, String productName, String oldVersion, String newVersion,
			String releaseType);
	
	public void addEntry(Hashtable<String, String> entry);
}
