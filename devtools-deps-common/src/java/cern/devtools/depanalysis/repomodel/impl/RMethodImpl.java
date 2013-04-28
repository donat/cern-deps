/**
 */
package cern.devtools.depanalysis.repomodel.impl;

import cern.devtools.depanalysis.repomodel.RClass;
import cern.devtools.depanalysis.repomodel.RMethod;
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
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RMethod</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RMethodImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RMethodImpl#getParameterTypes <em>Parameter Types</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RMethodImpl#getClass_ <em>Class</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RMethodImpl#getReferencedFields <em>Referenced Fields</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RMethodImpl#getReferencedMethods <em>Referenced Methods</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RMethodImpl#getSignature <em>Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RMethodImpl extends RStructuralImpl implements RMethod {
	/**
	 * The default value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected static final String RETURN_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected String returnType = RETURN_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameterTypes() <em>Parameter Types</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> parameterTypes;

	/**
	 * The cached value of the '{@link #getReferencedFields() <em>Referenced Fields</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedFields()
	 * @generated
	 * @ordered
	 */
	protected EList<String> referencedFields;

	/**
	 * The cached value of the '{@link #getReferencedMethods() <em>Referenced Methods</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedMethods()
	 * @generated
	 * @ordered
	 */
	protected EList<String> referencedMethods;

	/**
	 * The default value of the '{@link #getSignature() <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignature()
	 * @generated
	 * @ordered
	 */
	protected static final String SIGNATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSignature() <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignature()
	 * @generated
	 * @ordered
	 */
	protected String signature = SIGNATURE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RMethodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RepomodelPackage.Literals.RMETHOD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReturnType() {
		return returnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnType(String newReturnType) {
		String oldReturnType = returnType;
		returnType = newReturnType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RMETHOD__RETURN_TYPE, oldReturnType, returnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getParameterTypes() {
		if (parameterTypes == null) {
			parameterTypes = new EDataTypeUniqueEList<String>(String.class, this, RepomodelPackage.RMETHOD__PARAMETER_TYPES);
		}
		return parameterTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RClass getClass_() {
		if (eContainerFeatureID() != RepomodelPackage.RMETHOD__CLASS) return null;
		return (RClass)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RClass basicGetClass() {
		if (eContainerFeatureID() != RepomodelPackage.RMETHOD__CLASS) return null;
		return (RClass)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClass(RClass newClass, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newClass, RepomodelPackage.RMETHOD__CLASS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClass(RClass newClass) {
		if (newClass != eInternalContainer() || (eContainerFeatureID() != RepomodelPackage.RMETHOD__CLASS && newClass != null)) {
			if (EcoreUtil.isAncestor(this, newClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newClass != null)
				msgs = ((InternalEObject)newClass).eInverseAdd(this, RepomodelPackage.RCLASS__METHODS, RClass.class, msgs);
			msgs = basicSetClass(newClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RMETHOD__CLASS, newClass, newClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getReferencedFields() {
		if (referencedFields == null) {
			referencedFields = new EDataTypeUniqueEList<String>(String.class, this, RepomodelPackage.RMETHOD__REFERENCED_FIELDS);
		}
		return referencedFields;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getReferencedMethods() {
		if (referencedMethods == null) {
			referencedMethods = new EDataTypeUniqueEList<String>(String.class, this, RepomodelPackage.RMETHOD__REFERENCED_METHODS);
		}
		return referencedMethods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignature(String newSignature) {
		String oldSignature = signature;
		signature = newSignature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RMETHOD__SIGNATURE, oldSignature, signature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RepomodelPackage.RMETHOD__CLASS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetClass((RClass)otherEnd, msgs);
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
			case RepomodelPackage.RMETHOD__CLASS:
				return basicSetClass(null, msgs);
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
			case RepomodelPackage.RMETHOD__CLASS:
				return eInternalContainer().eInverseRemove(this, RepomodelPackage.RCLASS__METHODS, RClass.class, msgs);
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
			case RepomodelPackage.RMETHOD__RETURN_TYPE:
				return getReturnType();
			case RepomodelPackage.RMETHOD__PARAMETER_TYPES:
				return getParameterTypes();
			case RepomodelPackage.RMETHOD__CLASS:
				if (resolve) return getClass_();
				return basicGetClass();
			case RepomodelPackage.RMETHOD__REFERENCED_FIELDS:
				return getReferencedFields();
			case RepomodelPackage.RMETHOD__REFERENCED_METHODS:
				return getReferencedMethods();
			case RepomodelPackage.RMETHOD__SIGNATURE:
				return getSignature();
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
			case RepomodelPackage.RMETHOD__RETURN_TYPE:
				setReturnType((String)newValue);
				return;
			case RepomodelPackage.RMETHOD__PARAMETER_TYPES:
				getParameterTypes().clear();
				getParameterTypes().addAll((Collection<? extends String>)newValue);
				return;
			case RepomodelPackage.RMETHOD__CLASS:
				setClass((RClass)newValue);
				return;
			case RepomodelPackage.RMETHOD__REFERENCED_FIELDS:
				getReferencedFields().clear();
				getReferencedFields().addAll((Collection<? extends String>)newValue);
				return;
			case RepomodelPackage.RMETHOD__REFERENCED_METHODS:
				getReferencedMethods().clear();
				getReferencedMethods().addAll((Collection<? extends String>)newValue);
				return;
			case RepomodelPackage.RMETHOD__SIGNATURE:
				setSignature((String)newValue);
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
			case RepomodelPackage.RMETHOD__RETURN_TYPE:
				setReturnType(RETURN_TYPE_EDEFAULT);
				return;
			case RepomodelPackage.RMETHOD__PARAMETER_TYPES:
				getParameterTypes().clear();
				return;
			case RepomodelPackage.RMETHOD__CLASS:
				setClass((RClass)null);
				return;
			case RepomodelPackage.RMETHOD__REFERENCED_FIELDS:
				getReferencedFields().clear();
				return;
			case RepomodelPackage.RMETHOD__REFERENCED_METHODS:
				getReferencedMethods().clear();
				return;
			case RepomodelPackage.RMETHOD__SIGNATURE:
				setSignature(SIGNATURE_EDEFAULT);
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
			case RepomodelPackage.RMETHOD__RETURN_TYPE:
				return RETURN_TYPE_EDEFAULT == null ? returnType != null : !RETURN_TYPE_EDEFAULT.equals(returnType);
			case RepomodelPackage.RMETHOD__PARAMETER_TYPES:
				return parameterTypes != null && !parameterTypes.isEmpty();
			case RepomodelPackage.RMETHOD__CLASS:
				return basicGetClass() != null;
			case RepomodelPackage.RMETHOD__REFERENCED_FIELDS:
				return referencedFields != null && !referencedFields.isEmpty();
			case RepomodelPackage.RMETHOD__REFERENCED_METHODS:
				return referencedMethods != null && !referencedMethods.isEmpty();
			case RepomodelPackage.RMETHOD__SIGNATURE:
				return SIGNATURE_EDEFAULT == null ? signature != null : !SIGNATURE_EDEFAULT.equals(signature);
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
		result.append(" (returnType: ");
		result.append(returnType);
		result.append(", parameterTypes: ");
		result.append(parameterTypes);
		result.append(", referencedFields: ");
		result.append(referencedFields);
		result.append(", referencedMethods: ");
		result.append(referencedMethods);
		result.append(", signature: ");
		result.append(signature);
		result.append(')');
		return result.toString();
	}

} //RMethodImpl
