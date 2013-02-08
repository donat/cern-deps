/***********************************************************************************************************************
 * � Copyright 2013 CERN. This software is distributed under the terms of the GNU General Public Licence version 3 (GPL
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
package cern.devtools.deps.bean;

import cern.devtools.deps.domain.Product;

/**
 * Descriptor for obtaining a {@link Product} with it.
 * 
 * @author Donat Csikos
 * 
 */
public interface ArtifactDescriptor {
	/**
	 * @return The name of the product.
	 */
	public String getName();

	/**
	 * @return The version of the product.
	 */
	public String getVersion();

	/**
	 * 
	 * @return The absolute file path which can used to open the jar file.
	 */
	public String getJarPath();

	/**
	 * @return If the product is in the pcrops repository, it returns the container subfolder structure.
	 */
	public String getContainingFolders();
}