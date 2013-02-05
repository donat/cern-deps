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
package cern.devtools.depanalysis.repomodel;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import cern.devtools.depanalysis.domain.ApiClass;
import cern.devtools.depanalysis.domain.Field;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RField</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RField#getSignature <em>Signature</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RField#getRClass <em>RClass</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RField#isPrivate <em>Private</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRField()
 * @model kind="class"
 * @generated
 */
@SuppressWarnings("all")
public class RField extends RCodeElement implements Field {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RField() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return RepomodelPackage.Literals.RFIELD;
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
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRField_Signature()
	 * @model
	 * @generated
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RField#getSignature <em>Signature</em>}' attribute.
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
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RFIELD__SIGNATURE, oldSignature, signature));
	}

	/**
	 * Returns the value of the '<em><b>RClass</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RClass#getRFields <em>RFields</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RClass</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RClass</em>' container reference.
	 * @see #setRClass(RClass)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRField_RClass()
	 * @see cern.devtools.depanalysis.repomodel.RClass#getRFields
	 * @model opposite="rFields" required="true" transient="false"
	 * @generated
	 */
	public RClass getRClass() {
		if (eContainerFeatureID() != RepomodelPackage.RFIELD__RCLASS) return null;
		return (RClass)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRClass(RClass newRClass, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRClass, RepomodelPackage.RFIELD__RCLASS, msgs);
		return msgs;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RField#getRClass <em>RClass</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RClass</em>' container reference.
	 * @see #getRClass()
	 * @generated
	 */
	public void setRClass(RClass newRClass) {
		if (newRClass != eInternalContainer() || (eContainerFeatureID() != RepomodelPackage.RFIELD__RCLASS && newRClass != null)) {
			if (EcoreUtil.isAncestor(this, newRClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRClass != null)
				msgs = ((InternalEObject)newRClass).eInverseAdd(this, RepomodelPackage.RCLASS__RFIELDS, RClass.class, msgs);
			msgs = basicSetRClass(newRClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RFIELD__RCLASS, newRClass, newRClass));
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
	 * @see #setPrivate(boolean)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRField_Private()
	 * @model
	 * @generated
	 */
	public boolean isPrivate() {
		return private_;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RField#isPrivate <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Private</em>' attribute.
	 * @see #isPrivate()
	 * @generated
	 */
	public void setPrivate(boolean newPrivate) {
		boolean oldPrivate = private_;
		private_ = newPrivate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RFIELD__PRIVATE, oldPrivate, private_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RepomodelPackage.RFIELD__RCLASS:
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
			case RepomodelPackage.RFIELD__RCLASS:
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
			case RepomodelPackage.RFIELD__RCLASS:
				return eInternalContainer().eInverseRemove(this, RepomodelPackage.RCLASS__RFIELDS, RClass.class, msgs);
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
			case RepomodelPackage.RFIELD__SIGNATURE:
				return getSignature();
			case RepomodelPackage.RFIELD__RCLASS:
				return getRClass();
			case RepomodelPackage.RFIELD__PRIVATE:
				return isPrivate() ? Boolean.TRUE : Boolean.FALSE;
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
			case RepomodelPackage.RFIELD__SIGNATURE:
				setSignature((String)newValue);
				return;
			case RepomodelPackage.RFIELD__RCLASS:
				setRClass((RClass)newValue);
				return;
			case RepomodelPackage.RFIELD__PRIVATE:
				setPrivate(((Boolean)newValue).booleanValue());
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
			case RepomodelPackage.RFIELD__SIGNATURE:
				setSignature(SIGNATURE_EDEFAULT);
				return;
			case RepomodelPackage.RFIELD__RCLASS:
				setRClass((RClass)null);
				return;
			case RepomodelPackage.RFIELD__PRIVATE:
				setPrivate(PRIVATE_EDEFAULT);
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
			case RepomodelPackage.RFIELD__SIGNATURE:
				return SIGNATURE_EDEFAULT == null ? signature != null : !SIGNATURE_EDEFAULT.equals(signature);
			case RepomodelPackage.RFIELD__RCLASS:
				return getRClass() != null;
			case RepomodelPackage.RFIELD__PRIVATE:
				return private_ != PRIVATE_EDEFAULT;
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
		result.append(')');
		return result.toString();
	}

	@Override
	public ApiClass getApiClass() {
		return getRClass();
	}

	@Override
	public void setApiClass(ApiClass apiClass) {
		setRClass((RClass) apiClass);
	}

} // RField
