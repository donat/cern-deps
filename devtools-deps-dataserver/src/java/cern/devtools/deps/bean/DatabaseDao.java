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

import cern.devtools.depanalysis.repomodel.RClass;
import cern.devtools.depanalysis.repomodel.RComponent;
import cern.devtools.depanalysis.repomodel.RDependency;
import cern.devtools.depanalysis.repomodel.RDependencyType;
import cern.devtools.depanalysis.repomodel.RField;
import cern.devtools.depanalysis.repomodel.RMethod;
import cern.devtools.depanalysis.repomodel.RProject;

/**
 * Interface for communication between the framework and the database.<p>
 * 
 * The implementation should define all the operations for the persisting and retrieving an object from the database. It
 * includes finding, saving and deleting a {@link RProject}.<p>
 * 
 * The database's responsibility to find and maintain the dependencies between the element of a {@link RProject}. Because
 * of this, the RDependency extraction can be independent of the data representation's structure.<p>
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public interface DatabaseDao {

	/**
	 * Finds a RProject by it's properties. If the RProject does not exist, then the method returns <code>null</code>.
	 * 
	 * @param RProject
	 *            the instance which carries the properties to search.
	 * @param deep
	 *            If set to false, only the RProject instance will be returned without the contained class structure.
	 * @return The found RProject instance or <code>null</code>, if does not exist.
	 * @throws DatabaseException
	 *             If the operation fails.
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public RProject findProject(RProject RProject, boolean deep) throws DatabaseException;

	/**
	 * Saves the specified RProject and updates all the referenced items. If a same RProject exists, it must merge the new
	 * and the existing ones. If the version number already exists, the function should throw an exception.
	 * 
	 * @param RProject
	 *            The RProject to save or to merge.
	 * @throws DatabaseException
	 *             If the operation fails.
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveProject(RProject RProject) throws DatabaseException;

	/**
	 * Delete the specified RProject from the database. This function should eliminate all the structure and the
	 * dependencies from the database. If the passed RProject instance does not have an id (e.g. the instance is not a
	 * persistent object), the method will look after the RProject based on it's properties. If it finds, then deletes it
	 * from the database. if there is no object, then the database will be untouched.
	 * 
	 * @param RProject
	 *            The RProject to delete.
	 * @throws DatabaseException
	 *             If the operation fails.
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteRProject(RProject RProject) throws DatabaseException;

	/**
	 * TODO
	 * 
	 * @param RProject
	 * @throws DatabaseException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateRProject(RProject RProject) throws DatabaseException;

	/**
	 * Looks for dependencies in the database and stores is. First the function should execute a find operation on every
	 * element of the targets and should make a result set from them. After the implementation should store a a
	 * RDependency starting from the source argument to each item in the previously found result set.
	 * 
	 * @param source
	 *            The item, from where the RDependency starts. It has to be in the database or else the function will
	 *            throw an Exception.
	 * @param targets
	 *            The items, which the implementation should look for in the database.
	 * @param type
	 *            the type of the RDependency the function should look for and store.
	 * @return The number of found dependencies.
	 * @throws DatabaseException
	 *             If the operation fails
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int findAndSaveRDependencyRelation(RComponent source, Collection<? extends RComponent> targets,
			RDependencyType type) throws DatabaseException;

	/**
	 * Searches the database for incoming RDependency for a passed element. In case of {@link ApiClass} parameter, it
	 * searches for all the classes outside of the parameter's project, which inherits from it, and for all the methods
	 * which uses the class (e.g. instantiating it). The result will contain all the required RDependency information.
	 * 
	 * @param element
	 *            The destination part of the RDependency relation.
	 * @return The set of RDependency, which contains the source part of the RDependency relation.
	 */

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Collection<RDependency> findClassDependencies(RClass element) throws DatabaseException;

	/**
	 * Searches the database for incoming RDependency for a passed element. In case of {@link Method} parameter, it
	 * searches for all methods (outside of the project) which call the passed method and all methods which overrides
	 * the passed one.
	 * 
	 * @param element
	 *            The destination part of the RDependency relation.
	 * @return The set of RDependency, which contains the source part of the RDependency relation.
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Collection<RDependency> findMethodDependencies(RMethod element) throws DatabaseException;

	/**
	 * Searches the database for incoming RDependency for a passed element. In case of {@link Field} parameter, it
	 * searches for all methods which access the passed field outside of the field's project.
	 * 
	 * @param element
	 *            The destination part of the RDependency relation.
	 * @return The set of RDependency, which contains the source part of the RDependency relation.
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Collection<RDependency> findFieldDependencies(RField element) throws DatabaseException;

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Collection<RDependency> findRProjectDependencies(RProject element) throws DatabaseException;
	
	public long getNumOfDeps();

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void flush(String string) throws IOException;

	@Transactional(propagation = Propagation.REQUIRED)
	public void reset();

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Object findCp3ModelForDirectDeps(List<String> projects);
}
