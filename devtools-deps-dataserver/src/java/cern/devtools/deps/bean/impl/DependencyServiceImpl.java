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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.neo4j.helpers.Pair;

import cern.devtools.deps.bean.Controller;
import cern.devtools.deps.bean.Controller.DependencyUpdateListener;
import cern.devtools.deps.bean.DatabaseDao;
import cern.devtools.deps.bean.DepBeanException;
import cern.devtools.deps.bean.DependencyService;
import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Product;

/**
 * Simple implementation of the {@link DependencyService} interface.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public class DependencyServiceImpl implements DependencyService {

	/**
	 * Listener which puts a null every time an update is initiated. In this way the {@link DependencyServiceImpl} can
	 * cache the releaselog file's content until the database itself is refreshed.
	 */
	private DependencyUpdateListener listener = new DependencyUpdateListener() {
		public void onUpdate() {
			releaseLog = null;
		}
	};

	/**
	 * Database bean.
	 */
	private final DatabaseDao db;

	/**
	 * Logger instance..
	 */
	private static final Logger LOG = Logger.getLogger(DependencyServiceImpl.class);

	/**
	 * The cache of the commonbuild repository's log.
	 */
	private Map<Pair<String, String>, String> releaseLog;

	private final Controller controller;


	/**
	 * Constructor.
	 * 
	 * @param db DataBase access bean.
	 */
	public DependencyServiceImpl(DatabaseDao db, Controller controller) {
		this.db = db;
		this.controller = controller;
		controller.addDependencyUpdateListener(listener);
	}

	public Collection<Dependency> getIncomingDependencies(CodeElement element) throws DepBeanException {
		// Log the query
		LOG.info("Analyse  dependencies <" + element + ">.");

		// Do the query based on the type of the instance.
		try {
			Collection<Dependency> result;
			if (element instanceof ApiClass) {
				result = db.findClassDependencies((ApiClass) element);
			} else if (element instanceof Method) {
				result = db.findMethodDependencies((Method) element);
			} else if (element instanceof Field) {
				result = db.findFieldDependencies((Field) element);
			} else if (element instanceof Product) {
				result = db.findProductDependencies((Product) element);
			} else {
				throw new IllegalArgumentException("This type of element cannot be analysed: " + element.getClass());
			}

			// Log the incoming dependencies list if the logger is set to collect debug information.
			debugLogDependencies(result);

			return result;
		} catch (Exception e) {
			// On error log it and throw a Service-level exception.
			LOG.warn("getIncomingDependencies() failed. Reason is: " + e.getMessage());
			throw new DepBeanException(e);
		}
	}

	private void debugLogDependencies(Collection<Dependency> result) {
		String log = debugLogDependencies("", result);
		LOG.debug("\n" + log);
	}

	private String debugLogDependencies(String indent, Collection<Dependency> result) {
		StringBuffer sb = new StringBuffer();
		for (Dependency d : result) {
			sb.append(d.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	public Collection<String> getCommittersName(Product p) throws DepBeanException {
		BufferedReader reader = null;
		StringBuilder peoplePath = null;
		try {
			// Obtain commonbuild root.
			String path;
			if (BeanUtils.IS_LINUX) {
				path = ConstantStore.PCROPS_LINUX_LOC;
			} else {
				path = ConstantStore.PCROPS_WINDOWS_LOC;
			}

			// append the location of the "people file"
			peoplePath = new StringBuilder(path);
			peoplePath.append(File.separatorChar);
			peoplePath.append(p.getContainingFolders().replace('/', File.separatorChar)
					.replace('\\', File.separatorChar));
			peoplePath.append(File.separatorChar);
			peoplePath.append("PRO");
			peoplePath.append(File.separatorChar);
			peoplePath.append("people");

			// Check if the people file exists.
			File people = new File(peoplePath.toString());
			if (!people.exists()) {
				String msg = "People file not exist in path " + people.getAbsolutePath();
				LOG.warn(msg);
				throw new DepBeanException(msg);
			}

			// Read the contents of the people file.
			List<String> result = new LinkedList<String>();
			reader = new BufferedReader(new FileReader(people));
			String r = null;
			while ((r = reader.readLine()) != null) {
				result.add(r.trim());
			}

			// On success return the contents in a list.
			return result;

		} catch (Exception e) {
			// On error log it and throw a Service-level exception.
			LOG.warn("getCommittersName() failed. Reason: " + e.getMessage());
			throw new DepBeanException(e);
		} finally {
			// Close reader if it is possible.
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					LOG.warn("File reader object for " + peoplePath + " could not be closed. Reason: " + e.getMessage());
				}
			}
		}
	}

	public String findReleaser(Product p, String version) throws DepBeanException {
		try {
			// If the releaselog file is not cached
			if (releaseLog == null) {
				releaseLog = parseReleaseLog();
			}

			Pair<String, String> key = Pair.of(p.getContainingFolders(), version);
			String committer = releaseLog.get(key);
			if (committer == null) {
				// If committer not found then something is not OK => exception.
				throw new RuntimeException("Releaser not found in the log file");
			} else {
				return committer;
			}
		} catch (Exception e) {
			// On error log it and throw a Service-level exception.
			LOG.warn("findReleaser() failed. Reason is: " + e.getMessage());
			throw new DepBeanException(e);
		}
	}

	public void reportClientActivation(String user, String host) throws DepBeanException {
		LOG.info("Client activation. Username: " + user + ", host: " + host + ".");
	}
	
	private Map<Pair<String, String>, String> parseReleaseLog() throws Exception {
		List<String> log = readReleaseLog();
		return parseReleaseLog(log);
	}

	private List<String> readReleaseLog() throws IOException {

		BufferedReader reader = null;
		try {
			List<String> log = new LinkedList<String>();
			final URL releaseLogURL = new URL(ConstantStore.CMMNBUILD_RELEASELOG_URL);
			String r = null;
			reader = new BufferedReader(new InputStreamReader(releaseLogURL.openStream()));
			while ((r = reader.readLine()) != null) {
				log.add(r.trim());
			}
			reader.close();
			return log;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	Map<Pair<String, String>, String> parseReleaseLog(List<String> releaseLog) {
		Map<Pair<String, String>, String> result = new HashMap<Pair<String, String>, String>();
		Pattern pattern = Pattern
				.compile("^\t?\t?([a-zA-Z0-9\\-\\_\\/\\.]*)\t\t([0-9\\.\\-\\_a-zA-Z\\=]*|Qfix)\t.*\t([a-z]*)\tPREVIOUS.*$");

		for (String log : releaseLog) {
			Matcher m = pattern.matcher(log);
			if (!m.find()) {
				continue;
			} else {
				String cf = m.group(1);
				String ver = m.group(2);
				String releaser = m.group(3);
				result.put(Pair.of(cf, ver), releaser);
			}
		}
		return result;
	}

	/**
	 * Finalizer method called by the Spring framework on closing.
	 */
	public void close() {
		controller.removeDependencyUpdateListener(listener);
	}

	public Object getCompactedServerModelFor(List<String> projects) {
		return db.findCp3ModelForDirectDeps(projects);
	}

}
