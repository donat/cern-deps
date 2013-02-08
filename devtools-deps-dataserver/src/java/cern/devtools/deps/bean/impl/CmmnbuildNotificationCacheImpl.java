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
package cern.devtools.deps.bean.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;

import cern.accsoft.commons.util.StringUtils;
import cern.devtools.deps.bean.CmmnbuildNotificationCache;

public class CmmnbuildNotificationCacheImpl implements CmmnbuildNotificationCache {

	File file;

	public CmmnbuildNotificationCacheImpl() {
		try {
			// try to create or access to the file which will be use as a storage.
			file = new File(CACHE_LOC);
			file.createNewFile();
		} catch (IOException e) {
			// The clas should access to this file.
			throw new RuntimeException(e);
		}
	}

	public void addEntry(String releaserName, String productName, String oldVersion, String newVersion,
			String releaseType) {
		try {
			String entry = StringUtils.arrayToCommaDelimitedString(new String[] { releaserName, productName,
					oldVersion, newVersion, releaseType });
			PrintWriter appender = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			appender.println(entry);
			appender.close();
		} catch (IOException e) {
			throw new RuntimeException("This should not happen as the constructor check the acces for the file", e);
		}
	}

	public void addEntry(Hashtable<String, String> entry) {
		addEntry(entry.get(KEY_RELEASER_NAME), entry.get(KEY_PRODUCT_NAME), entry.get(KEY_OLD_VERSION),
				entry.get(KEY_NEW_VERSION), entry.get(KEY_RELEASE_TYPE));
	}

	public Collection<Hashtable<String, String>> popAllEntries() {
		try {
			// Read all entries from the file and convert them to String[] format.
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			Collection<Hashtable<String, String>> entries = new LinkedList<Hashtable<String, String>>();
			while ((line = reader.readLine()) != null) {
				String[] lineArray = StringUtils.commaDelimitedListToStringArray(line);
				Hashtable<String, String> entry = createProperty(lineArray);
				entries.add(entry);
			}
			reader.close();

			// Delete the file content.
			PrintWriter appender = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
			appender.print("");
			appender.close();
			file.delete();
			file = new File(CACHE_LOC);
			file.createNewFile();

			// Return the array.
			return entries;

		} catch (IOException e) {
			throw new RuntimeException("This should not happen as the constructor check the acces for the file", e);
		}
	}

	private Hashtable<String, String> createProperty(String[] data) {
		if (data.length <= 4) {
			throw new RuntimeException("Argument is too small");
		}

		Hashtable<String, String> result = new Hashtable<String, String>();
		result.put(KEY_RELEASER_NAME, data[0]);
		result.put(KEY_PRODUCT_NAME, data[1]);
		result.put(KEY_OLD_VERSION, data[2]);
		result.put(KEY_NEW_VERSION, data[3]);
		result.put(KEY_RELEASE_TYPE, data[4]);

		return result;
	}

}
