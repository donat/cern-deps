package cern.devtools.deps.bean.impl;

import static cern.devtools.deps.bean.CmmnbuildNotificationCache.KEY_NEW_VERSION;
import static cern.devtools.deps.bean.CmmnbuildNotificationCache.KEY_OLD_VERSION;
import static cern.devtools.deps.bean.CmmnbuildNotificationCache.KEY_PRODUCT_NAME;
import static cern.devtools.deps.bean.CmmnbuildNotificationCache.KEY_RELEASER_NAME;
import static cern.devtools.deps.bean.CmmnbuildNotificationCache.KEY_RELEASE_TYPE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

import cern.devtools.deps.bean.CmmnbuildNotificationCache;
import cern.devtools.deps.bean.impl.CmmnbuildNotificationCacheImpl;

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
