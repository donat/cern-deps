/**
 */
package cern.devtools.depanalysis.repomodel;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RMethod</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RMethod#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RMethod#getParameterTypes <em>Parameter Types</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RMethod#getClass_ <em>Class</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RMethod#getReferencedFields <em>Referenced Fields</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RMethod#getReferencedMethods <em>Referenced Methods</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RMethod#getSignature <em>Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRMethod()
 * @model
 * @generated
 */
public interface RMethod extends RStructural {
	/**
	 * Returns the value of the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Type</em>' attribute.
	 * @see #setReturnType(String)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRMethod_ReturnType()
	 * @model
	 * @generated
	 */
	String getReturnType();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RMethod#getReturnType <em>Return Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Type</em>' attribute.
	 * @see #getReturnType()
	 * @generated
	 */
	void setReturnType(String value);

	/**
	 * Returns the value of the '<em><b>Parameter Types</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Types</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Types</em>' attribute list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRMethod_ParameterTypes()
	 * @model
	 * @generated
	 */
	List<String> getParameterTypes();

	/**
	 * Returns the value of the '<em><b>Class</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RClass#getMethods <em>Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' container reference.
	 * @see #setClass(RClass)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRMethod_Class()
	 * @see cern.devtools.depanalysis.repomodel.RClass#getMethods
	 * @model opposite="methods" required="true" transient="false"
	 * @generated
	 */
	RClass getClass_();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RMethod#getClass_ <em>Class</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' container reference.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass(RClass value);

	/**
	 * Returns the value of the '<em><b>Referenced Fields</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Fields</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Fields</em>' attribute list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRMethod_ReferencedFields()
	 * @model transient="true"
	 *        extendedMetaData="kind='element'"
	 * @generated
	 */
	List<String> getReferencedFields();

	/**
	 * Returns the value of the '<em><b>Referenced Methods</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Methods</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Methods</em>' attribute list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRMethod_ReferencedMethods()
	 * @model transient="true"
	 * @generated
	 */
	List<String> getReferencedMethods();

	/**
	 * Returns the value of the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signature</em>' attribute.
	 * @see #setSignature(String)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRMethod_Signature()
	 * @model
	 * @generated
	 */
	String getSignature();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RMethod#getSignature <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signature</em>' attribute.
	 * @see #getSignature()
	 * @generated
	 */
	void setSignature(String value);

} // RMethod
