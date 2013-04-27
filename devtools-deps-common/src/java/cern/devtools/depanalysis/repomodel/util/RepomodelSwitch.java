/**
 */
package cern.devtools.depanalysis.repomodel.util;

import cern.devtools.depanalysis.repomodel.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage
 * @generated
 */
public class RepomodelSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static RepomodelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RepomodelSwitch() {
		if (modelPackage == null) {
			modelPackage = RepomodelPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case RepomodelPackage.RCOMPONENT: {
				RComponent rComponent = (RComponent)theEObject;
				T result = caseRComponent(rComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RVERSIONED: {
				RVersioned rVersioned = (RVersioned)theEObject;
				T result = caseRVersioned(rVersioned);
				if (result == null) result = caseRComponent(rVersioned);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RSTRUCTURAL: {
				RStructural rStructural = (RStructural)theEObject;
				T result = caseRStructural(rStructural);
				if (result == null) result = caseRVersioned(rStructural);
				if (result == null) result = caseRComponent(rStructural);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RREPOSITORY: {
				RRepository rRepository = (RRepository)theEObject;
				T result = caseRRepository(rRepository);
				if (result == null) result = caseRComponent(rRepository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RPROJECT: {
				RProject rProject = (RProject)theEObject;
				T result = caseRProject(rProject);
				if (result == null) result = caseRVersioned(rProject);
				if (result == null) result = caseRComponent(rProject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RPACKAGE: {
				RPackage rPackage = (RPackage)theEObject;
				T result = caseRPackage(rPackage);
				if (result == null) result = caseRVersioned(rPackage);
				if (result == null) result = caseRComponent(rPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RCLASS: {
				RClass rClass = (RClass)theEObject;
				T result = caseRClass(rClass);
				if (result == null) result = caseRStructural(rClass);
				if (result == null) result = caseRVersioned(rClass);
				if (result == null) result = caseRComponent(rClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RFIELD: {
				RField rField = (RField)theEObject;
				T result = caseRField(rField);
				if (result == null) result = caseRStructural(rField);
				if (result == null) result = caseRVersioned(rField);
				if (result == null) result = caseRComponent(rField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RMETHOD: {
				RMethod rMethod = (RMethod)theEObject;
				T result = caseRMethod(rMethod);
				if (result == null) result = caseRStructural(rMethod);
				if (result == null) result = caseRVersioned(rMethod);
				if (result == null) result = caseRComponent(rMethod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RDEPENDENCY: {
				RDependency rDependency = (RDependency)theEObject;
				T result = caseRDependency(rDependency);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RComponent</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RComponent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRComponent(RComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RVersioned</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RVersioned</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRVersioned(RVersioned object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RStructural</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RStructural</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRStructural(RStructural object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RRepository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RRepository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRRepository(RRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RProject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RProject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRProject(RProject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RPackage</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RPackage</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRPackage(RPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RClass</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RClass</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRClass(RClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RField</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RField</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRField(RField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RMethod</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RMethod</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRMethod(RMethod object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RDependency</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RDependency</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRDependency(RDependency object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //RepomodelSwitch
