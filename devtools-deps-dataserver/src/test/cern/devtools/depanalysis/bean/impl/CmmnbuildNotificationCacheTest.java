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
package cern.devtools.depanalysis.bean.impl;

import static cern.devtools.depanalysis.bean.CmmnbuildNotificationCache.KEY_NEW_VERSION;
import static cern.devtools.depanalysis.bean.CmmnbuildNotificationCache.KEY_OLD_VERSION;
import static cern.devtools.depanalysis.bean.CmmnbuildNotificationCache.KEY_PRODUCT_NAME;
import static cern.devtools.depanalysis.bean.CmmnbuildNotificationCache.KEY_RELEASER_NAME;
import static cern.devtools.depanalysis.bean.CmmnbuildNotificationCache.KEY_RELEASE_TYPE;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cern.devtools.depanalysis.bean.CmmnbuildNotificationCache;

public class CmmnbuildNotificationCacheTest {

	CmmnbuildNotificationCache cache;
	

	@Before
	public void testBasic() {
		cache = new CmmnbuildNotificationCacheImpl();
	}

	@After
	public void cleanup() {
		cache.popAllEntries();
		new File(CmmnbuildNotificationCache.CACHE_LOC).delete();
	}

	@Test
	public void testFileCreatedInConstrucor() {
		assertTrue(new File(CmmnbuildNotificationCache.CACHE_LOC).exists());
	}

	@Test
	public void addEntryWorks() throws FileNotFoundException, IOException {
		cache.addEntry("releaser", "product", "oldversion", "newversion", "PRO");
		String content = new BufferedReader(new FileReader(new File(CmmnbuildNotificationCache.CACHE_LOC))).readLine();
		assertEquals("releaser,product,oldversion,newversion,PRO", content);
	}

	@Test
	public void popAllEntries() {
		cache.addEntry("r1", "p1", "o1", "n1", "pt1");
		cache.addEntry("r2", "p2", "o2", "n2", "pt2");
		Collection<Hashtable<String, String>> entries = cache.popAllEntries();
		Iterator<Hashtable<String, String>> i = entries.iterator();
		Hashtable<String, String> e1 = i.next();
		Hashtable<String, String> e2 = i.next();
		assertFalse(i.hasNext());
		
		assertEquals("r1", e1.get(KEY_RELEASER_NAME));
		assertEquals("p1", e1.get(KEY_PRODUCT_NAME));
		assertEquals("o1", e1.get(KEY_OLD_VERSION));
		assertEquals("n1", e1.get(KEY_NEW_VERSION));
		assertEquals("pt1", e1.get(KEY_RELEASE_TYPE));
		
		assertEquals("r2", e2.get(KEY_RELEASER_NAME));
		assertEquals("p2", e2.get(KEY_PRODUCT_NAME));
		assertEquals("o2", e2.get(KEY_OLD_VERSION));
		assertEquals("n2", e2.get(KEY_NEW_VERSION));
		assertEquals("pt2", e2.get(KEY_RELEASE_TYPE));
		
		
		assertFalse(cache.popAllEntries().iterator().hasNext());
	}
}
