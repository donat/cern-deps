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

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.Product;

/**
 * Spring bean interface for remote access the dependency information. This interface should be exposed to the UI layer
 * by utilising Spring RMI.
 *
 * @author Donat Csikos
 */
public interface DependencyService extends Remote {

	/**
	 * Executes a database query which retrieves every dependent elements which contains reference to the specified
	 * signature.
	 *
	 * @param element The method instance which only should contain the signature of the the searched method (in source
	 *        format)
	 * @return The graph of the incoming dependencies
	 */
	public Collection<Dependency> getIncomingDependencies(CodeElement element) throws DepBeanException, RemoteException;

	/**
	 * <p>
	 * Return the names of the people who are able to commit to a specific project.
	 * </p>
	 * <p>
	 * This information is returned from the product's "people" file. For instance the japc project should return
	 * {"lmestre", "vbaggiol", "eroux", "arey", "rgorbono", "vlezhebo"}.
	 * </p>
	 *
	 * @param p The project to return it's committeers.
	 * @return The list of the committeers name.
	 * @throws DepBeanException If an error happens during the execution.
	 */
	public Collection<String> getCommittersName(Product p) throws DepBeanException, RemoteException;

	/**
	 * Returns the name of the person who released the specific version of the product; The method should extract this
	 * information from the release log available from the http://abwww/ap/dist/release_prefs/releaselog resource.
	 *
	 * @param p The product to find the releaser for
	 * @param version The version to find a release.
	 * @return The name of the releaser.
	 * @throws DepBeanException If an error happens during the execution.
	 */
	public String findReleaser(Product p, String version) throws DepBeanException, RemoteException;

	/**
	 * On startup every client should report the usage of the plugin by calling this function once.
	 *
	 * @param user The name of the user,
	 * @param host The host machine's name where the client is started.
	 * @throws DepBeanException If an error happens during the execution.
	 */
	public void reportClientActivation(String user, String host) throws DepBeanException, RemoteException;
}
