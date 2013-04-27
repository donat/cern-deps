/**
 */
package cern.devtools.depanalysis.repomodel;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RPackage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RPackage#getClasses <em>Classes</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RPackage#getProject <em>Project</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRPackage()
 * @model
 * @generated
 */
public interface RPackage extends RVersioned {
	/**
	 * Returns the value of the '<em><b>Classes</b></em>' containment reference list.
	 * The list contents are of type {@link cern.devtools.depanalysis.repomodel.RClass}.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RClass#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes</em>' containment reference list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRPackage_Classes()
	 * @see cern.devtools.depanalysis.repomodel.RClass#getPackage
	 * @model opposite="package" containment="true" resolveProxies="true"
	 * @generated
	 */
	List<RClass> getClasses();

	/**
	 * Returns the value of the '<em><b>Project</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RProject#getPackages <em>Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project</em>' container reference.
	 * @see #setProject(RProject)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRPackage_Project()
	 * @see cern.devtools.depanalysis.repomodel.RProject#getPackages
	 * @model opposite="packages" required="true" transient="false"
	 * @generated
	 */
	RProject getProject();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RPackage#getProject <em>Project</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project</em>' container reference.
	 * @see #getProject()
	 * @generated
	 */
	void setProject(RProject value);

} // RPackage
