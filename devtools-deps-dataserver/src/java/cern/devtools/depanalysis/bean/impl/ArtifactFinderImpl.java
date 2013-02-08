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

import java.io.File;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cern.accsoft.commons.util.OSUtils;
import cern.devtools.depanalysis.bean.ArtifactDescriptor;
import cern.devtools.depanalysis.bean.ArtifactFinder;
import cern.devtools.deps.bean.DepBeanException;

/**
 * Implementation of the {@link ArtifactFinder} interface for retrieving the products from the Commonbuild repository.<p>
 * 
 * The constructor saves the location information and the search properties and initiates the search by calling the
 * method specified in the interface.<p>
 * 
 * The implementation is capable of extract two type of jar set from the specified repository: only the latest version
 * or all versions of the products. Also there is the option to perform a <i>slow</i> and a <i>fast</i> execution. The
 * first accesses all folders and looks for files. It requires a huge amount of resources takes minutes to finish, but
 * every found artifacts will be found. On the other hand, the <i>fast</i> version extracts the information by parsing
 * the repository's xml descriptor, which is much faster, but does not contain all jar's and sometimes points to a
 * non-existing product.<p>
 * 
 * @author Vito Baggiolini, Donat Csikos
 */
public final class ArtifactFinderImpl implements ArtifactFinder {

	/**
	 * Helper class for the SAX parser to gather information from repository.xml.
	 */
	private final class ProductHandler extends DefaultHandler {
		// Result holder.
		private List<Properties> descriptorData;
		// <product> node properties.
		private String directory;
		private boolean isPro;
		private String jarpath;
		private String name;
		private Date releaseDate;
		private String version;

		// Indicates, that the execution is at the <jar> tag, and jar location can be read in the characters function.
		boolean in_jar = false;
		// Same as the in_jar, but here we want to know save the release time information.
		boolean in_release = false;

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			if (in_release) {
				String dateString = new String(ch, start, length);
				if (dateString != null) {
					// If hour:minute:second data is present, then cut it.
					if (dateString.contains(":")) {
						dateString = dateString.substring(0, dateString.length() - 9);
					}

					DateFormat df = new SimpleDateFormat("EEEE, MMM d, y");
					try {
						releaseDate = df.parse(dateString);
					} catch (ParseException e) {
						LOG.debug("Date cannot be parsed. Reason is: " + e.getMessage() + ".");
					}
				}
			} else if (in_jar) {
				jarpath = new String(ch, start, length);
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			// on </product> node save the entry data and reset the information holders
			if (qName.equals("product")) {
				if ((!justProJars) || (justProJars && isPro)) {
					Properties p = new Properties();
					p.put("name", name);
					p.put("version", version);
					p.put("directory", directory);
					p.put("jarPath", jarpath);
					if (releaseDate != null) {
						p.put("relaseDate", releaseDate);
					}

					descriptorData.add(p);
				}
				name = null;
				version = null;
				directory = null;
				releaseDate = null;
				isPro = false;
				jarpath = null;
			} else if (qName.equals("releaseDate")) {
				in_release = false;
			} else if (qName.equals("jar")) {
				in_jar = false;
			}
		}

		public List<Properties> getDescriptors() {
			return descriptorData;
		}

		@Override
		public void startDocument() throws SAXException {
			descriptorData = new LinkedList<Properties>();
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if (qName.equals("product")) {
				for (int i = 0; i < attributes.getLength(); ++i) {
					name = attributes.getValue("name");
					version = attributes.getValue("version");
					directory = attributes.getValue("directory");

					String link = attributes.getValue("link");
					if (link != null) {
						isPro = link.contains("PRO");
					}

				}
			} else if (qName.equals("releaseDate")) {
				in_release = true;
			}
			if (qName.equals("jar")) {
				in_jar = true;
			}
		}
	}

	/**
	 * Sub-folder structure, where the jars are stored inside the repository items. More information available in the
	 * commonbuild documentation.
	 */
	private static String dirBuildDist = "/build/dist";

	/**
	 * File filter to access files only with the .jar extension.
	 */
	private static final FilenameFilter jarFilter = new FilenameFilter() {
		public boolean accept(File dir, String name) {
			// Accept only .jar files.
			return (name.endsWith(".jar"));
		}
	};

	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(ArtifactFinderImpl.class);

	/**
	 * If set to <code>true</code>, the implementation will sweep through the repository with file operations and checks
	 * if the pointed artifacts are exist.
	 */
	private final boolean checkFilesExist;

	/**
	 * The directory where the commonbuild repository is located (and also the repository.xml).
	 */
	private final String dirPcropsDist;

	/**
	 * Location of the repository.xml stored in a string format.
	 */
	private final String fileRepositoryXml;

	/**
	 * Stores if this finder should retrieve all versions of the software or just the latest (PRO) products.
	 */
	private final boolean justProJars;

	/**
	 * Older jars should not present in the result set.
	 */
	private Date oldestJarToParse = new Date(1l);

	/**
	 * Creates an object, which is capable of finding jar files from the commonbuild repositories with their base
	 * information.
	 * 
	 * @param justProJars If true, just pro jars will be handled during the search.
	 * @param checkFilesExist If true, a descriptor will be constructed by using file operations, and none of them will
	 *        point to a non-existing item. If false, then the result will point some non-existing artifacts, but the
	 *        search will finish much faster
	 */
	public ArtifactFinderImpl(boolean justProJars, boolean checkFilesExist) {
		this(justProJars, checkFilesExist, determinePcropsDist(ConstantStore.PCROPS_LINUX_LOC,
				ConstantStore.PCROPS_WINDOWS_LOC));
	}

	public ArtifactFinderImpl(boolean justProJars, boolean checkFilesExist, String repoLoc) {
		this.dirPcropsDist = repoLoc;
		this.justProJars = justProJars;
		this.checkFilesExist = checkFilesExist;
		this.fileRepositoryXml = repoLoc + File.separator + "repository.xml";
	}

	public List<? extends ArtifactDescriptor> findArtifacts() throws DepBeanException {

		// check if the pointed file exists
		if (!new File(fileRepositoryXml).exists()) {
			throw new DepBeanException(
					"ArtifactFinderImpl initialization failed - repository descriptor does not exist: "
							+ fileRepositoryXml + ".");
		}

		// Extract the descriptors.
		LOG.debug("ArfifactFinderImpl executed at " + new Date() + ".");
		List<FileDescriptor> result = null;
		if (checkFilesExist) {
			result = findCheckedArtifacts();
		} else {
			result = findUncheckedArtifacts();
		}

		// Sort and return the result.
		Collections.sort(result);

		return result;

	}

	/**
	 * Specifies the date of the oldest retrieved jar. If a jar file is released before this date, then it won't be
	 * returned. By default this parameter is set to 1970.01.01.
	 * 
	 * @param date The the border date.
	 */
	public void setOldestJarToParse(String dateString) {
		DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		try {
			this.oldestJarToParse = df.parse(dateString);
		} catch (ParseException e) {
			LOG.debug("Failed to set the oldest jar date", e);
		}
	}

	/**
	 * Returns descriptors faster, but there is no guarantee that they exist. Also it points to one single jar per
	 * product, which can be problematic if the release directory holds more than one jars.
	 * 
	 * @return The found artifacts.
	 * @throws DepBeanException If the repository.xml is not exist or well formed.
	 */
	private List<FileDescriptor> findCheckedArtifacts() throws DepBeanException {
		try {
			List<FileDescriptor> result = new LinkedList<FileDescriptor>();

			List<Properties> buildDirs = parseRepoXml();

			// We don't need dir which is modified before 2007
			long timeInMillis = BeanUtils.getMillis2007();
			for (Properties props : buildDirs) {

				String jarDir = dirPcropsDist + props.get("directory") + File.separator + props.get("version")
						+ dirBuildDist;

				File dir = new File(jarDir);
				if (dir.exists() && dir.isDirectory() && dir.lastModified() >= timeInMillis) {
					String[] shortJarFileNames = dir.list(jarFilter);
					for (String jfn : shortJarFileNames) {
						String name = (String) props.get("name");
						String version = (String) props.get("version");
						String jarPath = jarDir + File.separator + jfn;
						String containingFolders = props.getProperty("directory");
						FileDescriptor np = new FileDescriptor(name, version, jarPath, containingFolders);
						result.add(np);
					}
				}
			}
			return result;
		} catch (Exception e) {
			// Multiplex all type of exception to a Bean-level exception.
			throw new DepBeanException(e);
		}
	}

	/**
	 * Returns descriptors, which guaranteed existence
	 * 
	 * @return List of found descriptors.
	 * @throws DepBeanException If the repository.xml is not exist or well formed.
	 */
	private List<FileDescriptor> findUncheckedArtifacts() throws DepBeanException {
		try {

			// Parse repository.xml and get the jar location data out of it.
			List<FileDescriptor> result = new LinkedList<FileDescriptor>();
			List<Properties> descriptorData = parseRepoXml();

			// Gets information from descriptor
			for (Properties props : descriptorData) {
				String name = (String) props.get("name");
				String version = (String) props.get("version");
				String containingFolders = props.getProperty("directory");
				String jarPath = dirPcropsDist + containingFolders + File.separator + props.get("jarPath");
				FileDescriptor np = new FileDescriptor(name, version, jarPath, containingFolders);
				result.add(np);
			}
			return result;

		} catch (Exception e) {
			throw new DepBeanException(e);
		}
	}

	/**
	 * Runs SAX parser on repository.xml.
	 * 
	 * @return The list of key-value pairs which contains all the information about the products. The available keys:
	 *         "name", "version", "directory" and "jarPath".
	 * @throws Exception If the parsing fails.
	 */
	private List<Properties> parseRepoXml() throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		ProductHandler handler = new ProductHandler();
		saxParser.parse(new File(fileRepositoryXml), handler);
		List<Properties> descriptors = handler.getDescriptors();
		removeOldData(descriptors);
		return descriptors;
	}

	private void removeOldData(List<Properties> descriptors) {
		// Iterate through all gained data.
		Iterator<Properties> it = descriptors.iterator();
		while (it.hasNext()) {
			Properties p = it.next();
			// Check for the release date
			Object releaseDateObject = p.get("relaseDate");
			if (releaseDateObject != null) {
				// If it is described in the xml file then check if it is younger than the deadline.
				Date releaseDate = (Date) releaseDateObject;
				if (releaseDate.before(oldestJarToParse)) {
					it.remove();
				}
			}
		}
	}

	/**
	 * Constructs the repository string based on the OS.
	 * 
	 * @param repoDirLinux Linux path.
	 * @param repoDirWindows Windows path.
	 * @return the chosen string.
	 */
	private static final String determinePcropsDist(String repoDirLinux, String repoDirWindows) {
		String repo = null;
		if (OSUtils.IS_WINDOWS) {
			repo = repoDirWindows;
		} else if (OSUtils.IS_LINUX) {
			repo = repoDirLinux;
		} else {
			throw new RuntimeException("ArtifactFinderImpl - unsopported platform.");
		}
		return repo;
	}

	/**
	 * Sets the subfolder's name where commonbuild stores the released jar inside the product container. Normally it is
	 * the build/dist folder (in the
	 * ${cmmnbuild-home}/${poject-containing-folders}/${version}/build/dist/${project-name}.jar. But because
	 * 
	 * @param buildDist
	 */
	// public static void setBuildDistDirectoryForTesting(String buildDist) {
	//
	// }
}
