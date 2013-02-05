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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
@SuppressWarnings("all")
public class RepomodelSwitch {
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
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case RepomodelPackage.RREPOSITORY_ITEM: {
				RRepositoryItem rRepositoryItem = (RRepositoryItem)theEObject;
				Object result = caseRRepositoryItem(rRepositoryItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RCODE_ELEMENT: {
				RCodeElement rCodeElement = (RCodeElement)theEObject;
				Object result = caseRCodeElement(rCodeElement);
				if (result == null) result = caseRRepositoryItem(rCodeElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RPROJECT: {
				RProject rProject = (RProject)theEObject;
				Object result = caseRProject(rProject);
				if (result == null) result = caseRCodeElement(rProject);
				if (result == null) result = caseRRepositoryItem(rProject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RCLASS: {
				RClass rClass = (RClass)theEObject;
				Object result = caseRClass(rClass);
				if (result == null) result = caseRCodeElement(rClass);
				if (result == null) result = caseRRepositoryItem(rClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RMETHOD: {
				RMethod rMethod = (RMethod)theEObject;
				Object result = caseRMethod(rMethod);
				if (result == null) result = caseRCodeElement(rMethod);
				if (result == null) result = caseRRepositoryItem(rMethod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RFIELD: {
				RField rField = (RField)theEObject;
				Object result = caseRField(rField);
				if (result == null) result = caseRCodeElement(rField);
				if (result == null) result = caseRRepositoryItem(rField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RDEPENDENCY: {
				RDependency rDependency = (RDependency)theEObject;
				Object result = caseRDependency(rDependency);
				if (result == null) result = caseRRepositoryItem(rDependency);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RREPOSITORY: {
				RRepository rRepository = (RRepository)theEObject;
				Object result = caseRRepository(rRepository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RepomodelPackage.RTRANSITIVE_DEPENDENCY: {
				RTransitiveDependency rTransitiveDependency = (RTransitiveDependency)theEObject;
				Object result = caseRTransitiveDependency(rTransitiveDependency);
				if (result == null) result = caseRDependency(rTransitiveDependency);
				if (result == null) result = caseRRepositoryItem(rTransitiveDependency);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RRepository Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RRepository Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRRepositoryItem(RRepositoryItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RCode Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RCode Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRCodeElement(RCodeElement object) {
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
	public Object caseRProject(RProject object) {
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
	public Object caseRClass(RClass object) {
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
	public Object caseRMethod(RMethod object) {
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
	public Object caseRField(RField object) {
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
	public Object caseRDependency(RDependency object) {
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
	public Object caseRRepository(RRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RTransitive Dependency</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RTransitive Dependency</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRTransitiveDependency(RTransitiveDependency object) {
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
	public Object defaultCase(EObject object) {
		return null;
	}

} //RepomodelSwitch
