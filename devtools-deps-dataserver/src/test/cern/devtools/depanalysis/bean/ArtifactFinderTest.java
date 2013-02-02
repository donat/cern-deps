/*******************************************************************************
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
 ******************************************************************************/
package cern.devtools.depanalysis.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cern.devtools.depanalysis.bean.impl.ArtifactFinderImpl;

/**
 * Test artifact finding. Because svn does not accept jar files nor folders with the name 'build' the test repository is
 * created in a temporary location. <p>
 *
 * The repository.xml is located in the classpath://resources/repository.xml file. <p>
 * 
 * The schema of test-repository is the following:
 *  
 * ${commonbuild-test-repo-folder}<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;filter<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.0.6<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;build<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dist<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;filter.jar<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.0.7<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.0.8<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;build<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dist<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;filter.jar<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;tea<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.0.0<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;build<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dist<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tea.jar<br/> <p>
 * 
 * The jar files are empty, the details of the projects are described in the xml file.<p>
 * 
 * @author Donat Csikos
 * 
 */
public class ArtifactFinderTest {

	private File cmmnbuildHomeFolder;

	private String cmmnbuildHome;

	@Before
	public void createFolders() throws IOException {
		// Create temporary commonbuild home.
		cmmnbuildHomeFolder = File.createTempFile("cmmnbuild-test-repo", null);
		cmmnbuildHomeFolder = cmmnbuildHomeFolder.getParentFile();
		cmmnbuildHomeFolder = new File(cmmnbuildHomeFolder.getAbsolutePath() + File.separator + "cmmnbuild-test-repo");
		cmmnbuildHomeFolder.mkdir();
		cmmnbuildHome = cmmnbuildHomeFolder.getAbsolutePath() + File.separator;

		// Create repository.xml inside the repository.
		File repoXml = new File(cmmnbuildHomeFolder.getAbsolutePath() + File.separator + "repository.xml");
		repoXml.createNewFile();
		writeToFile(repoXml, repositoryXmlContent());

		// Create folder Structure.
		String[] folders1 = { "example" };
		String[] folders2 = { "example/filter", "example/tea" };
		String[] folders3 = { "example/filter/0.0.6", "example/filter/0.0.7", "example/filter/0.0.8",
				"example/tea/1.0.0" };
		String[] folders4 = { "example/filter/0.0.6/build", "example/filter/0.0.6/build/dist" };
		String[] folders5 = { "example/filter/0.0.8/build", "example/filter/0.0.8/build/dist" };
		String[] folders6 = { "example/tea/1.0.0/build", "example/tea/1.0.0/build/dist" };

		String[] files = { "example/tea/1.0.0/build/dist/tea.jar", "example/filter/0.0.6/build/dist/filter.jar",
				"example/filter/0.0.8/build/dist/filter.jar" };

		createFolders(cmmnbuildHomeFolder, folders1, folders2, folders3, folders4, folders5, folders6);
		createFiles(cmmnbuildHomeFolder, files);
	}

	private void createFolders(File baseDir, String[]... files) throws IOException {
		for (String[] file : files) {
			for (String f : file) {
				new File(baseDir.getAbsolutePath() + File.separator + f).mkdir();
			}
		}
	}

	private void createFiles(File baseDir, String[] files) throws IOException {
		for (String file : files) {
			File f = new File(baseDir.getAbsolutePath() + File.separator + file);
			touch(f);
		}
	}
	
	private void touch(File file) throws IOException {
		 if (!file.exists()) {
	            OutputStream out = openOutputStream(file);
	            try {
	                if (out != null) {
	                    out.close();
	                }
	            } catch (IOException ioe) {
	                // ignore
	            }
	        }
	        boolean success = file.setLastModified(System.currentTimeMillis());
	        if (!success) {
	            throw new IOException("Unable to set the last modification time for " + file);
	        }
	}
	
	public static FileOutputStream openOutputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canWrite() == false) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null && parent.exists() == false) {
                if (parent.mkdirs() == false) {
                    throw new IOException("File '" + file + "' could not be created");
                }
            }
        }
        return new FileOutputStream(file);
    }

	@After
	public void deleteFolders() {
		deleteDir(cmmnbuildHomeFolder);
	}

	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}

	public String repositoryXmlContent() throws IOException {
		InputStream repoxmlStream = ClassLoader.getSystemClassLoader().getResourceAsStream("resources/repository.xml");
		BufferedReader reader = null;
		StringBuffer result = new StringBuffer();

		reader = new BufferedReader(new InputStreamReader(repoxmlStream));
		String line;
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}
		reader.close();
		return result.toString();

	}

	public void writeToFile(File f, String content) throws IOException {
		FileWriter writer = new FileWriter(f);
		writer.write(content);
		writer.close();
	}

	@Test
	public void getEveryDataFromDescriptor() throws DepBeanException {
		ArtifactFinder af = new ArtifactFinderImpl(false, false, cmmnbuildHome);
		List<? extends ArtifactDescriptor> artifacts = af.findArtifacts();
		assertEquals(4, artifacts.size());
		ArtifactDescriptor ad = artifacts.get(0);
		assertEquals("filter", ad.getName());
		assertEquals("0.0.8", ad.getVersion());
		assertEquals(cmmnbuildHome + "example/filter" + File.separator + "0.0.8/build/dist/filter.jar", ad.getJarPath());
		assertEquals("example/filter", ad.getContainingFolders());
	}

	@Test
	public void excludeNonexisting() throws DepBeanException {
		ArtifactFinder af = new ArtifactFinderImpl(false, true, cmmnbuildHome);
		List<? extends ArtifactDescriptor> as = af.findArtifacts();

		// the 0.0.7 version should be excluded from the results.
		assertEquals(3, as.size());

		for (ArtifactDescriptor ad : as) {
			assertTrue(Arrays.asList("0.0.6", "0.0.8", "1.0.0").contains(ad.getVersion()));
		}

	}

	@Test
	public void onlyProJars() throws DepBeanException {
		ArtifactFinder af = new ArtifactFinderImpl(true, false, cmmnbuildHome);
		List<? extends ArtifactDescriptor> as = af.findArtifacts();
		assertEquals("0.0.8", as.get(0).getVersion());
	}

	@Test
	public void excludeOld() throws DepBeanException {
		// Exclude jars before 2001.
		ArtifactFinderImpl af = new ArtifactFinderImpl(false, false, cmmnbuildHome);
		af.setOldestJarToParse("2001.01.01");

		// The oldest jar (version 0.0.6) should be eliminated.
		List<? extends ArtifactDescriptor> as = af.findArtifacts();

		assertEquals(3, as.size());
		for (ArtifactDescriptor ad : as) {
			assertTrue(Arrays.asList("0.0.7", "0.0.8", "1.0.0").contains(ad.getVersion()));
		}

	}

}
