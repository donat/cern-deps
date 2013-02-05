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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import cern.devtools.depanalysis.domain.CodeElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RCode Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RCodeElement#getRIncoming <em>RIncoming</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RCodeElement#getROutgoing <em>ROutgoing</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RCodeElement#getVersions <em>Versions</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RCodeElement#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRCodeElement()
 * @model kind="class" abstract="true"
 * @generated
 */
@SuppressWarnings("all")
public abstract class RCodeElement extends RRepositoryItem implements CodeElement {
	/**
	 * The cached value of the '{@link #getRIncoming() <em>RIncoming</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRIncoming()
	 * @generated
	 * @ordered
	 */
	protected EList rIncoming;

	/**
	 * The cached value of the '{@link #getROutgoing() <em>ROutgoing</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getROutgoing()
	 * @generated
	 * @ordered
	 */
	protected EList rOutgoing;

	/**
	 * The cached value of the '{@link #getVersions() <em>Versions</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersions()
	 * @generated
	 * @ordered
	 */
	protected EList versions;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final long ID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected long id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RCodeElement() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return RepomodelPackage.Literals.RCODE_ELEMENT;
	}

	/**
	 * Returns the value of the '<em><b>RIncoming</b></em>' reference list.
	 * The list contents are of type {@link cern.devtools.depanalysis.repomodel.RDependency}.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RDependency#getRTo <em>RTo</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RIncoming</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RIncoming</em>' reference list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRCodeElement_RIncoming()
	 * @see cern.devtools.depanalysis.repomodel.RDependency#getRTo
	 * @model type="cern.devtools.depanalysis.repomodel.RDependency" opposite="rTo"
	 * @generated
	 */
	public EList getRIncoming() {
		if (rIncoming == null) {
			rIncoming = new EObjectWithInverseResolvingEList(RDependency.class, this, RepomodelPackage.RCODE_ELEMENT__RINCOMING, RepomodelPackage.RDEPENDENCY__RTO);
		}
		return rIncoming;
	}

	/**
	 * Returns the value of the '<em><b>ROutgoing</b></em>' reference list.
	 * The list contents are of type {@link cern.devtools.depanalysis.repomodel.RDependency}.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RDependency#getRFrom <em>RFrom</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ROutgoing</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ROutgoing</em>' reference list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRCodeElement_ROutgoing()
	 * @see cern.devtools.depanalysis.repomodel.RDependency#getRFrom
	 * @model type="cern.devtools.depanalysis.repomodel.RDependency" opposite="rFrom"
	 * @generated
	 */
	public EList getROutgoing() {
		if (rOutgoing == null) {
			rOutgoing = new EObjectWithInverseResolvingEList(RDependency.class, this, RepomodelPackage.RCODE_ELEMENT__ROUTGOING, RepomodelPackage.RDEPENDENCY__RFROM);
		}
		return rOutgoing;
	}

	/**
	 * Returns the value of the '<em><b>Versions</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Versions</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Versions</em>' attribute list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRCodeElement_Versions()
	 * @model
	 * @generated
	 */
	public EList getVersions() {
		if (versions == null) {
			versions = new EDataTypeUniqueEList(String.class, this, RepomodelPackage.RCODE_ELEMENT__VERSIONS);
		}
		return versions;
	}

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(long)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRCodeElement_Id()
	 * @model
	 * @generated
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RCodeElement#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	public void setId(long newId) {
		long oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RCODE_ELEMENT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	public String getDisplayName() {
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
			case RepomodelPackage.RCODE_ELEMENT__RINCOMING:
				return ((InternalEList)getRIncoming()).basicAdd(otherEnd, msgs);
			case RepomodelPackage.RCODE_ELEMENT__ROUTGOING:
				return ((InternalEList)getROutgoing()).basicAdd(otherEnd, msgs);
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
			case RepomodelPackage.RCODE_ELEMENT__RINCOMING:
				return ((InternalEList)getRIncoming()).basicRemove(otherEnd, msgs);
			case RepomodelPackage.RCODE_ELEMENT__ROUTGOING:
				return ((InternalEList)getROutgoing()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RepomodelPackage.RCODE_ELEMENT__RINCOMING:
				return getRIncoming();
			case RepomodelPackage.RCODE_ELEMENT__ROUTGOING:
				return getROutgoing();
			case RepomodelPackage.RCODE_ELEMENT__VERSIONS:
				return getVersions();
			case RepomodelPackage.RCODE_ELEMENT__ID:
				return new Long(getId());
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
			case RepomodelPackage.RCODE_ELEMENT__RINCOMING:
				getRIncoming().clear();
				getRIncoming().addAll((Collection)newValue);
				return;
			case RepomodelPackage.RCODE_ELEMENT__ROUTGOING:
				getROutgoing().clear();
				getROutgoing().addAll((Collection)newValue);
				return;
			case RepomodelPackage.RCODE_ELEMENT__VERSIONS:
				getVersions().clear();
				getVersions().addAll((Collection)newValue);
				return;
			case RepomodelPackage.RCODE_ELEMENT__ID:
				setId(((Long)newValue).longValue());
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
			case RepomodelPackage.RCODE_ELEMENT__RINCOMING:
				getRIncoming().clear();
				return;
			case RepomodelPackage.RCODE_ELEMENT__ROUTGOING:
				getROutgoing().clear();
				return;
			case RepomodelPackage.RCODE_ELEMENT__VERSIONS:
				getVersions().clear();
				return;
			case RepomodelPackage.RCODE_ELEMENT__ID:
				setId(ID_EDEFAULT);
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
			case RepomodelPackage.RCODE_ELEMENT__RINCOMING:
				return rIncoming != null && !rIncoming.isEmpty();
			case RepomodelPackage.RCODE_ELEMENT__ROUTGOING:
				return rOutgoing != null && !rOutgoing.isEmpty();
			case RepomodelPackage.RCODE_ELEMENT__VERSIONS:
				return versions != null && !versions.isEmpty();
			case RepomodelPackage.RCODE_ELEMENT__ID:
				return id != ID_EDEFAULT;
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
		result.append(" (versions: ");
		result.append(versions);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}
	
	/**
	 * @generated NOT
	 */
	public void addVersions(String... versions) {
		for (String v : versions) {
			getVersions().add(v);
		}
		
	}

} // RCodeElement
