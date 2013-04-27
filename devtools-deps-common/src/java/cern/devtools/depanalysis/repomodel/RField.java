/**
 */
package cern.devtools.depanalysis.repomodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RField</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RField#getType <em>Type</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RField#getClass_ <em>Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRField()
 * @model
 * @generated
 */
public interface RField extends RStructural {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRField_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RField#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Class</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RClass#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' container reference.
	 * @see #setClass(RClass)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRField_Class()
	 * @see cern.devtools.depanalysis.repomodel.RClass#getFields
	 * @model opposite="fields" required="true" transient="false"
	 * @generated
	 */
	RClass getClass_();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RField#getClass_ <em>Class</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' container reference.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass(RClass value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String fqName();

} // RField
