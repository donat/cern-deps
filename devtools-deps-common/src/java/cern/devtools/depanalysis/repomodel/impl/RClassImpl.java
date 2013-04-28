/**
 */
package cern.devtools.depanalysis.repomodel.impl;

import cern.devtools.depanalysis.repomodel.RClass;
import cern.devtools.depanalysis.repomodel.RField;
import cern.devtools.depanalysis.repomodel.RMethod;
import cern.devtools.depanalysis.repomodel.RPackage;
import cern.devtools.depanalysis.repomodel.RepomodelPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RClass</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RClassImpl#getExtends <em>Extends</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RClassImpl#getImplements <em>Implements</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RClassImpl#getFields <em>Fields</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RClassImpl#getMethods <em>Methods</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RClassImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RClassImpl#getReferencedClasses <em>Referenced Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RClassImpl extends RStructuralImpl implements RClass {
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
	 * The cached value of the '{@link #getImplements() <em>Implements</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplements()
	 * @generated
	 * @ordered
	 */
	protected EList<String> implements_;

	/**
	 * The cached value of the '{@link #getFields() <em>Fields</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFields()
	 * @generated
	 * @ordered
	 */
	protected EList<RField> fields;

	/**
	 * The cached value of the '{@link #getMethods() <em>Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethods()
	 * @generated
	 * @ordered
	 */
	protected EList<RMethod> methods;

	/**
	 * The cached value of the '{@link #getReferencedClasses() <em>Referenced Classes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<String> referencedClasses;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RepomodelPackage.Literals.RCLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExtends() {
		return extends_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtends(String newExtends) {
		String oldExtends = extends_;
		extends_ = newExtends;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RCLASS__EXTENDS, oldExtends, extends_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getImplements() {
		if (implements_ == null) {
			implements_ = new EDataTypeUniqueEList<String>(String.class, this, RepomodelPackage.RCLASS__IMPLEMENTS);
		}
		return implements_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<RField> getFields() {
		if (fields == null) {
			fields = new EObjectContainmentWithInverseEList.Resolving<RField>(RField.class, this, RepomodelPackage.RCLASS__FIELDS, RepomodelPackage.RFIELD__CLASS);
		}
		return fields;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<RMethod> getMethods() {
		if (methods == null) {
			methods = new EObjectContainmentWithInverseEList.Resolving<RMethod>(RMethod.class, this, RepomodelPackage.RCLASS__METHODS, RepomodelPackage.RMETHOD__CLASS);
		}
		return methods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RPackage getPackage() {
		if (eContainerFeatureID() != RepomodelPackage.RCLASS__PACKAGE) return null;
		return (RPackage)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RPackage basicGetPackage() {
		if (eContainerFeatureID() != RepomodelPackage.RCLASS__PACKAGE) return null;
		return (RPackage)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPackage(RPackage newPackage, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPackage, RepomodelPackage.RCLASS__PACKAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackage(RPackage newPackage) {
		if (newPackage != eInternalContainer() || (eContainerFeatureID() != RepomodelPackage.RCLASS__PACKAGE && newPackage != null)) {
			if (EcoreUtil.isAncestor(this, newPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPackage != null)
				msgs = ((InternalEObject)newPackage).eInverseAdd(this, RepomodelPackage.RPACKAGE__CLASSES, RPackage.class, msgs);
			msgs = basicSetPackage(newPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RCLASS__PACKAGE, newPackage, newPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getReferencedClasses() {
		if (referencedClasses == null) {
			referencedClasses = new EDataTypeUniqueEList<String>(String.class, this, RepomodelPackage.RCLASS__REFERENCED_CLASSES);
		}
		return referencedClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String fqName() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RepomodelPackage.RCLASS__FIELDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFields()).basicAdd(otherEnd, msgs);
			case RepomodelPackage.RCLASS__METHODS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMethods()).basicAdd(otherEnd, msgs);
			case RepomodelPackage.RCLASS__PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPackage((RPackage)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RepomodelPackage.RCLASS__FIELDS:
				return ((InternalEList<?>)getFields()).basicRemove(otherEnd, msgs);
			case RepomodelPackage.RCLASS__METHODS:
				return ((InternalEList<?>)getMethods()).basicRemove(otherEnd, msgs);
			case RepomodelPackage.RCLASS__PACKAGE:
				return basicSetPackage(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case RepomodelPackage.RCLASS__PACKAGE:
				return eInternalContainer().eInverseRemove(this, RepomodelPackage.RPACKAGE__CLASSES, RPackage.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RepomodelPackage.RCLASS__EXTENDS:
				return getExtends();
			case RepomodelPackage.RCLASS__IMPLEMENTS:
				return getImplements();
			case RepomodelPackage.RCLASS__FIELDS:
				return getFields();
			case RepomodelPackage.RCLASS__METHODS:
				return getMethods();
			case RepomodelPackage.RCLASS__PACKAGE:
				if (resolve) return getPackage();
				return basicGetPackage();
			case RepomodelPackage.RCLASS__REFERENCED_CLASSES:
				return getReferencedClasses();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RepomodelPackage.RCLASS__EXTENDS:
				setExtends((String)newValue);
				return;
			case RepomodelPackage.RCLASS__IMPLEMENTS:
				getImplements().clear();
				getImplements().addAll((Collection<? extends String>)newValue);
				return;
			case RepomodelPackage.RCLASS__FIELDS:
				getFields().clear();
				getFields().addAll((Collection<? extends RField>)newValue);
				return;
			case RepomodelPackage.RCLASS__METHODS:
				getMethods().clear();
				getMethods().addAll((Collection<? extends RMethod>)newValue);
				return;
			case RepomodelPackage.RCLASS__PACKAGE:
				setPackage((RPackage)newValue);
				return;
			case RepomodelPackage.RCLASS__REFERENCED_CLASSES:
				getReferencedClasses().clear();
				getReferencedClasses().addAll((Collection<? extends String>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RepomodelPackage.RCLASS__EXTENDS:
				setExtends(EXTENDS_EDEFAULT);
				return;
			case RepomodelPackage.RCLASS__IMPLEMENTS:
				getImplements().clear();
				return;
			case RepomodelPackage.RCLASS__FIELDS:
				getFields().clear();
				return;
			case RepomodelPackage.RCLASS__METHODS:
				getMethods().clear();
				return;
			case RepomodelPackage.RCLASS__PACKAGE:
				setPackage((RPackage)null);
				return;
			case RepomodelPackage.RCLASS__REFERENCED_CLASSES:
				getReferencedClasses().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RepomodelPackage.RCLASS__EXTENDS:
				return EXTENDS_EDEFAULT == null ? extends_ != null : !EXTENDS_EDEFAULT.equals(extends_);
			case RepomodelPackage.RCLASS__IMPLEMENTS:
				return implements_ != null && !implements_.isEmpty();
			case RepomodelPackage.RCLASS__FIELDS:
				return fields != null && !fields.isEmpty();
			case RepomodelPackage.RCLASS__METHODS:
				return methods != null && !methods.isEmpty();
			case RepomodelPackage.RCLASS__PACKAGE:
				return basicGetPackage() != null;
			case RepomodelPackage.RCLASS__REFERENCED_CLASSES:
				return referencedClasses != null && !referencedClasses.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (extends: ");
		result.append(extends_);
		result.append(", implements: ");
		result.append(implements_);
		result.append(", referencedClasses: ");
		result.append(referencedClasses);
		result.append(')');
		return result.toString();
	}

} //RClassImpl
