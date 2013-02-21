/***********************************************************************************************************************
 * © Copyright 2013 CERN. This software is distributed under the terms of the GNU General Public Licence version 3 (GPL
 * Version 3), copied verbatim in the file "COPYING". In applying this licence, CERN does not waive the privileges and
 * immunities granted to it by virtue of its status as an Intergovernmental Organization or submit itself to any
 * jurisdiction.
 * 
 * If you modify this Program, or any covered work, by linking or combining it with the Eclipse Integrated Development
 * Environment Plugin libraries (or a modified version of these libraries), containing parts covered by the terms of EPL
 * (Eclipse Public licence), the licensors of this Program grant you additional permission to convey the resulting work.
 * Corresponding Source for a non-source form of such a combination shall include the source code for the parts of
 * Eclipse Integrated Development Environment Plugin libraries used as well as that of the covered work.
 **********************************************************************************************************************/
package cern.devtools.deps.repomodel;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Product;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RClass</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.deps.repomodel.RClass#getSimpleName <em>Simple Name</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RClass#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RClass#getRMethods <em>RMethods</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RClass#getRFields <em>RFields</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RClass#getRProject <em>RProject</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RClass#getExtends <em>Extends</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RClass#getImplements <em>Implements</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RClass#isPrivate <em>Private</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RClass#isAnonymous <em>Anonymous</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RClass#getReferencedClasses <em>Referenced Classes</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RClass#getFqName <em>Fq Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass()
 * @model kind="class"
 * @generated
 */
@SuppressWarnings("all")
public class RClass extends RCodeElement implements ApiClass {
	/**
	 * The default value of the '{@link #getSimpleName() <em>Simple Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimpleName()
	 * @generated
	 * @ordered
	 */
	protected static final String SIMPLE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSimpleName() <em>Simple Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimpleName()
	 * @generated
	 * @ordered
	 */
	protected String simpleName = SIMPLE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected String packageName = PACKAGE_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRMethods() <em>RMethods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRMethods()
	 * @generated
	 * @ordered
	 */
	protected EList rMethods;

	/**
	 * The cached value of the '{@link #getRFields() <em>RFields</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRFields()
	 * @generated
	 * @ordered
	 */
	protected EList rFields;

	/**
	 * The default value of the '{@link #getExtends() <em>Extends</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtends()
	 * @generated
	 * @ordered
	 */
	protected static final String EXTENDS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExtends() <em>Extends</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtends()
	 * @generated
	 * @ordered
	 */
	protected String extends_ = EXTENDS_EDEFAULT;

	/**
	 * The default value of the '{@link #getImplements() <em>Implements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplements()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPLEMENTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImplements() <em>Implements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplements()
	 * @generated
	 * @ordered
	 */
	protected String implements_ = IMPLEMENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #isPrivate() <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrivate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PRIVATE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPrivate() <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrivate()
	 * @generated
	 * @ordered
	 */
	protected boolean private_ = PRIVATE_EDEFAULT;

	/**
	 * This is true if the Private attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean privateESet;

	/**
	 * The default value of the '{@link #isAnonymous() <em>Anonymous</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAnonymous()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ANONYMOUS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAnonymous() <em>Anonymous</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAnonymous()
	 * @generated
	 * @ordered
	 */
	protected boolean anonymous = ANONYMOUS_EDEFAULT;

	/**
	 * The default value of the '{@link #getReferencedClasses() <em>Referenced Classes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedClasses()
	 * @generated
	 * @ordered
	 */
	protected static final EList REFERENCED_CLASSES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReferencedClasses() <em>Referenced Classes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedClasses()
	 * @generated
	 * @ordered
	 */
	protected EList referencedClasses = REFERENCED_CLASSES_EDEFAULT;

	/**
	 * The default value of the '{@link #getFqName() <em>Fq Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFqName()
	 * @generated
	 * @ordered
	 */
	protected static final String FQ_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFqName() <em>Fq Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFqName()
	 * @generated
	 * @ordered
	 */
	protected String fqName = FQ_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RClass() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return RepomodelPackage.Literals.RCLASS;
	}

	/**
	 * Returns the value of the '<em><b>Simple Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Simple Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simple Name</em>' attribute.
	 * @see #setSimpleName(String)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass_SimpleName()
	 * @model
	 * @generated
	 */
	public String getSimpleName() {
		return simpleName;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RClass#getSimpleName <em>Simple Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simple Name</em>' attribute.
	 * @see #getSimpleName()
	 * @generated
	 */
	public void setSimpleName(String newSimpleName) {
		String oldSimpleName = simpleName;
		simpleName = newSimpleName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RCLASS__SIMPLE_NAME, oldSimpleName, simpleName));
	}

	/**
	 * Returns the value of the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Name</em>' attribute.
	 * @see #setPackageName(String)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass_PackageName()
	 * @model
	 * @generated
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RClass#getPackageName <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Name</em>' attribute.
	 * @see #getPackageName()
	 * @generated
	 */
	public void setPackageName(String newPackageName) {
		String oldPackageName = packageName;
		packageName = newPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RCLASS__PACKAGE_NAME, oldPackageName, packageName));
	}

	/**
	 * Returns the value of the '<em><b>RMethods</b></em>' containment reference list.
	 * The list contents are of type {@link cern.devtools.deps.repomodel.RMethod}.
	 * It is bidirectional and its opposite is '{@link cern.devtools.deps.repomodel.RMethod#getRClass <em>RClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RMethods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RMethods</em>' containment reference list.
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass_RMethods()
	 * @see cern.devtools.deps.repomodel.RMethod#getRClass
	 * @model type="cern.devtools.depanalysis.repomodel.RMethod" opposite="rClass" containment="true"
	 * @generated
	 */
	public EList getRMethods() {
		if (rMethods == null) {
			rMethods = new EObjectContainmentWithInverseEList(RMethod.class, this, RepomodelPackage.RCLASS__RMETHODS, RepomodelPackage.RMETHOD__RCLASS);
		}
		return rMethods;
	}

	/**
	 * Returns the value of the '<em><b>RFields</b></em>' containment reference list.
	 * The list contents are of type {@link cern.devtools.deps.repomodel.RField}.
	 * It is bidirectional and its opposite is '{@link cern.devtools.deps.repomodel.RField#getRClass <em>RClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RFields</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RFields</em>' containment reference list.
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass_RFields()
	 * @see cern.devtools.deps.repomodel.RField#getRClass
	 * @model type="cern.devtools.depanalysis.repomodel.RField" opposite="rClass" containment="true"
	 * @generated
	 */
	public EList getRFields() {
		if (rFields == null) {
			rFields = new EObjectContainmentWithInverseEList(RField.class, this, RepomodelPackage.RCLASS__RFIELDS, RepomodelPackage.RFIELD__RCLASS);
		}
		return rFields;
	}

	/**
	 * Returns the value of the '<em><b>RProject</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link cern.devtools.deps.repomodel.RProject#getRClasses <em>RClasses</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RProject</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RProject</em>' container reference.
	 * @see #setRProject(RProject)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass_RProject()
	 * @see cern.devtools.deps.repomodel.RProject#getRClasses
	 * @model opposite="rClasses" required="true" transient="false"
	 * @generated
	 */
	public RProject getRProject() {
		if (eContainerFeatureID() != RepomodelPackage.RCLASS__RPROJECT) return null;
		return (RProject)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRProject(RProject newRProject, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRProject, RepomodelPackage.RCLASS__RPROJECT, msgs);
		return msgs;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RClass#getRProject <em>RProject</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RProject</em>' container reference.
	 * @see #getRProject()
	 * @generated
	 */
	public void setRProject(RProject newRProject) {
		if (newRProject != eInternalContainer() || (eContainerFeatureID() != RepomodelPackage.RCLASS__RPROJECT && newRProject != null)) {
			if (EcoreUtil.isAncestor(this, newRProject))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRProject != null)
				msgs = ((InternalEObject)newRProject).eInverseAdd(this, RepomodelPackage.RPROJECT__RCLASSES, RProject.class, msgs);
			msgs = basicSetRProject(newRProject, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RCLASS__RPROJECT, newRProject, newRProject));
	}

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
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass_Extends()
	 * @model
	 * @generated
	 */
	public String getExtends() {
		return extends_;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RClass#getExtends <em>Extends</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extends</em>' attribute.
	 * @see #getExtends()
	 * @generated
	 */
	public void setExtends(String newExtends) {
		String oldExtends = extends_;
		extends_ = newExtends;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RCLASS__EXTENDS, oldExtends, extends_));
	}

	/**
	 * Returns the value of the '<em><b>Implements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implements</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implements</em>' attribute.
	 * @see #setImplements(String)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass_Implements()
	 * @model
	 * @generated
	 */
	public String getImplements() {
		return implements_;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RClass#getImplements <em>Implements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implements</em>' attribute.
	 * @see #getImplements()
	 * @generated
	 */
	public void setImplements(String newImplements) {
		String oldImplements = implements_;
		implements_ = newImplements;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RCLASS__IMPLEMENTS, oldImplements, implements_));
	}

	/**
	 * Returns the value of the '<em><b>Private</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Private</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Private</em>' attribute.
	 * @see #isSetPrivate()
	 * @see #unsetPrivate()
	 * @see #setPrivate(boolean)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass_Private()
	 * @model unsettable="true"
	 * @generated
	 */
	public boolean isPrivate() {
		return private_;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RClass#isPrivate <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Private</em>' attribute.
	 * @see #isSetPrivate()
	 * @see #unsetPrivate()
	 * @see #isPrivate()
	 * @generated
	 */
	public void setPrivate(boolean newPrivate) {
		boolean oldPrivate = private_;
		private_ = newPrivate;
		boolean oldPrivateESet = privateESet;
		privateESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RCLASS__PRIVATE, oldPrivate, private_, !oldPrivateESet));
	}

	/**
	 * Unsets the value of the '{@link cern.devtools.deps.repomodel.RClass#isPrivate <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPrivate()
	 * @see #isPrivate()
	 * @see #setPrivate(boolean)
	 * @generated
	 */
	public void unsetPrivate() {
		boolean oldPrivate = private_;
		boolean oldPrivateESet = privateESet;
		private_ = PRIVATE_EDEFAULT;
		privateESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, RepomodelPackage.RCLASS__PRIVATE, oldPrivate, PRIVATE_EDEFAULT, oldPrivateESet));
	}

	/**
	 * Returns whether the value of the '{@link cern.devtools.deps.repomodel.RClass#isPrivate <em>Private</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Private</em>' attribute is set.
	 * @see #unsetPrivate()
	 * @see #isPrivate()
	 * @see #setPrivate(boolean)
	 * @generated
	 */
	public boolean isSetPrivate() {
		return privateESet;
	}

	/**
	 * Returns the value of the '<em><b>Anonymous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Anonymous</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Anonymous</em>' attribute.
	 * @see #setAnonymous(boolean)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass_Anonymous()
	 * @model
	 * @generated
	 */
	public boolean isAnonymous() {
		return anonymous;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RClass#isAnonymous <em>Anonymous</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Anonymous</em>' attribute.
	 * @see #isAnonymous()
	 * @generated
	 */
	public void setAnonymous(boolean newAnonymous) {
		boolean oldAnonymous = anonymous;
		anonymous = newAnonymous;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RCLASS__ANONYMOUS, oldAnonymous, anonymous));
	}

	/**
	 * Returns the value of the '<em><b>Referenced Classes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Classes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Classes</em>' attribute.
	 * @see #setReferencedClasses(EList)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass_ReferencedClasses()
	 * @model many="false" transient="true"
	 * @generated
	 */
	public EList getReferencedClasses() {
		return referencedClasses;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RClass#getReferencedClasses <em>Referenced Classes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Classes</em>' attribute.
	 * @see #getReferencedClasses()
	 * @generated
	 */
	public void setReferencedClasses(EList newReferencedClasses) {
		EList oldReferencedClasses = referencedClasses;
		referencedClasses = newReferencedClasses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RCLASS__REFERENCED_CLASSES, oldReferencedClasses, referencedClasses));
	}

	/**
	 * Returns the value of the '<em><b>Fq Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fq Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fq Name</em>' attribute.
	 * @see #setFqName(String)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass_FqName()
	 * @model
	 * @generated
	 */
	public String getFqName() {
		return fqName;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RClass#getFqName <em>Fq Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fq Name</em>' attribute.
	 * @see #getFqName()
	 * @generated
	 */
	public void setFqName(String newFqName) {
		String oldFqName = fqName;
		fqName = newFqName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RCLASS__FQ_NAME, oldFqName, fqName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	public String getSourcePath() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RepomodelPackage.RCLASS__RMETHODS:
				return ((InternalEList)getRMethods()).basicAdd(otherEnd, msgs);
			case RepomodelPackage.RCLASS__RFIELDS:
				return ((InternalEList)getRFields()).basicAdd(otherEnd, msgs);
			case RepomodelPackage.RCLASS__RPROJECT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRProject((RProject)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RepomodelPackage.RCLASS__RMETHODS:
				return ((InternalEList)getRMethods()).basicRemove(otherEnd, msgs);
			case RepomodelPackage.RCLASS__RFIELDS:
				return ((InternalEList)getRFields()).basicRemove(otherEnd, msgs);
			case RepomodelPackage.RCLASS__RPROJECT:
				return basicSetRProject(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case RepomodelPackage.RCLASS__RPROJECT:
				return eInternalContainer().eInverseRemove(this, RepomodelPackage.RPROJECT__RCLASSES, RProject.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RepomodelPackage.RCLASS__SIMPLE_NAME:
				return getSimpleName();
			case RepomodelPackage.RCLASS__PACKAGE_NAME:
				return getPackageName();
			case RepomodelPackage.RCLASS__RMETHODS:
				return getRMethods();
			case RepomodelPackage.RCLASS__RFIELDS:
				return getRFields();
			case RepomodelPackage.RCLASS__RPROJECT:
				return getRProject();
			case RepomodelPackage.RCLASS__EXTENDS:
				return getExtends();
			case RepomodelPackage.RCLASS__IMPLEMENTS:
				return getImplements();
			case RepomodelPackage.RCLASS__PRIVATE:
				return isPrivate() ? Boolean.TRUE : Boolean.FALSE;
			case RepomodelPackage.RCLASS__ANONYMOUS:
				return isAnonymous() ? Boolean.TRUE : Boolean.FALSE;
			case RepomodelPackage.RCLASS__REFERENCED_CLASSES:
				return getReferencedClasses();
			case RepomodelPackage.RCLASS__FQ_NAME:
				return getFqName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RepomodelPackage.RCLASS__SIMPLE_NAME:
				setSimpleName((String)newValue);
				return;
			case RepomodelPackage.RCLASS__PACKAGE_NAME:
				setPackageName((String)newValue);
				return;
			case RepomodelPackage.RCLASS__RMETHODS:
				getRMethods().clear();
				getRMethods().addAll((Collection)newValue);
				return;
			case RepomodelPackage.RCLASS__RFIELDS:
				getRFields().clear();
				getRFields().addAll((Collection)newValue);
				return;
			case RepomodelPackage.RCLASS__RPROJECT:
				setRProject((RProject)newValue);
				return;
			case RepomodelPackage.RCLASS__EXTENDS:
				setExtends((String)newValue);
				return;
			case RepomodelPackage.RCLASS__IMPLEMENTS:
				setImplements((String)newValue);
				return;
			case RepomodelPackage.RCLASS__PRIVATE:
				setPrivate(((Boolean)newValue).booleanValue());
				return;
			case RepomodelPackage.RCLASS__ANONYMOUS:
				setAnonymous(((Boolean)newValue).booleanValue());
				return;
			case RepomodelPackage.RCLASS__REFERENCED_CLASSES:
				setReferencedClasses((EList)newValue);
				return;
			case RepomodelPackage.RCLASS__FQ_NAME:
				setFqName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case RepomodelPackage.RCLASS__SIMPLE_NAME:
				setSimpleName(SIMPLE_NAME_EDEFAULT);
				return;
			case RepomodelPackage.RCLASS__PACKAGE_NAME:
				setPackageName(PACKAGE_NAME_EDEFAULT);
				return;
			case RepomodelPackage.RCLASS__RMETHODS:
				getRMethods().clear();
				return;
			case RepomodelPackage.RCLASS__RFIELDS:
				getRFields().clear();
				return;
			case RepomodelPackage.RCLASS__RPROJECT:
				setRProject((RProject)null);
				return;
			case RepomodelPackage.RCLASS__EXTENDS:
				setExtends(EXTENDS_EDEFAULT);
				return;
			case RepomodelPackage.RCLASS__IMPLEMENTS:
				setImplements(IMPLEMENTS_EDEFAULT);
				return;
			case RepomodelPackage.RCLASS__PRIVATE:
				unsetPrivate();
				return;
			case RepomodelPackage.RCLASS__ANONYMOUS:
				setAnonymous(ANONYMOUS_EDEFAULT);
				return;
			case RepomodelPackage.RCLASS__REFERENCED_CLASSES:
				setReferencedClasses(REFERENCED_CLASSES_EDEFAULT);
				return;
			case RepomodelPackage.RCLASS__FQ_NAME:
				setFqName(FQ_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RepomodelPackage.RCLASS__SIMPLE_NAME:
				return SIMPLE_NAME_EDEFAULT == null ? simpleName != null : !SIMPLE_NAME_EDEFAULT.equals(simpleName);
			case RepomodelPackage.RCLASS__PACKAGE_NAME:
				return PACKAGE_NAME_EDEFAULT == null ? packageName != null : !PACKAGE_NAME_EDEFAULT.equals(packageName);
			case RepomodelPackage.RCLASS__RMETHODS:
				return rMethods != null && !rMethods.isEmpty();
			case RepomodelPackage.RCLASS__RFIELDS:
				return rFields != null && !rFields.isEmpty();
			case RepomodelPackage.RCLASS__RPROJECT:
				return getRProject() != null;
			case RepomodelPackage.RCLASS__EXTENDS:
				return EXTENDS_EDEFAULT == null ? extends_ != null : !EXTENDS_EDEFAULT.equals(extends_);
			case RepomodelPackage.RCLASS__IMPLEMENTS:
				return IMPLEMENTS_EDEFAULT == null ? implements_ != null : !IMPLEMENTS_EDEFAULT.equals(implements_);
			case RepomodelPackage.RCLASS__PRIVATE:
				return isSetPrivate();
			case RepomodelPackage.RCLASS__ANONYMOUS:
				return anonymous != ANONYMOUS_EDEFAULT;
			case RepomodelPackage.RCLASS__REFERENCED_CLASSES:
				return REFERENCED_CLASSES_EDEFAULT == null ? referencedClasses != null : !REFERENCED_CLASSES_EDEFAULT.equals(referencedClasses);
			case RepomodelPackage.RCLASS__FQ_NAME:
				return FQ_NAME_EDEFAULT == null ? fqName != null : !FQ_NAME_EDEFAULT.equals(fqName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (simpleName: ");
		result.append(simpleName);
		result.append(", packageName: ");
		result.append(packageName);
		result.append(", extends: ");
		result.append(extends_);
		result.append(", implements: ");
		result.append(implements_);
		result.append(", private: ");
		if (privateESet) result.append(private_); else result.append("<unset>");
		result.append(", anonymous: ");
		result.append(anonymous);
		result.append(", referencedClasses: ");
		result.append(referencedClasses);
		result.append(", fqName: ");
		result.append(fqName);
		result.append(')');
		return result.toString();
	}

	private RProject prnt;
	
	@Override
	public RProject getProduct() {
	    if (prnt != null) {
	        return prnt;
	    }
		return getRProject();
	}

	@Override
	public void setProduct(Product p) {
	    prnt = (RProject)p;
		setRProject((RProject)p);
		
	}

	@Override
	public Collection<Method> getMethods() {
		return getRMethods();
	}

	@Override
	public Collection<Field> getFields() {
		return getRFields();
	}

} // RClass
