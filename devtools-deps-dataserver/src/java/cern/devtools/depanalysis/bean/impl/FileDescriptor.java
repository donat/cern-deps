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

import cern.devtools.depanalysis.bean.ArtifactDescriptor;

/**
 * Describes an artifact which is obtained from commonbuild release repository Stores all necessary information about
 * the product: name, version, location, important folder hierarchy data. e.g. jarpath
 * /user/pcrops/dist/lsa/lsa-app-config/3.3.4/build/dist/lsa-app-config.jar has
 * <ul>
 * <li>the repository root: /usr/pcprops/dist</li>
 * <li>project name: lsa-app-config</li>
 * <li>version: 3.3.4</li>
 * <li>containing folders: lsa/lsa-app-config</li>
 * </ul>
 * 
 * @author Donat Csikos
 */
public class FileDescriptor implements Comparable<FileDescriptor>, ArtifactDescriptor {
	private final String name;
	private final String version;
	private final String jarPath;
	private final String containingFolders;

	/**
	 * Constructor
	 * 
	 * @param name name of the product
	 * @param version version string
	 * @param jarPath fully qualified path where the jar is present
	 * @param containingFolders containing source path directory structure
	 */
	public FileDescriptor(String name, String version, String jarPath, String containingFolders) {
		this.name = name;
		this.version = version;
		this.jarPath = jarPath;
		this.containingFolders = containingFolders;
	}

	/**
	 * @return product name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return string representation of the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @return canonical path for the file
	 */
	public String getJarPath() {
		return jarPath;
	}

	/**
	 * @return source folder hierarchy for later usage (to show the source code in the front-end)
	 */
	public String getContainingFolders() {
		return containingFolders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((containingFolders == null) ? 0 : containingFolders.hashCode());
		result = prime * result + ((jarPath == null) ? 0 : jarPath.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileDescriptor other = (FileDescriptor) obj;
		if (containingFolders == null) {
			if (other.containingFolders != null)
				return false;
		} else if (!containingFolders.equals(other.containingFolders))
			return false;
		if (jarPath == null) {
			if (other.jarPath != null)
				return false;
		} else if (!jarPath.equals(other.jarPath))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArtifactDescriptorImpl [name=" + name + ", version=" + version + ", jarPath=" + jarPath
				+ ", containingFolders=" + containingFolders + "]";
	}

	public int compareTo(FileDescriptor o) {
		return this.name.compareTo(o.name);
	}

}
