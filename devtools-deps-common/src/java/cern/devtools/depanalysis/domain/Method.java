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

import java.util.List;

/**
 * Interface representing a simple Method object from a .class file.
 * 
 * @author Donat Csikos
 */
public interface Method extends CodeElement {

	/**
	 * @return java class containing the method
	 */
	public ApiClass getApiClass();

	/**
	 * Returns the signature
	 * 
	 * @return
	 */
	public String getSignature();

	/**
	 * @param ac the apiclass
	 */
	public void setApiClass(ApiClass ac);

	/**
	 * @param signature
	 * @return
	 */
	public void setSignature(String signature);

	/**
	 * Returns <code>true</code>, if the instance is a static method.
	 * 
	 * @return <code>true</code>, if static.
	 */
	public boolean isStatic();

	/**
	 * Returns <code>true</code>, if the method is private.
	 * 
	 * @return <code>true</code>, if the method is private.
	 */
	public boolean isPrivate();

	/**
	 * Contains the string representation of the external fields which is accessed in this method. This data is used for
	 * determining the {@link DependencyType#FIELD_REFERENCE} type of dependency.
	 * 
	 * @return The accessed external fields.
	 */
	public List<String> getReferencedFields();

	/**
	 * Contains the string representation of the called methods. This data is used for determining the
	 * {@link DependencyType#METHOD_CALL} type of dependency.
	 * 
	 * @return The called methods.
	 */
	public List<String> getReferencedMethods();
}
