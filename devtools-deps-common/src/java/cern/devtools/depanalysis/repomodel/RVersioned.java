/**
 */
package cern.devtools.depanalysis.repomodel;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RVersioned</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RVersioned#getVersions <em>Versions</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRVersioned()
 * @model abstract="true"
 * @generated
 */
public interface RVersioned extends RComponent {
	/**
	 * Returns the value of the '<em><b>Versions</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Versions</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Versions</em>' attribute list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRVersioned_Versions()
	 * @model
	 * @generated
	 */
	List<String> getVersions();

} // RVersioned
