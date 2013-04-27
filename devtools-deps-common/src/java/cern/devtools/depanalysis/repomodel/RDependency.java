/**
 */
package cern.devtools.depanalysis.repomodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RDependency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RDependency#getFrom <em>From</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RDependency#getTo <em>To</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RDependency#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRDependency()
 * @model
 * @generated
 */
public interface RDependency extends EObject {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RComponent#getOutgoing <em>Outgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(RComponent)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRDependency_From()
	 * @see cern.devtools.depanalysis.repomodel.RComponent#getOutgoing
	 * @model opposite="outgoing" required="true"
	 * @generated
	 */
	RComponent getFrom();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RDependency#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(RComponent value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RComponent#getIncoming <em>Incoming</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(RComponent)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRDependency_To()
	 * @see cern.devtools.depanalysis.repomodel.RComponent#getIncoming
	 * @model opposite="incoming" required="true"
	 * @generated
	 */
	RComponent getTo();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RDependency#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(RComponent value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link cern.devtools.depanalysis.repomodel.RDependencyType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see cern.devtools.depanalysis.repomodel.RDependencyType
	 * @see #setType(RDependencyType)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRDependency_Type()
	 * @model
	 * @generated
	 */
	RDependencyType getType();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RDependency#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see cern.devtools.depanalysis.repomodel.RDependencyType
	 * @see #getType()
	 * @generated
	 */
	void setType(RDependencyType value);

} // RDependency
