/**
 */
package cern.devtools.depanalysis.repomodel;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RStructural</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RStructural#getModifiers <em>Modifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRStructural()
 * @model abstract="true"
 * @generated
 */
public interface RStructural extends RVersioned {
	/**
	 * Returns the value of the '<em><b>Modifiers</b></em>' attribute list.
	 * The list contents are of type {@link cern.devtools.depanalysis.repomodel.RModifier}.
	 * The literals are from the enumeration {@link cern.devtools.depanalysis.repomodel.RModifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modifiers</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modifiers</em>' attribute list.
	 * @see cern.devtools.depanalysis.repomodel.RModifier
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRStructural_Modifiers()
	 * @model
	 * @generated
	 */
	List<RModifier> getModifiers();

} // RStructural
