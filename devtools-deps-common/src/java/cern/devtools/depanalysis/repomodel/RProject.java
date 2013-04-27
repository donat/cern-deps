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

} // RProject
