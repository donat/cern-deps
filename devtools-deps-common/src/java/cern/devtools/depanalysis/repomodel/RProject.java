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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import cern.devtools.depanalysis.domain.ApiClass;
import cern.devtools.depanalysis.domain.Product;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RProject</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RProject#getName <em>Name</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RProject#getRClasses <em>RClasses</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RProject#getContainingFolders <em>Containing Folders</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RProject#getProductPath <em>Product Path</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RProject#getStoreLocation <em>Store Location</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRProject()
 * @model kind="class"
 * @generated
 */
@SuppressWarnings("all")
public class RProject extends RCodeElement implements Product {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRClasses() <em>RClasses</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRClasses()
	 * @generated
	 * @ordered
	 */
	protected EList rClasses;

	/**
	 * The default value of the '{@link #getContainingFolders() <em>Containing Folders</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainingFolders()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTAINING_FOLDERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContainingFolders() <em>Containing Folders</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainingFolders()
	 * @generated
	 * @ordered
	 */
	protected String containingFolders = CONTAINING_FOLDERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getProductPath() <em>Product Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PRODUCT_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProductPath() <em>Product Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductPath()
	 * @generated
	 * @ordered
	 */
	protected String productPath = PRODUCT_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getStoreLocation() <em>Store Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStoreLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String STORE_LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStoreLocation() <em>Store Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStoreLocation()
	 * @generated
	 * @ordered
	 */
	protected String storeLocation = STORE_LOCATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RProject() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return RepomodelPackage.Literals.RPROJECT;
	}

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRProject_Name()
	 * @model
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RProject#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RPROJECT__NAME, oldName, name));
	}

	/**
	 * Returns the value of the '<em><b>RClasses</b></em>' containment reference list.
	 * The list contents are of type {@link cern.devtools.depanalysis.repomodel.RClass}.
	 * It is bidirectional and its opposite is '{@link cern.devtools.depanalysis.repomodel.RClass#getRProject <em>RProject</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RClasses</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RClasses</em>' containment reference list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRProject_RClasses()
	 * @see cern.devtools.depanalysis.repomodel.RClass#getRProject
	 * @model type="cern.devtools.depanalysis.repomodel.RClass" opposite="rProject" containment="true"
	 * @generated
	 */
	public EList getRClasses() {
		if (rClasses == null) {
			rClasses = new EObjectContainmentWithInverseEList(RClass.class, this, RepomodelPackage.RPROJECT__RCLASSES, RepomodelPackage.RCLASS__RPROJECT);
		}
		return rClasses;
	}

	/**
	 * Returns the value of the '<em><b>Containing Folders</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Folders</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Folders</em>' attribute.
	 * @see #setContainingFolders(String)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRProject_ContainingFolders()
	 * @model
	 * @generated
	 */
	public String getContainingFolders() {
		return containingFolders;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RProject#getContainingFolders <em>Containing Folders</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Folders</em>' attribute.
	 * @see #getContainingFolders()
	 * @generated
	 */
	public void setContainingFolders(String newContainingFolders) {
		String oldContainingFolders = containingFolders;
		containingFolders = newContainingFolders;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RPROJECT__CONTAINING_FOLDERS, oldContainingFolders, containingFolders));
	}

	/**
	 * Returns the value of the '<em><b>Product Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product Path</em>' attribute.
	 * @see #setProductPath(String)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRProject_ProductPath()
	 * @model
	 * @generated
	 */
	public String getProductPath() {
		return productPath;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RProject#getProductPath <em>Product Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product Path</em>' attribute.
	 * @see #getProductPath()
	 * @generated
	 */
	public void setProductPath(String newProductPath) {
		String oldProductPath = productPath;
		productPath = newProductPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RPROJECT__PRODUCT_PATH, oldProductPath, productPath));
	}

	/**
	 * Returns the value of the '<em><b>Store Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Store Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Store Location</em>' attribute.
	 * @see #setStoreLocation(String)
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRProject_StoreLocation()
	 * @model
	 * @generated
	 */
	public String getStoreLocation() {
		return storeLocation;
	}

	/**
	 * Sets the value of the '{@link cern.devtools.depanalysis.repomodel.RProject#getStoreLocation <em>Store Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Store Location</em>' attribute.
	 * @see #getStoreLocation()
	 * @generated
	 */
	public void setStoreLocation(String newStoreLocation) {
		String oldStoreLocation = storeLocation;
		storeLocation = newStoreLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RPROJECT__STORE_LOCATION, oldStoreLocation, storeLocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RepomodelPackage.RPROJECT__RCLASSES:
				return ((InternalEList)getRClasses()).basicAdd(otherEnd, msgs);
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
			case RepomodelPackage.RPROJECT__RCLASSES:
				return ((InternalEList)getRClasses()).basicRemove(otherEnd, msgs);
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
			case RepomodelPackage.RPROJECT__NAME:
				return getName();
			case RepomodelPackage.RPROJECT__RCLASSES:
				return getRClasses();
			case RepomodelPackage.RPROJECT__CONTAINING_FOLDERS:
				return getContainingFolders();
			case RepomodelPackage.RPROJECT__PRODUCT_PATH:
				return getProductPath();
			case RepomodelPackage.RPROJECT__STORE_LOCATION:
				return getStoreLocation();
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
			case RepomodelPackage.RPROJECT__NAME:
				setName((String)newValue);
				return;
			case RepomodelPackage.RPROJECT__RCLASSES:
				getRClasses().clear();
				getRClasses().addAll((Collection)newValue);
				return;
			case RepomodelPackage.RPROJECT__CONTAINING_FOLDERS:
				setContainingFolders((String)newValue);
				return;
			case RepomodelPackage.RPROJECT__PRODUCT_PATH:
				setProductPath((String)newValue);
				return;
			case RepomodelPackage.RPROJECT__STORE_LOCATION:
				setStoreLocation((String)newValue);
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
			case RepomodelPackage.RPROJECT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RepomodelPackage.RPROJECT__RCLASSES:
				getRClasses().clear();
				return;
			case RepomodelPackage.RPROJECT__CONTAINING_FOLDERS:
				setContainingFolders(CONTAINING_FOLDERS_EDEFAULT);
				return;
			case RepomodelPackage.RPROJECT__PRODUCT_PATH:
				setProductPath(PRODUCT_PATH_EDEFAULT);
				return;
			case RepomodelPackage.RPROJECT__STORE_LOCATION:
				setStoreLocation(STORE_LOCATION_EDEFAULT);
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
			case RepomodelPackage.RPROJECT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case RepomodelPackage.RPROJECT__RCLASSES:
				return rClasses != null && !rClasses.isEmpty();
			case RepomodelPackage.RPROJECT__CONTAINING_FOLDERS:
				return CONTAINING_FOLDERS_EDEFAULT == null ? containingFolders != null : !CONTAINING_FOLDERS_EDEFAULT.equals(containingFolders);
			case RepomodelPackage.RPROJECT__PRODUCT_PATH:
				return PRODUCT_PATH_EDEFAULT == null ? productPath != null : !PRODUCT_PATH_EDEFAULT.equals(productPath);
			case RepomodelPackage.RPROJECT__STORE_LOCATION:
				return STORE_LOCATION_EDEFAULT == null ? storeLocation != null : !STORE_LOCATION_EDEFAULT.equals(storeLocation);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", containingFolders: ");
		result.append(containingFolders);
		result.append(", productPath: ");
		result.append(productPath);
		result.append(", storeLocation: ");
		result.append(storeLocation);
		result.append(')');
		return result.toString();
	}

	@Override
	public Collection<ApiClass> getClasses() {
		return getRClasses();
	}

} // RProject
