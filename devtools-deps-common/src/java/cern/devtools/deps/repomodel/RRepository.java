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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RRepository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.deps.repomodel.RRepository#getRDependencies <em>RDependencies</em>}</li>
 *   <li>{@link cern.devtools.deps.repomodel.RRepository#getRProjects <em>RProjects</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRRepository()
 * @model kind="class"
 * @generated
 */
@SuppressWarnings("all")
public class RRepository extends EObjectImpl implements EObject {
	/**
	 * The cached value of the '{@link #getRDependencies() <em>RDependencies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList rDependencies;

	/**
	 * The cached value of the '{@link #getRProjects() <em>RProjects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRProjects()
	 * @generated
	 * @ordered
	 */
	protected EList rProjects;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RRepository() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return RepomodelPackage.Literals.RREPOSITORY;
	}

	/**
	 * Returns the value of the '<em><b>RDependencies</b></em>' containment reference list.
	 * The list contents are of type {@link cern.devtools.deps.repomodel.RDependency}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RDependencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RDependencies</em>' containment reference list.
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRRepository_RDependencies()
	 * @model type="cern.devtools.depanalysis.repomodel.RDependency" containment="true"
	 * @generated
	 */
	public EList getRDependencies() {
		if (rDependencies == null) {
			rDependencies = new EObjectContainmentEList(RDependency.class, this, RepomodelPackage.RREPOSITORY__RDEPENDENCIES);
		}
		return rDependencies;
	}

	/**
	 * Returns the value of the '<em><b>RProjects</b></em>' containment reference list.
	 * The list contents are of type {@link cern.devtools.deps.repomodel.RProject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RProjects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RProjects</em>' containment reference list.
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRRepository_RProjects()
	 * @model type="cern.devtools.depanalysis.repomodel.RProject" containment="true"
	 * @generated
	 */
	public EList getRProjects() {
		if (rProjects == null) {
			rProjects = new EObjectContainmentEList(RProject.class, this, RepomodelPackage.RREPOSITORY__RPROJECTS);
		}
		return rProjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RepomodelPackage.RREPOSITORY__RDEPENDENCIES:
				return ((InternalEList)getRDependencies()).basicRemove(otherEnd, msgs);
			case RepomodelPackage.RREPOSITORY__RPROJECTS:
				return ((InternalEList)getRProjects()).basicRemove(otherEnd, msgs);
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
			case RepomodelPackage.RREPOSITORY__RDEPENDENCIES:
				return getRDependencies();
			case RepomodelPackage.RREPOSITORY__RPROJECTS:
				return getRProjects();
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
			case RepomodelPackage.RREPOSITORY__RDEPENDENCIES:
				getRDependencies().clear();
				getRDependencies().addAll((Collection)newValue);
				return;
			case RepomodelPackage.RREPOSITORY__RPROJECTS:
				getRProjects().clear();
				getRProjects().addAll((Collection)newValue);
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
			case RepomodelPackage.RREPOSITORY__RDEPENDENCIES:
				getRDependencies().clear();
				return;
			case RepomodelPackage.RREPOSITORY__RPROJECTS:
				getRProjects().clear();
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
			case RepomodelPackage.RREPOSITORY__RDEPENDENCIES:
				return rDependencies != null && !rDependencies.isEmpty();
			case RepomodelPackage.RREPOSITORY__RPROJECTS:
				return rProjects != null && !rProjects.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // RRepository
