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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see cern.devtools.deps.repomodel.RepomodelPackage
 * @generated
 */
public class RepomodelFactory extends EFactoryImpl {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final RepomodelFactory eINSTANCE = init();

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RepomodelFactory init() {
		try {
			RepomodelFactory theRepomodelFactory = (RepomodelFactory)EPackage.Registry.INSTANCE.getEFactory("http://inf.mit.bme.hu/donat/incquery-deps/repomodel"); 
			if (theRepomodelFactory != null) {
				return theRepomodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RepomodelFactory();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RepomodelFactory() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RepomodelPackage.RPROJECT: return createRProject();
			case RepomodelPackage.RCLASS: return createRClass();
			case RepomodelPackage.RMETHOD: return createRMethod();
			case RepomodelPackage.RFIELD: return createRField();
			case RepomodelPackage.RDEPENDENCY: return createRDependency();
			case RepomodelPackage.RREPOSITORY: return createRRepository();
			case RepomodelPackage.RTRANSITIVE_DEPENDENCY: return createRTransitiveDependency();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RProject createRProject() {
		RProject rProject = new RProject();
		return rProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RClass createRClass() {
		RClass rClass = new RClass();
		return rClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RMethod createRMethod() {
		RMethod rMethod = new RMethod();
		return rMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RField createRField() {
		RField rField = new RField();
		return rField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RDependency createRDependency() {
		RDependency rDependency = new RDependency();
		return rDependency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RRepository createRRepository() {
		RRepository rRepository = new RRepository();
		return rRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RTransitiveDependency createRTransitiveDependency() {
		RTransitiveDependency rTransitiveDependency = new RTransitiveDependency();
		return rTransitiveDependency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RepomodelPackage getRepomodelPackage() {
		return (RepomodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static RepomodelPackage getPackage() {
		return RepomodelPackage.eINSTANCE;
	}

} //RepomodelFactory
