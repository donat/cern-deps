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
package cern.devtools.deps.domain;

import java.util.Collection;
import java.util.List;

/**
 * Domain object representing a java class.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public interface ApiClass extends CodeElement {

	/**
	 * @return Simple name of the class without the package declaration.
	 */
	public String getSimpleName();

	/**
	 * @return Containing package name.
	 */
	public String getPackageName();

	/**
	 * Returns the fully qualified name based on the simple name and the package name.
	 * 
	 * @return The fully qualified name.
	 */
	public String getFqName();

	/**
	 * @return FQ name of the superclass.
	 */
	public String getExtends();

	/**
	 * @return FQ name of the implemented interfaces divided by a space character.
	 */
	public String getImplements();

	/**
	 * @return Container product.
	 */
	public Product getProduct();

	/**
	 * @param p Container product.
	 */
	public void setProduct(Product p);

	/**
	 * Defined methods in the class.
	 */
	public Collection<Method> getMethods();

	/**
	 * Defined fields in the class.
	 */
	public Collection<Field> getFields();

	/**
	 * Calculate the path of the java file, where the class is defined. In case a simple class
	 * <code>org.example.Myclass</code> this method should <code>return org/example/Myclass.java</code>. Another
	 * use-case, when the class is an internal/anonymous class. For example if the class is
	 * <code>org.example.AnotherClass$Internal</code>, the <code>org/example/AnotherClass.java</code> should be
	 * returned.
	 * 
	 * @return The relative source path of the container source file relative to the product's source folder.
	 */
	public String getSourcePath();

	/**
	 * Indicates if the class is private.
	 * 
	 * @return <code>true</code>, if the class is private.
	 */
	public boolean isPrivate();

	/**
	 * Indicates if the instance is an anonymous class.
	 * 
	 * @return <code>true</code>, if anonymous.
	 */
	public boolean isAnonymous();

	/**
	 * Contains the string representation of the classes referenced in this class. This method is used for determining
	 * which class has a {@link DependencyType#CLASS_USAGE} dependency on an another.
	 * 
	 * @return The names of the referenced classes.
	 */
	public List<String> getReferencedClasses();
}
