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
package cern.devtools.depanalysis.domain;

import java.util.Collection;

/**
 * Class representing a java project.
 *
 * @author Donat Csikos
 */
public interface Product extends CodeElement {
	/**
	 * List of the classes and interfaces stored in the product.
	 *
	 * @return
	 */
	public Collection<ApiClass> getClasses();

	/**
	 * @return name of the product
	 */
	public String getName();

	/**
	 * @return the folder hiearchy where the product is stored.
	 */
	public String getContainingFolders();

	/**
	 *
	 * @return The store location of the product.
	 */
	public String getStoreLocation();
}
