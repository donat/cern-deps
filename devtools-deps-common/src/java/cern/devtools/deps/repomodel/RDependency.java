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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.DependencyType;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>RDependency</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link cern.devtools.deps.repomodel.RDependency#getRFrom <em>RFrom</em>}</li>
 * <li>{@link cern.devtools.deps.repomodel.RDependency#getRTo <em>RTo</em>}</li>
 * <li>{@link cern.devtools.deps.repomodel.RDependency#getDepType <em>Dep Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRDependency()
 * @model kind="class"
 * @generated
 */
public class RDependency extends RRepositoryItem implements Dependency {
	/**
	 * The cached value of the '{@link #getRFrom() <em>RFrom</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getRFrom()
	 * @generated
	 * @ordered
	 */
	protected RCodeElement rFrom;

	/**
	 * The cached value of the '{@link #getRTo() <em>RTo</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRTo()
	 * @generated
	 * @ordered
	 */
	protected RCodeElement rTo;

	/**
	 * The default value of the '{@link #getDepType() <em>Dep Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDepType()
	 * @generated
	 * @ordered
	 */
	protected static final int DEP_TYPE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDepType() <em>Dep Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDepType()
	 * @generated
	 * @ordered
	 */
	protected int depType = DEP_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RDependency() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return RepomodelPackage.Literals.RDEPENDENCY;
	}

	/**
	 * Returns the value of the '<em><b>RFrom</b></em>' reference. It is bidirectional and its opposite is '
	 * {@link cern.devtools.deps.repomodel.RCodeElement#getROutgoing <em>ROutgoing</em>}'. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>RFrom</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>RFrom</em>' reference.
	 * @see #setRFrom(RCodeElement)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRDependency_RFrom()
	 * @see cern.devtools.deps.repomodel.RCodeElement#getROutgoing
	 * @model opposite="rOutgoing" required="true"
	 * @generated
	 */
	public RCodeElement getRFrom() {
		if (rFrom != null && rFrom.eIsProxy()) {
			InternalEObject oldRFrom = (InternalEObject) rFrom;
			rFrom = (RCodeElement) eResolveProxy(oldRFrom);
			if (rFrom != oldRFrom) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RepomodelPackage.RDEPENDENCY__RFROM,
							oldRFrom, rFrom));
			}
		}
		return rFrom;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RCodeElement basicGetRFrom() {
		return rFrom;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetRFrom(RCodeElement newRFrom, NotificationChain msgs) {
		RCodeElement oldRFrom = rFrom;
		rFrom = newRFrom;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					RepomodelPackage.RDEPENDENCY__RFROM, oldRFrom, newRFrom);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RDependency#getRFrom <em>RFrom</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>RFrom</em>' reference.
	 * @see #getRFrom()
	 * @generated
	 */
	public void setRFrom(RCodeElement newRFrom) {
		if (newRFrom != rFrom) {
			NotificationChain msgs = null;
			if (rFrom != null)
				msgs = ((InternalEObject) rFrom).eInverseRemove(this, RepomodelPackage.RCODE_ELEMENT__ROUTGOING,
						RCodeElement.class, msgs);
			if (newRFrom != null)
				msgs = ((InternalEObject) newRFrom).eInverseAdd(this, RepomodelPackage.RCODE_ELEMENT__ROUTGOING,
						RCodeElement.class, msgs);
			msgs = basicSetRFrom(newRFrom, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RDEPENDENCY__RFROM, newRFrom,
					newRFrom));
	}

	/**
	 * Returns the value of the '<em><b>RTo</b></em>' reference. It is bidirectional and its opposite is '
	 * {@link cern.devtools.deps.repomodel.RCodeElement#getRIncoming <em>RIncoming</em>}'. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>RTo</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>RTo</em>' reference.
	 * @see #setRTo(RCodeElement)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRDependency_RTo()
	 * @see cern.devtools.deps.repomodel.RCodeElement#getRIncoming
	 * @model opposite="rIncoming" required="true"
	 * @generated
	 */
	public RCodeElement getRTo() {
		if (rTo != null && rTo.eIsProxy()) {
			InternalEObject oldRTo = (InternalEObject) rTo;
			rTo = (RCodeElement) eResolveProxy(oldRTo);
			if (rTo != oldRTo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RepomodelPackage.RDEPENDENCY__RTO,
							oldRTo, rTo));
			}
		}
		return rTo;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RCodeElement basicGetRTo() {
		return rTo;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetRTo(RCodeElement newRTo, NotificationChain msgs) {
		RCodeElement oldRTo = rTo;
		rTo = newRTo;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					RepomodelPackage.RDEPENDENCY__RTO, oldRTo, newRTo);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RDependency#getRTo <em>RTo</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>RTo</em>' reference.
	 * @see #getRTo()
	 * @generated
	 */
	public void setRTo(RCodeElement newRTo) {
		if (newRTo != rTo) {
			NotificationChain msgs = null;
			if (rTo != null)
				msgs = ((InternalEObject) rTo).eInverseRemove(this, RepomodelPackage.RCODE_ELEMENT__RINCOMING,
						RCodeElement.class, msgs);
			if (newRTo != null)
				msgs = ((InternalEObject) newRTo).eInverseAdd(this, RepomodelPackage.RCODE_ELEMENT__RINCOMING,
						RCodeElement.class, msgs);
			msgs = basicSetRTo(newRTo, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RDEPENDENCY__RTO, newRTo, newRTo));
	}

	/**
	 * Returns the value of the '<em><b>Dep Type</b></em>' attribute. The default value is <code>"0"</code>. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dep Type</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Dep Type</em>' attribute.
	 * @see #setDepType(int)
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRDependency_DepType()
	 * @model default="0"
	 * @generated
	 */
	public int getDepType() {
		return depType;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.deps.repomodel.RDependency#getDepType <em>Dep Type</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Dep Type</em>' attribute.
	 * @see #getDepType()
	 * @generated
	 */
	public void setDepType(int newDepType) {
		int oldDepType = depType;
		depType = newDepType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RDEPENDENCY__DEP_TYPE, oldDepType,
					depType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case RepomodelPackage.RDEPENDENCY__RFROM:
			if (rFrom != null)
				msgs = ((InternalEObject) rFrom).eInverseRemove(this, RepomodelPackage.RCODE_ELEMENT__ROUTGOING,
						RCodeElement.class, msgs);
			return basicSetRFrom((RCodeElement) otherEnd, msgs);
		case RepomodelPackage.RDEPENDENCY__RTO:
			if (rTo != null)
				msgs = ((InternalEObject) rTo).eInverseRemove(this, RepomodelPackage.RCODE_ELEMENT__RINCOMING,
						RCodeElement.class, msgs);
			return basicSetRTo((RCodeElement) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case RepomodelPackage.RDEPENDENCY__RFROM:
			return basicSetRFrom(null, msgs);
		case RepomodelPackage.RDEPENDENCY__RTO:
			return basicSetRTo(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case RepomodelPackage.RDEPENDENCY__RFROM:
			if (resolve)
				return getRFrom();
			return basicGetRFrom();
		case RepomodelPackage.RDEPENDENCY__RTO:
			if (resolve)
				return getRTo();
			return basicGetRTo();
		case RepomodelPackage.RDEPENDENCY__DEP_TYPE:
			return new Integer(getDepType());
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case RepomodelPackage.RDEPENDENCY__RFROM:
			setRFrom((RCodeElement) newValue);
			return;
		case RepomodelPackage.RDEPENDENCY__RTO:
			setRTo((RCodeElement) newValue);
			return;
		case RepomodelPackage.RDEPENDENCY__DEP_TYPE:
			setDepType(((Integer) newValue).intValue());
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
		case RepomodelPackage.RDEPENDENCY__RFROM:
			setRFrom((RCodeElement) null);
			return;
		case RepomodelPackage.RDEPENDENCY__RTO:
			setRTo((RCodeElement) null);
			return;
		case RepomodelPackage.RDEPENDENCY__DEP_TYPE:
			setDepType(DEP_TYPE_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case RepomodelPackage.RDEPENDENCY__RFROM:
			return rFrom != null;
		case RepomodelPackage.RDEPENDENCY__RTO:
			return rTo != null;
		case RepomodelPackage.RDEPENDENCY__DEP_TYPE:
			return depType != DEP_TYPE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (depType: ");
		result.append(depType);
		result.append(')');
		return result.toString();
	}

	@Override
	public DependencyType getType() {
		return DependencyType.typeOf(getDepType());
	}

	@Override
	public CodeElement getFrom() {
		return getRFrom();
	}

	@Override
	public CodeElement getTo() {
		return getRTo();
	}

} // RDependency
