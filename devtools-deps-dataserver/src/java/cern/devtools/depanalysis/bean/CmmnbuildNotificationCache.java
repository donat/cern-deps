package cern.devtools.depanalysis.bean;

import java.util.Collection;
import java.util.Hashtable;

import cern.devtools.depanalysis.bean.impl.CmmnbuildParamsStore;

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
