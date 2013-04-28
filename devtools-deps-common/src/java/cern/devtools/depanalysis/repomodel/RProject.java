/**
 */
package cern.devtools.depanalysis.repomodel;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RProject</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RProject#getPackages <em>Packages</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RProject#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RProject#getRepository <em>Repository</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RProject#getContainingFolders <em>Containing Folders</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RProject#getJarPath <em>Jar Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRProject()
 * @model
 * @generated
 */
public interface RProject extends RVersioned {
	/**
	 * Returns the value of the '<em><b>Packages</b></em>' containment reference list.
	 * The list contents are of type {@link cern.devtools.depanalysis.repomodel.RPackage}.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RPackage#getProject <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Packages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Packages</em>' containment reference list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRProject_Packages()
	 * @see cern.devtools.depanalysis.repomodel.RPackage#getProject
	 * @model opposite="project" containment="true" resolveProxies="true"
	 * @generated
	 */
	List<RPackage> getPackages();

	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' containment reference list.
	 * The list contents are of type {@link cern.devtools.depanalysis.repomodel.RDependency}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependencies</em>' containment reference list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRProject_Dependencies()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	List<RDependency> getDependencies();

	/**
	 * Returns the value of the '<em><b>Repository</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RRepository#getProjects <em>Projects</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repository</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repository</em>' container reference.
	 * @see #setRepository(RRepository)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRProject_Repository()
	 * @see cern.devtools.depanalysis.repomodel.RRepository#getProjects
	 * @model opposite="projects" required="true" transient="false"
	 * @generated
	 */
	RRepository getRepository();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RProject#getRepository <em>Repository</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repository</em>' container reference.
	 * @see #getRepository()
	 * @generated
	 */
	void setRepository(RRepository value);

	/**
	 * Returns the value of the '<em><b>Containing Folders</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Folders</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Folders</em>' attribute.
	 * @see #setContainingFolders(String)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRProject_ContainingFolders()
	 * @model
	 * @generated
	 */
	String getContainingFolders();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RProject#getContainingFolders <em>Containing Folders</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Folders</em>' attribute.
	 * @see #getContainingFolders()
	 * @generated
	 */
	void setContainingFolders(String value);

	/**
	 * Returns the value of the '<em><b>Jar Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jar Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jar Path</em>' attribute.
	 * @see #setJarPath(String)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRProject_JarPath()
	 * @model
	 * @generated
	 */
	String getJarPath();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RProject#getJarPath <em>Jar Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jar Path</em>' attribute.
	 * @see #getJarPath()
	 * @generated
	 */
	void setJarPath(String value);

} // RProject
