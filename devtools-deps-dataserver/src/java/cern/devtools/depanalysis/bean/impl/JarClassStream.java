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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import cern.devtools.depanalysis.bean.ArtifactDescriptor;

/**
 * Helper class providing an iterable interface for accessing a jar's internal binary files as input streams.
 * 
 * @author Donat Csikos
 */
public class JarClassStream implements Iterable<InputStream> {

	/**
	 * Class files ending in the jar file.
	 */
	private static final String CLASS_SUFFIX = ".class";

	private final List<InputStream> streams;

	/**
	 * Private constructor to instantiate it only through the static {@link #fromDescriptor(ArtifactDescriptor)} method.
	 * 
	 * @param path The path to the jar file.
	 * @throws IOException If the file is not accessible.
	 */
	private JarClassStream(List<InputStream> streams) throws IOException {
		this.streams = streams;
	}

	public Iterator<InputStream> iterator() {
		return streams.iterator();
	}

	public static JarClassStream fromDescriptor(ArtifactDescriptor ad) throws IOException {
		if (ad instanceof FileDescriptor) {
			return new JarClassStream(getJarFileContents(ad.getJarPath()));
		} else if (ad instanceof ByteCodeDescriptor) {
			return new JarClassStream(getBinaryContents((ByteCodeDescriptor) ad));
		} else {
			throw new RuntimeException("Arfifact descriptor type not supported:" + ad.getClass());
		}
	}

	private static List<InputStream> getBinaryContents(ByteCodeDescriptor ad) {
		List<InputStream> classStreams = new LinkedList<InputStream>();
		ByteCodeDescriptor desc = (ByteCodeDescriptor) ad;
		for (byte[] bytecode : desc.getStreams()) {
			classStreams.add(new ByteArrayInputStream(bytecode));
		}
		return classStreams;
	}

	private static List<InputStream> getJarFileContents(String jarPath) throws IOException {
		ZipFile zipFile = new ZipFile(jarPath);
		List<ZipEntry> entries = filterClassEntries(zipFile);
		List<InputStream> result = new LinkedList<InputStream>();
		for (ZipEntry entry : entries) {
			result.add(zipFile.getInputStream(entry));
		}
		return result;
	}

	/**
	 * Returns all .class entries from the jar file.
	 * 
	 * @return The list of class files represented as a {@link ZipEntry}.
	 */
	private static List<ZipEntry> filterClassEntries(ZipFile zipFile) {
		List<ZipEntry> classEntries = new LinkedList<ZipEntry>();
		Enumeration<? extends ZipEntry> zes = zipFile.entries();
		while (zes.hasMoreElements()) {
			ZipEntry ze = zes.nextElement();
			if (ze.getName().endsWith(CLASS_SUFFIX)) {
				classEntries.add(ze);
			}
		}
		return classEntries;
	}
}
