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
package cern.devtools.deps.bean;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.DependencyType;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Product;

/**
 * Interface for communication between the framework and the database.<p>
 * 
 * The implementation should define all the operations for the persisting and retrieving an object from the database. It
 * includes finding, saving and deleting a {@link Product}.<p>
 * 
 * The database's responsibility to find and maintain the dependencies between the element of a {@link Product}. Because
 * of this, the dependency extraction can be independent of the data representation's structure.<p>
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public interface DatabaseDao {

	/**
	 * Finds a product by it's properties. If the product does not exist, then the method returns <code>null</code>.
	 * 
	 * @param product
	 *            the instance which carries the properties to search.
	 * @param deep
	 *            If set to false, only the Product instance will be returned without the contained class structure.
	 * @return The found product instance or <code>null</code>, if does not exist.
	 * @throws DatabaseException
	 *             If the operation fails.
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Product findProduct(Product product, boolean deep) throws DatabaseException;

	/**
	 * Saves the specified product and updates all the referenced items. If a same product exists, it must merge the new
	 * and the existing ones. If the version number already exists, the function should throw an exception.
	 * 
	 * @param product
	 *            The product to save or to merge.
	 * @throws DatabaseException
	 *             If the operation fails.
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveProduct(Product product) throws DatabaseException;

	/**
	 * Delete the specified product from the database. This function should eliminate all the structure and the
	 * dependencies from the database. If the passed product instance does not have an id (e.g. the instance is not a
	 * persistent object), the method will look after the product based on it's properties. If it finds, then deletes it
	 * from the database. if there is no object, then the database will be untouched.
	 * 
	 * @param product
	 *            The product to delete.
	 * @throws DatabaseException
	 *             If the operation fails.
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteProduct(Product product) throws DatabaseException;

	/**
	 * TODO
	 * 
	 * @param product
	 * @throws DatabaseException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateProduct(Product product) throws DatabaseException;

	/**
	 * Looks for dependencies in the database and stores is. First the function should execute a find operation on every
	 * element of the targets and should make a result set from them. After the implementation should store a a
	 * dependency starting from the source argument to each item in the previously found result set.
	 * 
	 * @param source
	 *            The item, from where the dependency starts. It has to be in the database or else the function will
	 *            throw an Exception.
	 * @param targets
	 *            The items, which the implementation should look for in the database.
	 * @param type
	 *            the type of the dependency the function should look for and store.
	 * @return The number of found dependencies.
	 * @throws DatabaseException
	 *             If the operation fails
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int findAndSaveDependencyRelation(CodeElement source, Collection<? extends CodeElement> targets,
			DependencyType type) throws DatabaseException;

	/**
	 * Searches the database for incoming dependency for a passed element. In case of {@link ApiClass} parameter, it
	 * searches for all the classes outside of the parameter's project, which inherits from it, and for all the methods
	 * which uses the class (e.g. instantiating it). The result will contain all the required dependency information.
	 * 
	 * @param element
	 *            The destination part of the dependency relation.
	 * @return The set of dependency, which contains the source part of the dependency relation.
	 */

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Collection<Dependency> findClassDependencies(ApiClass element) throws DatabaseException;

	/**
	 * Searches the database for incoming dependency for a passed element. In case of {@link Method} parameter, it
	 * searches for all methods (outside of the project) which call the passed method and all methods which overrides
	 * the passed one.
	 * 
	 * @param element
	 *            The destination part of the dependency relation.
	 * @return The set of dependency, which contains the source part of the dependency relation.
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Collection<Dependency> findMethodDependencies(Method element) throws DatabaseException;

	/**
	 * Searches the database for incoming dependency for a passed element. In case of {@link Field} parameter, it
	 * searches for all methods which access the passed field outside of the field's project.
	 * 
	 * @param element
	 *            The destination part of the dependency relation.
	 * @return The set of dependency, which contains the source part of the dependency relation.
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Collection<Dependency> findFieldDependencies(Field element) throws DatabaseException;

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Collection<Dependency> findProductDependencies(Product element) throws DatabaseException;
	
	public long getNumOfDeps();

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void flush(String string) throws IOException;

	@Transactional(propagation = Propagation.REQUIRED)
	public void reset();

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Object findCp3ModelForDirectDeps(List<String> projects);
}
