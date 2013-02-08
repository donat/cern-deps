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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see cern.devtools.deps.repomodel.RepomodelFactory
 * @model kind="package"
 * @generated
 */
public class RepomodelPackage extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "repomodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://inf.mit.bme.hu/donat/incquery-deps/repomodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "rm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final RepomodelPackage eINSTANCE = cern.devtools.deps.repomodel.RepomodelPackage.init();

	/**
	 * The meta object id for the '{@link cern.devtools.deps.repomodel.RRepositoryItem <em>RRepository Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.deps.repomodel.RRepositoryItem
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRRepositoryItem()
	 * @generated
	 */
	public static final int RREPOSITORY_ITEM = 0;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RREPOSITORY_ITEM__UUID = 0;

	/**
	 * The number of structural features of the '<em>RRepository Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RREPOSITORY_ITEM_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link cern.devtools.deps.repomodel.RCodeElement <em>RCode Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.deps.repomodel.RCodeElement
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRCodeElement()
	 * @generated
	 */
	public static final int RCODE_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCODE_ELEMENT__UUID = RREPOSITORY_ITEM__UUID;

	/**
	 * The feature id for the '<em><b>RIncoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCODE_ELEMENT__RINCOMING = RREPOSITORY_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>ROutgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCODE_ELEMENT__ROUTGOING = RREPOSITORY_ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCODE_ELEMENT__VERSIONS = RREPOSITORY_ITEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCODE_ELEMENT__ID = RREPOSITORY_ITEM_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>RCode Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCODE_ELEMENT_FEATURE_COUNT = RREPOSITORY_ITEM_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link cern.devtools.deps.repomodel.RProject <em>RProject</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.deps.repomodel.RProject
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRProject()
	 * @generated
	 */
	public static final int RPROJECT = 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RPROJECT__UUID = RCODE_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>RIncoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RPROJECT__RINCOMING = RCODE_ELEMENT__RINCOMING;

	/**
	 * The feature id for the '<em><b>ROutgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RPROJECT__ROUTGOING = RCODE_ELEMENT__ROUTGOING;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RPROJECT__VERSIONS = RCODE_ELEMENT__VERSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RPROJECT__ID = RCODE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RPROJECT__NAME = RCODE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>RClasses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RPROJECT__RCLASSES = RCODE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Containing Folders</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RPROJECT__CONTAINING_FOLDERS = RCODE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Product Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RPROJECT__PRODUCT_PATH = RCODE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Store Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RPROJECT__STORE_LOCATION = RCODE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>RProject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RPROJECT_FEATURE_COUNT = RCODE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link cern.devtools.deps.repomodel.RClass <em>RClass</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.deps.repomodel.RClass
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass()
	 * @generated
	 */
	public static final int RCLASS = 3;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__UUID = RCODE_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>RIncoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__RINCOMING = RCODE_ELEMENT__RINCOMING;

	/**
	 * The feature id for the '<em><b>ROutgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__ROUTGOING = RCODE_ELEMENT__ROUTGOING;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__VERSIONS = RCODE_ELEMENT__VERSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__ID = RCODE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Simple Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__SIMPLE_NAME = RCODE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__PACKAGE_NAME = RCODE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>RMethods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__RMETHODS = RCODE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>RFields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__RFIELDS = RCODE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>RProject</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__RPROJECT = RCODE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__EXTENDS = RCODE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Implements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__IMPLEMENTS = RCODE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Private</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__PRIVATE = RCODE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Anonymous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__ANONYMOUS = RCODE_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Referenced Classes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__REFERENCED_CLASSES = RCODE_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Fq Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS__FQ_NAME = RCODE_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>RClass</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RCLASS_FEATURE_COUNT = RCODE_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link cern.devtools.deps.repomodel.RMethod <em>RMethod</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.deps.repomodel.RMethod
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRMethod()
	 * @generated
	 */
	public static final int RMETHOD = 4;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RMETHOD__UUID = RCODE_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>RIncoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RMETHOD__RINCOMING = RCODE_ELEMENT__RINCOMING;

	/**
	 * The feature id for the '<em><b>ROutgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RMETHOD__ROUTGOING = RCODE_ELEMENT__ROUTGOING;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RMETHOD__VERSIONS = RCODE_ELEMENT__VERSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RMETHOD__ID = RCODE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RMETHOD__SIGNATURE = RCODE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>RClass</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RMETHOD__RCLASS = RCODE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Private</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RMETHOD__PRIVATE = RCODE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Referenced Fields</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RMETHOD__REFERENCED_FIELDS = RCODE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Referenced Methods</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RMETHOD__REFERENCED_METHODS = RCODE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RMETHOD__STATIC = RCODE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>RMethod</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RMETHOD_FEATURE_COUNT = RCODE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link cern.devtools.deps.repomodel.RField <em>RField</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.deps.repomodel.RField
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRField()
	 * @generated
	 */
	public static final int RFIELD = 5;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RFIELD__UUID = RCODE_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>RIncoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RFIELD__RINCOMING = RCODE_ELEMENT__RINCOMING;

	/**
	 * The feature id for the '<em><b>ROutgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RFIELD__ROUTGOING = RCODE_ELEMENT__ROUTGOING;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RFIELD__VERSIONS = RCODE_ELEMENT__VERSIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RFIELD__ID = RCODE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RFIELD__SIGNATURE = RCODE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>RClass</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RFIELD__RCLASS = RCODE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Private</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RFIELD__PRIVATE = RCODE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>RField</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RFIELD_FEATURE_COUNT = RCODE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link cern.devtools.deps.repomodel.RDependency <em>RDependency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.deps.repomodel.RDependency
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRDependency()
	 * @generated
	 */
	public static final int RDEPENDENCY = 6;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RDEPENDENCY__UUID = RREPOSITORY_ITEM__UUID;

	/**
	 * The feature id for the '<em><b>RFrom</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RDEPENDENCY__RFROM = RREPOSITORY_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>RTo</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RDEPENDENCY__RTO = RREPOSITORY_ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Dep Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RDEPENDENCY__DEP_TYPE = RREPOSITORY_ITEM_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>RDependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RDEPENDENCY_FEATURE_COUNT = RREPOSITORY_ITEM_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link cern.devtools.deps.repomodel.RRepository <em>RRepository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.deps.repomodel.RRepository
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRRepository()
	 * @generated
	 */
	public static final int RREPOSITORY = 7;

	/**
	 * The feature id for the '<em><b>RDependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RREPOSITORY__RDEPENDENCIES = 0;

	/**
	 * The feature id for the '<em><b>RProjects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RREPOSITORY__RPROJECTS = 1;

	/**
	 * The number of structural features of the '<em>RRepository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RREPOSITORY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link cern.devtools.deps.repomodel.RTransitiveDependency <em>RTransitive Dependency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.deps.repomodel.RTransitiveDependency
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRTransitiveDependency()
	 * @generated
	 */
	public static final int RTRANSITIVE_DEPENDENCY = 8;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RTRANSITIVE_DEPENDENCY__UUID = RDEPENDENCY__UUID;

	/**
	 * The feature id for the '<em><b>RFrom</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RTRANSITIVE_DEPENDENCY__RFROM = RDEPENDENCY__RFROM;

	/**
	 * The feature id for the '<em><b>RTo</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RTRANSITIVE_DEPENDENCY__RTO = RDEPENDENCY__RTO;

	/**
	 * The feature id for the '<em><b>Dep Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RTRANSITIVE_DEPENDENCY__DEP_TYPE = RDEPENDENCY__DEP_TYPE;

	/**
	 * The feature id for the '<em><b>RTtransitive From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RTRANSITIVE_DEPENDENCY__RTTRANSITIVE_FROM = RDEPENDENCY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>RTransitive Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RTRANSITIVE_DEPENDENCY_FEATURE_COUNT = RDEPENDENCY_FEATURE_COUNT + 1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rRepositoryItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rCodeElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rProjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rMethodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rDependencyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rTransitiveDependencyEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see cern.devtools.deps.repomodel.RepomodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RepomodelPackage() {
		super(eNS_URI, RepomodelFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link RepomodelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RepomodelPackage init() {
		if (isInited) return (RepomodelPackage)EPackage.Registry.INSTANCE.getEPackage(RepomodelPackage.eNS_URI);

		// Obtain or create and register package
		RepomodelPackage theRepomodelPackage = (RepomodelPackage)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RepomodelPackage ? EPackage.Registry.INSTANCE.get(eNS_URI) : new RepomodelPackage());

		isInited = true;

		// Create package meta-data objects
		theRepomodelPackage.createPackageContents();

		// Initialize created meta-data
		theRepomodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRepomodelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RepomodelPackage.eNS_URI, theRepomodelPackage);
		return theRepomodelPackage;
	}


	/**
	 * Returns the meta object for class '{@link cern.devtools.deps.repomodel.RRepositoryItem <em>RRepository Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RRepository Item</em>'.
	 * @see cern.devtools.deps.repomodel.RRepositoryItem
	 * @generated
	 */
	public EClass getRRepositoryItem() {
		return rRepositoryItemEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RRepositoryItem#getUuid <em>Uuid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uuid</em>'.
	 * @see cern.devtools.deps.repomodel.RRepositoryItem#getUuid()
	 * @see #getRRepositoryItem()
	 * @generated
	 */
	public EAttribute getRRepositoryItem_Uuid() {
		return (EAttribute)rRepositoryItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link cern.devtools.deps.repomodel.RCodeElement <em>RCode Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RCode Element</em>'.
	 * @see cern.devtools.deps.repomodel.RCodeElement
	 * @generated
	 */
	public EClass getRCodeElement() {
		return rCodeElementEClass;
	}

	/**
	 * Returns the meta object for the reference list '{@link cern.devtools.deps.repomodel.RCodeElement#getRIncoming <em>RIncoming</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>RIncoming</em>'.
	 * @see cern.devtools.deps.repomodel.RCodeElement#getRIncoming()
	 * @see #getRCodeElement()
	 * @generated
	 */
	public EReference getRCodeElement_RIncoming() {
		return (EReference)rCodeElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the reference list '{@link cern.devtools.deps.repomodel.RCodeElement#getROutgoing <em>ROutgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>ROutgoing</em>'.
	 * @see cern.devtools.deps.repomodel.RCodeElement#getROutgoing()
	 * @see #getRCodeElement()
	 * @generated
	 */
	public EReference getRCodeElement_ROutgoing() {
		return (EReference)rCodeElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the attribute list '{@link cern.devtools.deps.repomodel.RCodeElement#getVersions <em>Versions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Versions</em>'.
	 * @see cern.devtools.deps.repomodel.RCodeElement#getVersions()
	 * @see #getRCodeElement()
	 * @generated
	 */
	public EAttribute getRCodeElement_Versions() {
		return (EAttribute)rCodeElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RCodeElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see cern.devtools.deps.repomodel.RCodeElement#getId()
	 * @see #getRCodeElement()
	 * @generated
	 */
	public EAttribute getRCodeElement_Id() {
		return (EAttribute)rCodeElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for class '{@link cern.devtools.deps.repomodel.RProject <em>RProject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RProject</em>'.
	 * @see cern.devtools.deps.repomodel.RProject
	 * @generated
	 */
	public EClass getRProject() {
		return rProjectEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RProject#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see cern.devtools.deps.repomodel.RProject#getName()
	 * @see #getRProject()
	 * @generated
	 */
	public EAttribute getRProject_Name() {
		return (EAttribute)rProjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link cern.devtools.deps.repomodel.RProject#getRClasses <em>RClasses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>RClasses</em>'.
	 * @see cern.devtools.deps.repomodel.RProject#getRClasses()
	 * @see #getRProject()
	 * @generated
	 */
	public EReference getRProject_RClasses() {
		return (EReference)rProjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RProject#getContainingFolders <em>Containing Folders</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Containing Folders</em>'.
	 * @see cern.devtools.deps.repomodel.RProject#getContainingFolders()
	 * @see #getRProject()
	 * @generated
	 */
	public EAttribute getRProject_ContainingFolders() {
		return (EAttribute)rProjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RProject#getProductPath <em>Product Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product Path</em>'.
	 * @see cern.devtools.deps.repomodel.RProject#getProductPath()
	 * @see #getRProject()
	 * @generated
	 */
	public EAttribute getRProject_ProductPath() {
		return (EAttribute)rProjectEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RProject#getStoreLocation <em>Store Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Store Location</em>'.
	 * @see cern.devtools.deps.repomodel.RProject#getStoreLocation()
	 * @see #getRProject()
	 * @generated
	 */
	public EAttribute getRProject_StoreLocation() {
		return (EAttribute)rProjectEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * Returns the meta object for class '{@link cern.devtools.deps.repomodel.RClass <em>RClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RClass</em>'.
	 * @see cern.devtools.deps.repomodel.RClass
	 * @generated
	 */
	public EClass getRClass() {
		return rClassEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RClass#getSimpleName <em>Simple Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Simple Name</em>'.
	 * @see cern.devtools.deps.repomodel.RClass#getSimpleName()
	 * @see #getRClass()
	 * @generated
	 */
	public EAttribute getRClass_SimpleName() {
		return (EAttribute)rClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RClass#getPackageName <em>Package Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package Name</em>'.
	 * @see cern.devtools.deps.repomodel.RClass#getPackageName()
	 * @see #getRClass()
	 * @generated
	 */
	public EAttribute getRClass_PackageName() {
		return (EAttribute)rClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link cern.devtools.deps.repomodel.RClass#getRMethods <em>RMethods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>RMethods</em>'.
	 * @see cern.devtools.deps.repomodel.RClass#getRMethods()
	 * @see #getRClass()
	 * @generated
	 */
	public EReference getRClass_RMethods() {
		return (EReference)rClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link cern.devtools.deps.repomodel.RClass#getRFields <em>RFields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>RFields</em>'.
	 * @see cern.devtools.deps.repomodel.RClass#getRFields()
	 * @see #getRClass()
	 * @generated
	 */
	public EReference getRClass_RFields() {
		return (EReference)rClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for the container reference '{@link cern.devtools.deps.repomodel.RClass#getRProject <em>RProject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>RProject</em>'.
	 * @see cern.devtools.deps.repomodel.RClass#getRProject()
	 * @see #getRClass()
	 * @generated
	 */
	public EReference getRClass_RProject() {
		return (EReference)rClassEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RClass#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extends</em>'.
	 * @see cern.devtools.deps.repomodel.RClass#getExtends()
	 * @see #getRClass()
	 * @generated
	 */
	public EAttribute getRClass_Extends() {
		return (EAttribute)rClassEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RClass#getImplements <em>Implements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implements</em>'.
	 * @see cern.devtools.deps.repomodel.RClass#getImplements()
	 * @see #getRClass()
	 * @generated
	 */
	public EAttribute getRClass_Implements() {
		return (EAttribute)rClassEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RClass#isPrivate <em>Private</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Private</em>'.
	 * @see cern.devtools.deps.repomodel.RClass#isPrivate()
	 * @see #getRClass()
	 * @generated
	 */
	public EAttribute getRClass_Private() {
		return (EAttribute)rClassEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RClass#isAnonymous <em>Anonymous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Anonymous</em>'.
	 * @see cern.devtools.deps.repomodel.RClass#isAnonymous()
	 * @see #getRClass()
	 * @generated
	 */
	public EAttribute getRClass_Anonymous() {
		return (EAttribute)rClassEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RClass#getReferencedClasses <em>Referenced Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Referenced Classes</em>'.
	 * @see cern.devtools.deps.repomodel.RClass#getReferencedClasses()
	 * @see #getRClass()
	 * @generated
	 */
	public EAttribute getRClass_ReferencedClasses() {
		return (EAttribute)rClassEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RClass#getFqName <em>Fq Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fq Name</em>'.
	 * @see cern.devtools.deps.repomodel.RClass#getFqName()
	 * @see #getRClass()
	 * @generated
	 */
	public EAttribute getRClass_FqName() {
		return (EAttribute)rClassEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * Returns the meta object for class '{@link cern.devtools.deps.repomodel.RMethod <em>RMethod</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RMethod</em>'.
	 * @see cern.devtools.deps.repomodel.RMethod
	 * @generated
	 */
	public EClass getRMethod() {
		return rMethodEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RMethod#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signature</em>'.
	 * @see cern.devtools.deps.repomodel.RMethod#getSignature()
	 * @see #getRMethod()
	 * @generated
	 */
	public EAttribute getRMethod_Signature() {
		return (EAttribute)rMethodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the container reference '{@link cern.devtools.deps.repomodel.RMethod#getRClass <em>RClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>RClass</em>'.
	 * @see cern.devtools.deps.repomodel.RMethod#getRClass()
	 * @see #getRMethod()
	 * @generated
	 */
	public EReference getRMethod_RClass() {
		return (EReference)rMethodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RMethod#getPrivate <em>Private</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Private</em>'.
	 * @see cern.devtools.deps.repomodel.RMethod#getPrivate()
	 * @see #getRMethod()
	 * @generated
	 */
	public EAttribute getRMethod_Private() {
		return (EAttribute)rMethodEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RMethod#getReferencedFields <em>Referenced Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Referenced Fields</em>'.
	 * @see cern.devtools.deps.repomodel.RMethod#getReferencedFields()
	 * @see #getRMethod()
	 * @generated
	 */
	public EAttribute getRMethod_ReferencedFields() {
		return (EAttribute)rMethodEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RMethod#getReferencedMethods <em>Referenced Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Referenced Methods</em>'.
	 * @see cern.devtools.deps.repomodel.RMethod#getReferencedMethods()
	 * @see #getRMethod()
	 * @generated
	 */
	public EAttribute getRMethod_ReferencedMethods() {
		return (EAttribute)rMethodEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RMethod#isStatic <em>Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Static</em>'.
	 * @see cern.devtools.deps.repomodel.RMethod#isStatic()
	 * @see #getRMethod()
	 * @generated
	 */
	public EAttribute getRMethod_Static() {
		return (EAttribute)rMethodEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * Returns the meta object for class '{@link cern.devtools.deps.repomodel.RField <em>RField</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RField</em>'.
	 * @see cern.devtools.deps.repomodel.RField
	 * @generated
	 */
	public EClass getRField() {
		return rFieldEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RField#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signature</em>'.
	 * @see cern.devtools.deps.repomodel.RField#getSignature()
	 * @see #getRField()
	 * @generated
	 */
	public EAttribute getRField_Signature() {
		return (EAttribute)rFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the container reference '{@link cern.devtools.deps.repomodel.RField#getRClass <em>RClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>RClass</em>'.
	 * @see cern.devtools.deps.repomodel.RField#getRClass()
	 * @see #getRField()
	 * @generated
	 */
	public EReference getRField_RClass() {
		return (EReference)rFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RField#isPrivate <em>Private</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Private</em>'.
	 * @see cern.devtools.deps.repomodel.RField#isPrivate()
	 * @see #getRField()
	 * @generated
	 */
	public EAttribute getRField_Private() {
		return (EAttribute)rFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for class '{@link cern.devtools.deps.repomodel.RDependency <em>RDependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RDependency</em>'.
	 * @see cern.devtools.deps.repomodel.RDependency
	 * @generated
	 */
	public EClass getRDependency() {
		return rDependencyEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link cern.devtools.deps.repomodel.RDependency#getRFrom <em>RFrom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>RFrom</em>'.
	 * @see cern.devtools.deps.repomodel.RDependency#getRFrom()
	 * @see #getRDependency()
	 * @generated
	 */
	public EReference getRDependency_RFrom() {
		return (EReference)rDependencyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the reference '{@link cern.devtools.deps.repomodel.RDependency#getRTo <em>RTo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>RTo</em>'.
	 * @see cern.devtools.deps.repomodel.RDependency#getRTo()
	 * @see #getRDependency()
	 * @generated
	 */
	public EReference getRDependency_RTo() {
		return (EReference)rDependencyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.deps.repomodel.RDependency#getDepType <em>Dep Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dep Type</em>'.
	 * @see cern.devtools.deps.repomodel.RDependency#getDepType()
	 * @see #getRDependency()
	 * @generated
	 */
	public EAttribute getRDependency_DepType() {
		return (EAttribute)rDependencyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for class '{@link cern.devtools.deps.repomodel.RRepository <em>RRepository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RRepository</em>'.
	 * @see cern.devtools.deps.repomodel.RRepository
	 * @generated
	 */
	public EClass getRRepository() {
		return rRepositoryEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link cern.devtools.deps.repomodel.RRepository#getRDependencies <em>RDependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>RDependencies</em>'.
	 * @see cern.devtools.deps.repomodel.RRepository#getRDependencies()
	 * @see #getRRepository()
	 * @generated
	 */
	public EReference getRRepository_RDependencies() {
		return (EReference)rRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link cern.devtools.deps.repomodel.RRepository#getRProjects <em>RProjects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>RProjects</em>'.
	 * @see cern.devtools.deps.repomodel.RRepository#getRProjects()
	 * @see #getRRepository()
	 * @generated
	 */
	public EReference getRRepository_RProjects() {
		return (EReference)rRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link cern.devtools.deps.repomodel.RTransitiveDependency <em>RTransitive Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RTransitive Dependency</em>'.
	 * @see cern.devtools.deps.repomodel.RTransitiveDependency
	 * @generated
	 */
	public EClass getRTransitiveDependency() {
		return rTransitiveDependencyEClass;
	}

	/**
	 * Returns the meta object for the reference list '{@link cern.devtools.deps.repomodel.RTransitiveDependency#getRTtransitiveFrom <em>RTtransitive From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>RTtransitive From</em>'.
	 * @see cern.devtools.deps.repomodel.RTransitiveDependency#getRTtransitiveFrom()
	 * @see #getRTransitiveDependency()
	 * @generated
	 */
	public EReference getRTransitiveDependency_RTtransitiveFrom() {
		return (EReference)rTransitiveDependencyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public RepomodelFactory getRepomodelFactory() {
		return (RepomodelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		rRepositoryItemEClass = createEClass(RREPOSITORY_ITEM);
		createEAttribute(rRepositoryItemEClass, RREPOSITORY_ITEM__UUID);

		rCodeElementEClass = createEClass(RCODE_ELEMENT);
		createEReference(rCodeElementEClass, RCODE_ELEMENT__RINCOMING);
		createEReference(rCodeElementEClass, RCODE_ELEMENT__ROUTGOING);
		createEAttribute(rCodeElementEClass, RCODE_ELEMENT__VERSIONS);
		createEAttribute(rCodeElementEClass, RCODE_ELEMENT__ID);

		rProjectEClass = createEClass(RPROJECT);
		createEAttribute(rProjectEClass, RPROJECT__NAME);
		createEReference(rProjectEClass, RPROJECT__RCLASSES);
		createEAttribute(rProjectEClass, RPROJECT__CONTAINING_FOLDERS);
		createEAttribute(rProjectEClass, RPROJECT__PRODUCT_PATH);
		createEAttribute(rProjectEClass, RPROJECT__STORE_LOCATION);

		rClassEClass = createEClass(RCLASS);
		createEAttribute(rClassEClass, RCLASS__SIMPLE_NAME);
		createEAttribute(rClassEClass, RCLASS__PACKAGE_NAME);
		createEReference(rClassEClass, RCLASS__RMETHODS);
		createEReference(rClassEClass, RCLASS__RFIELDS);
		createEReference(rClassEClass, RCLASS__RPROJECT);
		createEAttribute(rClassEClass, RCLASS__EXTENDS);
		createEAttribute(rClassEClass, RCLASS__IMPLEMENTS);
		createEAttribute(rClassEClass, RCLASS__PRIVATE);
		createEAttribute(rClassEClass, RCLASS__ANONYMOUS);
		createEAttribute(rClassEClass, RCLASS__REFERENCED_CLASSES);
		createEAttribute(rClassEClass, RCLASS__FQ_NAME);

		rMethodEClass = createEClass(RMETHOD);
		createEAttribute(rMethodEClass, RMETHOD__SIGNATURE);
		createEReference(rMethodEClass, RMETHOD__RCLASS);
		createEAttribute(rMethodEClass, RMETHOD__PRIVATE);
		createEAttribute(rMethodEClass, RMETHOD__REFERENCED_FIELDS);
		createEAttribute(rMethodEClass, RMETHOD__REFERENCED_METHODS);
		createEAttribute(rMethodEClass, RMETHOD__STATIC);

		rFieldEClass = createEClass(RFIELD);
		createEAttribute(rFieldEClass, RFIELD__SIGNATURE);
		createEReference(rFieldEClass, RFIELD__RCLASS);
		createEAttribute(rFieldEClass, RFIELD__PRIVATE);

		rDependencyEClass = createEClass(RDEPENDENCY);
		createEReference(rDependencyEClass, RDEPENDENCY__RFROM);
		createEReference(rDependencyEClass, RDEPENDENCY__RTO);
		createEAttribute(rDependencyEClass, RDEPENDENCY__DEP_TYPE);

		rRepositoryEClass = createEClass(RREPOSITORY);
		createEReference(rRepositoryEClass, RREPOSITORY__RDEPENDENCIES);
		createEReference(rRepositoryEClass, RREPOSITORY__RPROJECTS);

		rTransitiveDependencyEClass = createEClass(RTRANSITIVE_DEPENDENCY);
		createEReference(rTransitiveDependencyEClass, RTRANSITIVE_DEPENDENCY__RTTRANSITIVE_FROM);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Add supertypes to classes
		rCodeElementEClass.getESuperTypes().add(this.getRRepositoryItem());
		rProjectEClass.getESuperTypes().add(this.getRCodeElement());
		rClassEClass.getESuperTypes().add(this.getRCodeElement());
		rMethodEClass.getESuperTypes().add(this.getRCodeElement());
		rFieldEClass.getESuperTypes().add(this.getRCodeElement());
		rDependencyEClass.getESuperTypes().add(this.getRRepositoryItem());
		rTransitiveDependencyEClass.getESuperTypes().add(this.getRDependency());

		// Initialize classes and features; add operations and parameters
		initEClass(rRepositoryItemEClass, RRepositoryItem.class, "RRepositoryItem", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRRepositoryItem_Uuid(), ecorePackage.getEString(), "uuid", null, 1, 1, RRepositoryItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rCodeElementEClass, RCodeElement.class, "RCodeElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRCodeElement_RIncoming(), this.getRDependency(), this.getRDependency_RTo(), "rIncoming", null, 0, -1, RCodeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRCodeElement_ROutgoing(), this.getRDependency(), this.getRDependency_RFrom(), "rOutgoing", null, 0, -1, RCodeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRCodeElement_Versions(), ecorePackage.getEString(), "versions", null, 0, -1, RCodeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRCodeElement_Id(), ecorePackage.getELong(), "id", null, 0, 1, RCodeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(rCodeElementEClass, ecorePackage.getEString(), "getDisplayName", 0, 1);

		initEClass(rProjectEClass, RProject.class, "RProject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRProject_Name(), ecorePackage.getEString(), "name", null, 0, 1, RProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRProject_RClasses(), this.getRClass(), this.getRClass_RProject(), "rClasses", null, 0, -1, RProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRProject_ContainingFolders(), ecorePackage.getEString(), "containingFolders", null, 0, 1, RProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRProject_ProductPath(), ecorePackage.getEString(), "productPath", null, 0, 1, RProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRProject_StoreLocation(), ecorePackage.getEString(), "storeLocation", null, 0, 1, RProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rClassEClass, RClass.class, "RClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRClass_SimpleName(), ecorePackage.getEString(), "simpleName", null, 0, 1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRClass_PackageName(), ecorePackage.getEString(), "packageName", null, 0, 1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRClass_RMethods(), this.getRMethod(), this.getRMethod_RClass(), "rMethods", null, 0, -1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRClass_RFields(), this.getRField(), this.getRField_RClass(), "rFields", null, 0, -1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRClass_RProject(), this.getRProject(), this.getRProject_RClasses(), "rProject", null, 1, 1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRClass_Extends(), ecorePackage.getEString(), "extends", null, 0, 1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRClass_Implements(), ecorePackage.getEString(), "implements", null, 0, 1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRClass_Private(), ecorePackage.getEBoolean(), "private", null, 0, 1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRClass_Anonymous(), ecorePackage.getEBoolean(), "anonymous", null, 0, 1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRClass_ReferencedClasses(), ecorePackage.getEEList(), "referencedClasses", null, 0, 1, RClass.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRClass_FqName(), ecorePackage.getEString(), "fqName", null, 0, 1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(rClassEClass, ecorePackage.getEString(), "getSourcePath", 0, 1);

		initEClass(rMethodEClass, RMethod.class, "RMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRMethod_Signature(), ecorePackage.getEString(), "signature", null, 0, 1, RMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRMethod_RClass(), this.getRClass(), this.getRClass_RMethods(), "rClass", null, 1, 1, RMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRMethod_Private(), ecorePackage.getEBooleanObject(), "private", null, 0, 1, RMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRMethod_ReferencedFields(), ecorePackage.getEEList(), "referencedFields", null, 0, 1, RMethod.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRMethod_ReferencedMethods(), ecorePackage.getEEList(), "referencedMethods", null, 0, 1, RMethod.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRMethod_Static(), ecorePackage.getEBoolean(), "static", null, 0, 1, RMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rFieldEClass, RField.class, "RField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRField_Signature(), ecorePackage.getEString(), "signature", null, 0, 1, RField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRField_RClass(), this.getRClass(), this.getRClass_RFields(), "rClass", null, 1, 1, RField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRField_Private(), ecorePackage.getEBoolean(), "private", null, 0, 1, RField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rDependencyEClass, RDependency.class, "RDependency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRDependency_RFrom(), this.getRCodeElement(), this.getRCodeElement_ROutgoing(), "rFrom", null, 1, 1, RDependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRDependency_RTo(), this.getRCodeElement(), this.getRCodeElement_RIncoming(), "rTo", null, 1, 1, RDependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRDependency_DepType(), ecorePackage.getEInt(), "depType", "0", 0, 1, RDependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rRepositoryEClass, RRepository.class, "RRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRRepository_RDependencies(), this.getRDependency(), null, "rDependencies", null, 0, -1, RRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRRepository_RProjects(), this.getRProject(), null, "rProjects", null, 0, -1, RRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rTransitiveDependencyEClass, RTransitiveDependency.class, "RTransitiveDependency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRTransitiveDependency_RTtransitiveFrom(), this.getRDependency(), null, "rTtransitiveFrom", null, 0, -1, RTransitiveDependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "ExtendedMetaData";		
		addAnnotation
		  (getRRepositoryItem_Uuid(), 
		   source, 
		   new String[] {
			 "name", "uuid",
			 "namespace", "http://cern.ch/be/co/devtools-deps-analysis/2.0.0"
		   });
	}

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public interface Literals {
		/**
		 * The meta object literal for the '{@link cern.devtools.deps.repomodel.RRepositoryItem <em>RRepository Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.deps.repomodel.RRepositoryItem
		 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRRepositoryItem()
		 * @generated
		 */
		public static final EClass RREPOSITORY_ITEM = eINSTANCE.getRRepositoryItem();

		/**
		 * The meta object literal for the '<em><b>Uuid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RREPOSITORY_ITEM__UUID = eINSTANCE.getRRepositoryItem_Uuid();

		/**
		 * The meta object literal for the '{@link cern.devtools.deps.repomodel.RCodeElement <em>RCode Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.deps.repomodel.RCodeElement
		 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRCodeElement()
		 * @generated
		 */
		public static final EClass RCODE_ELEMENT = eINSTANCE.getRCodeElement();

		/**
		 * The meta object literal for the '<em><b>RIncoming</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RCODE_ELEMENT__RINCOMING = eINSTANCE.getRCodeElement_RIncoming();

		/**
		 * The meta object literal for the '<em><b>ROutgoing</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RCODE_ELEMENT__ROUTGOING = eINSTANCE.getRCodeElement_ROutgoing();

		/**
		 * The meta object literal for the '<em><b>Versions</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RCODE_ELEMENT__VERSIONS = eINSTANCE.getRCodeElement_Versions();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RCODE_ELEMENT__ID = eINSTANCE.getRCodeElement_Id();

		/**
		 * The meta object literal for the '{@link cern.devtools.deps.repomodel.RProject <em>RProject</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.deps.repomodel.RProject
		 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRProject()
		 * @generated
		 */
		public static final EClass RPROJECT = eINSTANCE.getRProject();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RPROJECT__NAME = eINSTANCE.getRProject_Name();

		/**
		 * The meta object literal for the '<em><b>RClasses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RPROJECT__RCLASSES = eINSTANCE.getRProject_RClasses();

		/**
		 * The meta object literal for the '<em><b>Containing Folders</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RPROJECT__CONTAINING_FOLDERS = eINSTANCE.getRProject_ContainingFolders();

		/**
		 * The meta object literal for the '<em><b>Product Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RPROJECT__PRODUCT_PATH = eINSTANCE.getRProject_ProductPath();

		/**
		 * The meta object literal for the '<em><b>Store Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RPROJECT__STORE_LOCATION = eINSTANCE.getRProject_StoreLocation();

		/**
		 * The meta object literal for the '{@link cern.devtools.deps.repomodel.RClass <em>RClass</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.deps.repomodel.RClass
		 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRClass()
		 * @generated
		 */
		public static final EClass RCLASS = eINSTANCE.getRClass();

		/**
		 * The meta object literal for the '<em><b>Simple Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RCLASS__SIMPLE_NAME = eINSTANCE.getRClass_SimpleName();

		/**
		 * The meta object literal for the '<em><b>Package Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RCLASS__PACKAGE_NAME = eINSTANCE.getRClass_PackageName();

		/**
		 * The meta object literal for the '<em><b>RMethods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RCLASS__RMETHODS = eINSTANCE.getRClass_RMethods();

		/**
		 * The meta object literal for the '<em><b>RFields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RCLASS__RFIELDS = eINSTANCE.getRClass_RFields();

		/**
		 * The meta object literal for the '<em><b>RProject</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RCLASS__RPROJECT = eINSTANCE.getRClass_RProject();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RCLASS__EXTENDS = eINSTANCE.getRClass_Extends();

		/**
		 * The meta object literal for the '<em><b>Implements</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RCLASS__IMPLEMENTS = eINSTANCE.getRClass_Implements();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RCLASS__PRIVATE = eINSTANCE.getRClass_Private();

		/**
		 * The meta object literal for the '<em><b>Anonymous</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RCLASS__ANONYMOUS = eINSTANCE.getRClass_Anonymous();

		/**
		 * The meta object literal for the '<em><b>Referenced Classes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RCLASS__REFERENCED_CLASSES = eINSTANCE.getRClass_ReferencedClasses();

		/**
		 * The meta object literal for the '<em><b>Fq Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RCLASS__FQ_NAME = eINSTANCE.getRClass_FqName();

		/**
		 * The meta object literal for the '{@link cern.devtools.deps.repomodel.RMethod <em>RMethod</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.deps.repomodel.RMethod
		 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRMethod()
		 * @generated
		 */
		public static final EClass RMETHOD = eINSTANCE.getRMethod();

		/**
		 * The meta object literal for the '<em><b>Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RMETHOD__SIGNATURE = eINSTANCE.getRMethod_Signature();

		/**
		 * The meta object literal for the '<em><b>RClass</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RMETHOD__RCLASS = eINSTANCE.getRMethod_RClass();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RMETHOD__PRIVATE = eINSTANCE.getRMethod_Private();

		/**
		 * The meta object literal for the '<em><b>Referenced Fields</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RMETHOD__REFERENCED_FIELDS = eINSTANCE.getRMethod_ReferencedFields();

		/**
		 * The meta object literal for the '<em><b>Referenced Methods</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RMETHOD__REFERENCED_METHODS = eINSTANCE.getRMethod_ReferencedMethods();

		/**
		 * The meta object literal for the '<em><b>Static</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RMETHOD__STATIC = eINSTANCE.getRMethod_Static();

		/**
		 * The meta object literal for the '{@link cern.devtools.deps.repomodel.RField <em>RField</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.deps.repomodel.RField
		 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRField()
		 * @generated
		 */
		public static final EClass RFIELD = eINSTANCE.getRField();

		/**
		 * The meta object literal for the '<em><b>Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RFIELD__SIGNATURE = eINSTANCE.getRField_Signature();

		/**
		 * The meta object literal for the '<em><b>RClass</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RFIELD__RCLASS = eINSTANCE.getRField_RClass();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RFIELD__PRIVATE = eINSTANCE.getRField_Private();

		/**
		 * The meta object literal for the '{@link cern.devtools.deps.repomodel.RDependency <em>RDependency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.deps.repomodel.RDependency
		 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRDependency()
		 * @generated
		 */
		public static final EClass RDEPENDENCY = eINSTANCE.getRDependency();

		/**
		 * The meta object literal for the '<em><b>RFrom</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RDEPENDENCY__RFROM = eINSTANCE.getRDependency_RFrom();

		/**
		 * The meta object literal for the '<em><b>RTo</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RDEPENDENCY__RTO = eINSTANCE.getRDependency_RTo();

		/**
		 * The meta object literal for the '<em><b>Dep Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RDEPENDENCY__DEP_TYPE = eINSTANCE.getRDependency_DepType();

		/**
		 * The meta object literal for the '{@link cern.devtools.deps.repomodel.RRepository <em>RRepository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.deps.repomodel.RRepository
		 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRRepository()
		 * @generated
		 */
		public static final EClass RREPOSITORY = eINSTANCE.getRRepository();

		/**
		 * The meta object literal for the '<em><b>RDependencies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RREPOSITORY__RDEPENDENCIES = eINSTANCE.getRRepository_RDependencies();

		/**
		 * The meta object literal for the '<em><b>RProjects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RREPOSITORY__RPROJECTS = eINSTANCE.getRRepository_RProjects();

		/**
		 * The meta object literal for the '{@link cern.devtools.deps.repomodel.RTransitiveDependency <em>RTransitive Dependency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.deps.repomodel.RTransitiveDependency
		 * @see cern.devtools.deps.repomodel.RepomodelPackage#getRTransitiveDependency()
		 * @generated
		 */
		public static final EClass RTRANSITIVE_DEPENDENCY = eINSTANCE.getRTransitiveDependency();

		/**
		 * The meta object literal for the '<em><b>RTtransitive From</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RTRANSITIVE_DEPENDENCY__RTTRANSITIVE_FROM = eINSTANCE.getRTransitiveDependency_RTtransitiveFrom();

	}

} //RepomodelPackage
