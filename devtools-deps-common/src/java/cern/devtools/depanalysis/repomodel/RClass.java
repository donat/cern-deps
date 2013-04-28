/**
 */
package cern.devtools.depanalysis.repomodel;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RClass</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RClass#getExtends <em>Extends</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RClass#getImplements <em>Implements</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RClass#getFields <em>Fields</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RClass#getMethods <em>Methods</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RClass#getPackage <em>Package</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RClass#getReferencedClasses <em>Referenced Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRClass()
 * @model
 * @generated
 */
public interface RClass extends RStructural {
	/**
	 * Returns the value of the '<em><b>Extends</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extends</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extends</em>' attribute.
	 * @see #setExtends(String)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRClass_Extends()
	 * @model
	 * @generated
	 */
	String getExtends();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RClass#getExtends <em>Extends</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extends</em>' attribute.
	 * @see #getExtends()
	 * @generated
	 */
	void setExtends(String value);

	/**
	 * Returns the value of the '<em><b>Implements</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implements</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implements</em>' attribute list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRClass_Implements()
	 * @model
	 * @generated
	 */
	List<String> getImplements();

	/**
	 * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
	 * The list contents are of type {@link cern.devtools.depanalysis.repomodel.RField}.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RField#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fields</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fields</em>' containment reference list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRClass_Fields()
	 * @see cern.devtools.depanalysis.repomodel.RField#getClass_
	 * @model opposite="class" containment="true" resolveProxies="true"
	 * @generated
	 */
	List<RField> getFields();

	/**
	 * Returns the value of the '<em><b>Methods</b></em>' containment reference list.
	 * The list contents are of type {@link cern.devtools.depanalysis.repomodel.RMethod}.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RMethod#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Methods</em>' containment reference list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRClass_Methods()
	 * @see cern.devtools.depanalysis.repomodel.RMethod#getClass_
	 * @model opposite="class" containment="true" resolveProxies="true"
	 * @generated
	 */
	List<RMethod> getMethods();

	/**
	 * Returns the value of the '<em><b>Package</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RPackage#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package</em>' container reference.
	 * @see #setPackage(RPackage)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRClass_Package()
	 * @see cern.devtools.depanalysis.repomodel.RPackage#getClasses
	 * @model opposite="classes" required="true" transient="false"
	 * @generated
	 */
	RPackage getPackage();

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RClass#getPackage <em>Package</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package</em>' container reference.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(RPackage value);

	/**
	 * Returns the value of the '<em><b>Referenced Classes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Classes</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Classes</em>' attribute list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRClass_ReferencedClasses()
	 * @model transient="true"
	 * @generated
	 */
	List<String> getReferencedClasses();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String fqName();

} // RClass
