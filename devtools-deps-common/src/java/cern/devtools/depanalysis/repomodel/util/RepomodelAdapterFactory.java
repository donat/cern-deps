/**
 */
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
	@Override
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
	protected RepomodelSwitch<Adapter> modelSwitch =
		new RepomodelSwitch<Adapter>() {
			@Override
			public Adapter caseRComponent(RComponent object) {
				return createRComponentAdapter();
			}
			@Override
			public Adapter caseRVersioned(RVersioned object) {
				return createRVersionedAdapter();
			}
			@Override
			public Adapter caseRStructural(RStructural object) {
				return createRStructuralAdapter();
			}
			@Override
			public Adapter caseRRepository(RRepository object) {
				return createRRepositoryAdapter();
			}
			@Override
			public Adapter caseRProject(RProject object) {
				return createRProjectAdapter();
			}
			@Override
			public Adapter caseRPackage(RPackage object) {
				return createRPackageAdapter();
			}
			@Override
			public Adapter caseRClass(RClass object) {
				return createRClassAdapter();
			}
			@Override
			public Adapter caseRField(RField object) {
				return createRFieldAdapter();
			}
			@Override
			public Adapter caseRMethod(RMethod object) {
				return createRMethodAdapter();
			}
			@Override
			public Adapter caseRDependency(RDependency object) {
				return createRDependencyAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
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
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link cern.devtools.depanalysis.repomodel.RComponent <em>RComponent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.devtools.depanalysis.repomodel.RComponent
	 * @generated
	 */
	public Adapter createRComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cern.devtools.depanalysis.repomodel.RVersioned <em>RVersioned</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.devtools.depanalysis.repomodel.RVersioned
	 * @generated
	 */
	public Adapter createRVersionedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cern.devtools.depanalysis.repomodel.RStructural <em>RStructural</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.devtools.depanalysis.repomodel.RStructural
	 * @generated
	 */
	public Adapter createRStructuralAdapter() {
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
	 * Creates a new adapter for an object of class '{@link cern.devtools.depanalysis.repomodel.RPackage <em>RPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cern.devtools.depanalysis.repomodel.RPackage
	 * @generated
	 */
	public Adapter createRPackageAdapter() {
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
