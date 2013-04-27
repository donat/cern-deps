/**
 */
package cern.devtools.depanalysis.repomodel;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RRepository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RRepository#getProjects <em>Projects</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRRepository()
 * @model
 * @generated
 */
public interface RRepository extends RComponent {
	/**
	 * Returns the value of the '<em><b>Projects</b></em>' containment reference list.
	 * The list contents are of type {@link cern.devtools.depanalysis.repomodel.RProject}.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RProject#getRepository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Projects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Projects</em>' containment reference list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRRepository_Projects()
	 * @see cern.devtools.depanalysis.repomodel.RProject#getRepository
	 * @model opposite="repository" containment="true" resolveProxies="true"
	 * @generated
	 */
	List<RProject> getProjects();

} // RRepository
