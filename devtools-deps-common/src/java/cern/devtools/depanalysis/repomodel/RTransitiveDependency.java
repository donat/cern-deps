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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import cern.devtools.depanalysis.domain.Dependency;
import cern.devtools.depanalysis.domain.TransitiveDependency;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RTransitive Dependency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.RTransitiveDependency#getRTtransitiveFrom <em>RTtransitive From</em>}</li>
 * </ul>
 * </p>
 *
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRTransitiveDependency()
 * @model kind="class"
 * @generated
 */
@SuppressWarnings("all")
public class RTransitiveDependency extends RDependency implements TransitiveDependency {
	/**
	 * The cached value of the '{@link #getRTtransitiveFrom() <em>RTtransitive From</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTtransitiveFrom()
	 * @generated
	 * @ordered
	 */
	protected EList rTtransitiveFrom;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RTransitiveDependency() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return RepomodelPackage.Literals.RTRANSITIVE_DEPENDENCY;
	}

	/**
	 * Returns the value of the '<em><b>RTtransitive From</b></em>' reference list.
	 * The list contents are of type {@link cern.devtools.depanalysis.repomodel.RDependency}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RTtransitive From</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RTtransitive From</em>' reference list.
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#getRTransitiveDependency_RTtransitiveFrom()
	 * @model type="cern.devtools.depanalysis.repomodel.RDependency"
	 * @generated
	 */
	public EList getRTtransitiveFrom() {
		if (rTtransitiveFrom == null) {
			rTtransitiveFrom = new EObjectResolvingEList(RDependency.class, this, RepomodelPackage.RTRANSITIVE_DEPENDENCY__RTTRANSITIVE_FROM);
		}
		return rTtransitiveFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RepomodelPackage.RTRANSITIVE_DEPENDENCY__RTTRANSITIVE_FROM:
				return getRTtransitiveFrom();
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
			case RepomodelPackage.RTRANSITIVE_DEPENDENCY__RTTRANSITIVE_FROM:
				getRTtransitiveFrom().clear();
				getRTtransitiveFrom().addAll((Collection)newValue);
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
			case RepomodelPackage.RTRANSITIVE_DEPENDENCY__RTTRANSITIVE_FROM:
				getRTtransitiveFrom().clear();
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
			case RepomodelPackage.RTRANSITIVE_DEPENDENCY__RTTRANSITIVE_FROM:
				return rTtransitiveFrom != null && !rTtransitiveFrom.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	@Override
	public Collection<Dependency> getTransitiveFrom() {
		return getRTtransitiveFrom();
	}

} // RTransitiveDependency
