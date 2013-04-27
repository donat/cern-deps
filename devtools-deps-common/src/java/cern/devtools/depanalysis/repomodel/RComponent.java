/**
 */
package cern.devtools.depanalysis.repomodel;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RComponent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RComponent#getName <em>Name</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RComponent#getUuid <em>Uuid</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RComponent#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RComponent#getIncoming <em>Incoming</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRComponent()
 * @model abstract="true"
 * @generated
 */
public interface RComponent extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRComponent_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RComponent#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uuid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uuid</em>' attribute.
	 * @see #setUuid(String)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRComponent_Uuid()
	 * @model
	 * @generated
	 */
	String getUuid();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RComponent#getUuid <em>Uuid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uuid</em>' attribute.
	 * @see #getUuid()
	 * @generated
	 */
	void setUuid(String value);

	/**
	 * Returns the value of the '<em><b>Outgoing</b></em>' reference list.
	 * The list contents are of type {@link cern.devtools.depanalysis.repomodel.RDependency}.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RDependency#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing</em>' reference list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRComponent_Outgoing()
	 * @see cern.devtools.depanalysis.repomodel.RDependency#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	List<RDependency> getOutgoing();

	/**
	 * Returns the value of the '<em><b>Incoming</b></em>' reference list.
	 * The list contents are of type {@link cern.devtools.depanalysis.repomodel.RDependency}.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RDependency#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming</em>' reference list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRComponent_Incoming()
	 * @see cern.devtools.depanalysis.repomodel.RDependency#getTo
	 * @model opposite="to"
	 * @generated
	 */
	List<RDependency> getIncoming();

} // RComponent
