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
package cern.devtools.depanalysis.repomodel.util;

import cern.devtools.depanalysis.repomodel.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage
 * @generated
 */
public class RepomodelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static RepomodelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RepomodelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = RepomodelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RepomodelSwitch modelSwitch =
		new RepomodelSwitch() {
			public Object caseRRepositoryItem(RRepositoryItem object) {
				return createRRepositoryItemAdapter();
			}
			public Object caseRCodeElement(RCodeElement object) {
				return createRCodeElementAdapter();
			}
			public Object caseRProject(RProject object) {
				return createRProjectAdapter();
			}
			public Object caseRClass(RClass object) {
				return createRClassAdapter();
			}
			public Object caseRMethod(RMethod object) {
				return createRMethodAdapter();
			}
			public Object caseRField(RField object) {
				return createRFieldAdapter();
			}
			public Object caseRDependency(RDependency object) {
				return createRDependencyAdapter();
			}
			public Object caseRRepository(RRepository object) {
				return createRRepositoryAdapter();
			}
			public Object caseRTransitiveDependency(RTransitiveDependency object) {
				return createRTransitiveDependencyAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link cern.devtools.depanalysis.repomodel.RRepositoryItem <em>RRepository Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.devtools.depanalysis.repomodel.RRepositoryItem
	 * @generated
	 */
	public Adapter createRRepositoryItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cern.devtools.depanalysis.repomodel.RCodeElement <em>RCode Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.devtools.depanalysis.repomodel.RCodeElement
	 * @generated
	 */
	public Adapter createRCodeElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cern.devtools.depanalysis.repomodel.RProject <em>RProject</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.devtools.depanalysis.repomodel.RProject
	 * @generated
	 */
	public Adapter createRProjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cern.devtools.depanalysis.repomodel.RClass <em>RClass</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.devtools.depanalysis.repomodel.RClass
	 * @generated
	 */
	public Adapter createRClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cern.devtools.depanalysis.repomodel.RMethod <em>RMethod</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.devtools.depanalysis.repomodel.RMethod
	 * @generated
	 */
	public Adapter createRMethodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cern.devtools.depanalysis.repomodel.RField <em>RField</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.devtools.depanalysis.repomodel.RField
	 * @generated
	 */
	public Adapter createRFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cern.devtools.depanalysis.repomodel.RDependency <em>RDependency</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.devtools.depanalysis.repomodel.RDependency
	 * @generated
	 */
	public Adapter createRDependencyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cern.devtools.depanalysis.repomodel.RRepository <em>RRepository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.devtools.depanalysis.repomodel.RRepository
	 * @generated
	 */
	public Adapter createRRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cern.devtools.depanalysis.repomodel.RTransitiveDependency <em>RTransitive Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.devtools.depanalysis.repomodel.RTransitiveDependency
	 * @generated
	 */
	public Adapter createRTransitiveDependencyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //RepomodelAdapterFactory
