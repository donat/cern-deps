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
 * {@link ArtifactDescriptor} implementation which holds the class files in the memory. Serves only testing purposes.
 * 
 * @author Donat Csikos
 */
public class ByteCodeDescriptor implements ArtifactDescriptor {
	/**
	 * Name of the Product.
	 */
	private final String name;

	/**
	 * binary representation of the classes.
	 */
	private final byte[][] classStreams;

	public ByteCodeDescriptor(String name, byte[][] classStreams) {
		this.name = name;
		this.classStreams = classStreams;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		// No valid verion provided.
		return "1.0.0";
	}

	public String getJarPath() {
		// No valid path provided; the name is returned.
		return name;
	}

	public String getContainingFolders() {
		// No valid folders provided.
		return "";
	}

	/**
	 * Returns all streams in binary format, as it was compiled.
	 * 
	 * @return The class streams.
	 */
	public byte[][] getStreams() {
		return classStreams;
	}

}
