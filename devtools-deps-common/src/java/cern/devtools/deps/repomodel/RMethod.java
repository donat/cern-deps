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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Method;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RMethod</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.deps.repomodel.RMethod#getSignature <em>Signature</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RMethod#getRClass <em>RClass</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RMethod#getPrivate <em>Private</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RMethod#getReferencedFields <em>Referenced Fields</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RMethod#getReferencedMethods <em>Referenced Methods</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RMethod#isStatic <em>Static</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRMethod()
 * @model kind="class"
 * @generated
 */
 @SuppressWarnings("all")
public class RMethod extends RCodeElement implements Method {
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
	 * The default value of the '{@link #getPrivate() <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivate()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean PRIVATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrivate() <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivate()
	 * @generated
	 * @ordered
	 */
	protected Boolean private_ = PRIVATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getReferencedFields() <em>Referenced Fields</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedFields()
	 * @generated
	 * @ordered
	 */
	protected static final EList REFERENCED_FIELDS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReferencedFields() <em>Referenced Fields</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedFields()
	 * @generated
	 * @ordered
	 */
	protected EList referencedFields = REFERENCED_FIELDS_EDEFAULT;

	/**
	 * The default value of the '{@link #getReferencedMethods() <em>Referenced Methods</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedMethods()
	 * @generated
	 * @ordered
	 */
	protected static final EList REFERENCED_METHODS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReferencedMethods() <em>Referenced Methods</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedMethods()
	 * @generated
	 * @ordered
	 */
	protected EList referencedMethods = REFERENCED_METHODS_EDEFAULT;

	/**
	 * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STATIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected boolean static_ = STATIC_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RMethod() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return RepomodelPackage.Literals.RMETHOD;
	}

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
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRMethod_Signature()
	 * @model
	 * @generated
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RMethod#getSignature <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signature</em>' attribute.
	 * @see #getSignature()
	 * @generated
	 */
	public void setSignature(String newSignature) {
		String oldSignature = signature;
		signature = newSignature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RMETHOD__SIGNATURE, oldSignature, signature));
	}

	/**
	 * Returns the value of the '<em><b>RClass</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link cern.devtools.deps.repomodel.RClass#getRMethods <em>RMethods</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RClass</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RClass</em>' container reference.
	 * @see #setRClass(RClass)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRMethod_RClass()
	 * @see cern.devtools.deps.repomodel.RClass#getRMethods
	 * @model opposite="rMethods" required="true" transient="false"
	 * @generated
	 */
	public RClass getRClass() {
		if (eContainerFeatureID() != RepomodelPackage.RMETHOD__RCLASS) return null;
		return (RClass)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRClass(RClass newRClass, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRClass, RepomodelPackage.RMETHOD__RCLASS, msgs);
		return msgs;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RMethod#getRClass <em>RClass</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RClass</em>' container reference.
	 * @see #getRClass()
	 * @generated
	 */
	public void setRClass(RClass newRClass) {
		if (newRClass != eInternalContainer() || (eContainerFeatureID() != RepomodelPackage.RMETHOD__RCLASS && newRClass != null)) {
			if (EcoreUtil.isAncestor(this, newRClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRClass != null)
				msgs = ((InternalEObject)newRClass).eInverseAdd(this, RepomodelPackage.RCLASS__RMETHODS, RClass.class, msgs);
			msgs = basicSetRClass(newRClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RMETHOD__RCLASS, newRClass, newRClass));
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
	 * @see #setPrivate(Boolean)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRMethod_Private()
	 * @model
	 * @generated
	 */
	public Boolean getPrivate() {
		return private_;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RMethod#getPrivate <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Private</em>' attribute.
	 * @see #getPrivate()
	 * @generated
	 */
	public void setPrivate(Boolean newPrivate) {
		Boolean oldPrivate = private_;
		private_ = newPrivate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RMETHOD__PRIVATE, oldPrivate, private_));
	}

	/**
	 * Returns the value of the '<em><b>Referenced Fields</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Fields</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Fields</em>' attribute.
	 * @see #setReferencedFields(EList)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRMethod_ReferencedFields()
	 * @model many="false" transient="true"
	 * @generated
	 */
	public EList getReferencedFields() {
		return referencedFields;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RMethod#getReferencedFields <em>Referenced Fields</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Fields</em>' attribute.
	 * @see #getReferencedFields()
	 * @generated
	 */
	public void setReferencedFields(EList newReferencedFields) {
		EList oldReferencedFields = referencedFields;
		referencedFields = newReferencedFields;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RMETHOD__REFERENCED_FIELDS, oldReferencedFields, referencedFields));
	}

	/**
	 * Returns the value of the '<em><b>Referenced Methods</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Methods</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Methods</em>' attribute.
	 * @see #setReferencedMethods(EList)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRMethod_ReferencedMethods()
	 * @model many="false" transient="true"
	 * @generated
	 */
	public EList getReferencedMethods() {
		return referencedMethods;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RMethod#getReferencedMethods <em>Referenced Methods</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Methods</em>' attribute.
	 * @see #getReferencedMethods()
	 * @generated
	 */
	public void setReferencedMethods(EList newReferencedMethods) {
		EList oldReferencedMethods = referencedMethods;
		referencedMethods = newReferencedMethods;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RMETHOD__REFERENCED_METHODS, oldReferencedMethods, referencedMethods));
	}

	/**
	 * Returns the value of the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static</em>' attribute.
	 * @see #setStatic(boolean)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRMethod_Static()
	 * @model
	 * @generated
	 */
	public boolean isStatic() {
		return static_;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RMethod#isStatic <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static</em>' attribute.
	 * @see #isStatic()
	 * @generated
	 */
	public void setStatic(boolean newStatic) {
		boolean oldStatic = static_;
		static_ = newStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RMETHOD__STATIC, oldStatic, static_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RepomodelPackage.RMETHOD__RCLASS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRClass((RClass)otherEnd, msgs);
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
			case RepomodelPackage.RMETHOD__RCLASS:
				return basicSetRClass(null, msgs);
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
			case RepomodelPackage.RMETHOD__RCLASS:
				return eInternalContainer().eInverseRemove(this, RepomodelPackage.RCLASS__RMETHODS, RClass.class, msgs);
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
			case RepomodelPackage.RMETHOD__SIGNATURE:
				return getSignature();
			case RepomodelPackage.RMETHOD__RCLASS:
				return getRClass();
			case RepomodelPackage.RMETHOD__PRIVATE:
				return getPrivate();
			case RepomodelPackage.RMETHOD__REFERENCED_FIELDS:
				return getReferencedFields();
			case RepomodelPackage.RMETHOD__REFERENCED_METHODS:
				return getReferencedMethods();
			case RepomodelPackage.RMETHOD__STATIC:
				return isStatic() ? Boolean.TRUE : Boolean.FALSE;
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
			case RepomodelPackage.RMETHOD__SIGNATURE:
				setSignature((String)newValue);
				return;
			case RepomodelPackage.RMETHOD__RCLASS:
				setRClass((RClass)newValue);
				return;
			case RepomodelPackage.RMETHOD__PRIVATE:
				setPrivate((Boolean)newValue);
				return;
			case RepomodelPackage.RMETHOD__REFERENCED_FIELDS:
				setReferencedFields((EList)newValue);
				return;
			case RepomodelPackage.RMETHOD__REFERENCED_METHODS:
				setReferencedMethods((EList)newValue);
				return;
			case RepomodelPackage.RMETHOD__STATIC:
				setStatic(((Boolean)newValue).booleanValue());
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
			case RepomodelPackage.RMETHOD__SIGNATURE:
				setSignature(SIGNATURE_EDEFAULT);
				return;
			case RepomodelPackage.RMETHOD__RCLASS:
				setRClass((RClass)null);
				return;
			case RepomodelPackage.RMETHOD__PRIVATE:
				setPrivate(PRIVATE_EDEFAULT);
				return;
			case RepomodelPackage.RMETHOD__REFERENCED_FIELDS:
				setReferencedFields(REFERENCED_FIELDS_EDEFAULT);
				return;
			case RepomodelPackage.RMETHOD__REFERENCED_METHODS:
				setReferencedMethods(REFERENCED_METHODS_EDEFAULT);
				return;
			case RepomodelPackage.RMETHOD__STATIC:
				setStatic(STATIC_EDEFAULT);
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
			case RepomodelPackage.RMETHOD__SIGNATURE:
				return SIGNATURE_EDEFAULT == null ? signature != null : !SIGNATURE_EDEFAULT.equals(signature);
			case RepomodelPackage.RMETHOD__RCLASS:
				return getRClass() != null;
			case RepomodelPackage.RMETHOD__PRIVATE:
				return PRIVATE_EDEFAULT == null ? private_ != null : !PRIVATE_EDEFAULT.equals(private_);
			case RepomodelPackage.RMETHOD__REFERENCED_FIELDS:
				return REFERENCED_FIELDS_EDEFAULT == null ? referencedFields != null : !REFERENCED_FIELDS_EDEFAULT.equals(referencedFields);
			case RepomodelPackage.RMETHOD__REFERENCED_METHODS:
				return REFERENCED_METHODS_EDEFAULT == null ? referencedMethods != null : !REFERENCED_METHODS_EDEFAULT.equals(referencedMethods);
			case RepomodelPackage.RMETHOD__STATIC:
				return static_ != STATIC_EDEFAULT;
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
		result.append(" (signature: ");
		result.append(signature);
		result.append(", private: ");
		result.append(private_);
		result.append(", referencedFields: ");
		result.append(referencedFields);
		result.append(", referencedMethods: ");
		result.append(referencedMethods);
		result.append(", static: ");
		result.append(static_);
		result.append(')');
		return result.toString();
	}

	@Override
	public ApiClass getApiClass() {
		return getRClass();
	}

	@Override
	public void setApiClass(ApiClass ac) {
		setRClass((RClass)ac);
	}

	@Override
	public boolean isPrivate() {
		return private_;
	}

} // RMethod
