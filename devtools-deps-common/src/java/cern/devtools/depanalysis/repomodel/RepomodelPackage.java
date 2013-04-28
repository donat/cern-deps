/**
 */
package cern.devtools.depanalysis.repomodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see cern.devtools.depanalysis.repomodel.RepomodelFactory
 * @model kind="package"
 * @generated
 */
public interface RepomodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "repomodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://inf.mit.bme.hu/donat/incquery-deps/repomodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "rm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RepomodelPackage eINSTANCE = cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link cern.devtools.depanalysis.repomodel.impl.RComponentImpl <em>RComponent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.depanalysis.repomodel.impl.RComponentImpl
	 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRComponent()
	 * @generated
	 */
	int RCOMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCOMPONENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCOMPONENT__UUID = 1;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCOMPONENT__OUTGOING = 2;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCOMPONENT__INCOMING = 3;

	/**
	 * The number of structural features of the '<em>RComponent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCOMPONENT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link cern.devtools.depanalysis.repomodel.impl.RVersionedImpl <em>RVersioned</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.depanalysis.repomodel.impl.RVersionedImpl
	 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRVersioned()
	 * @generated
	 */
	int RVERSIONED = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RVERSIONED__NAME = RCOMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RVERSIONED__UUID = RCOMPONENT__UUID;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RVERSIONED__OUTGOING = RCOMPONENT__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RVERSIONED__INCOMING = RCOMPONENT__INCOMING;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RVERSIONED__VERSIONS = RCOMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>RVersioned</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RVERSIONED_FEATURE_COUNT = RCOMPONENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link cern.devtools.depanalysis.repomodel.impl.RStructuralImpl <em>RStructural</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.depanalysis.repomodel.impl.RStructuralImpl
	 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRStructural()
	 * @generated
	 */
	int RSTRUCTURAL = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RSTRUCTURAL__NAME = RVERSIONED__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RSTRUCTURAL__UUID = RVERSIONED__UUID;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RSTRUCTURAL__OUTGOING = RVERSIONED__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RSTRUCTURAL__INCOMING = RVERSIONED__INCOMING;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RSTRUCTURAL__VERSIONS = RVERSIONED__VERSIONS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RSTRUCTURAL__MODIFIERS = RVERSIONED_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>RStructural</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RSTRUCTURAL_FEATURE_COUNT = RVERSIONED_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link cern.devtools.depanalysis.repomodel.impl.RRepositoryImpl <em>RRepository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.depanalysis.repomodel.impl.RRepositoryImpl
	 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRRepository()
	 * @generated
	 */
	int RREPOSITORY = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RREPOSITORY__NAME = RCOMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RREPOSITORY__UUID = RCOMPONENT__UUID;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RREPOSITORY__OUTGOING = RCOMPONENT__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RREPOSITORY__INCOMING = RCOMPONENT__INCOMING;

	/**
	 * The feature id for the '<em><b>Projects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RREPOSITORY__PROJECTS = RCOMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>RRepository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RREPOSITORY_FEATURE_COUNT = RCOMPONENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link cern.devtools.depanalysis.repomodel.impl.RProjectImpl <em>RProject</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.depanalysis.repomodel.impl.RProjectImpl
	 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRProject()
	 * @generated
	 */
	int RPROJECT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPROJECT__NAME = RVERSIONED__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPROJECT__UUID = RVERSIONED__UUID;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPROJECT__OUTGOING = RVERSIONED__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPROJECT__INCOMING = RVERSIONED__INCOMING;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPROJECT__VERSIONS = RVERSIONED__VERSIONS;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPROJECT__PACKAGES = RVERSIONED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPROJECT__DEPENDENCIES = RVERSIONED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Repository</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPROJECT__REPOSITORY = RVERSIONED_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Containing Folders</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPROJECT__CONTAINING_FOLDERS = RVERSIONED_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Jar Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPROJECT__JAR_PATH = RVERSIONED_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>RProject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPROJECT_FEATURE_COUNT = RVERSIONED_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link cern.devtools.depanalysis.repomodel.impl.RPackageImpl <em>RPackage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.depanalysis.repomodel.impl.RPackageImpl
	 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRPackage()
	 * @generated
	 */
	int RPACKAGE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPACKAGE__NAME = RVERSIONED__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPACKAGE__UUID = RVERSIONED__UUID;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPACKAGE__OUTGOING = RVERSIONED__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPACKAGE__INCOMING = RVERSIONED__INCOMING;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPACKAGE__VERSIONS = RVERSIONED__VERSIONS;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPACKAGE__CLASSES = RVERSIONED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Project</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPACKAGE__PROJECT = RVERSIONED_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>RPackage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RPACKAGE_FEATURE_COUNT = RVERSIONED_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link cern.devtools.depanalysis.repomodel.impl.RClassImpl <em>RClass</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.depanalysis.repomodel.impl.RClassImpl
	 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRClass()
	 * @generated
	 */
	int RCLASS = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCLASS__NAME = RSTRUCTURAL__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCLASS__UUID = RSTRUCTURAL__UUID;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCLASS__OUTGOING = RSTRUCTURAL__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCLASS__INCOMING = RSTRUCTURAL__INCOMING;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCLASS__VERSIONS = RSTRUCTURAL__VERSIONS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCLASS__MODIFIERS = RSTRUCTURAL__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCLASS__EXTENDS = RSTRUCTURAL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Implements</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCLASS__IMPLEMENTS = RSTRUCTURAL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCLASS__FIELDS = RSTRUCTURAL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCLASS__METHODS = RSTRUCTURAL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCLASS__PACKAGE = RSTRUCTURAL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Referenced Classes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCLASS__REFERENCED_CLASSES = RSTRUCTURAL_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>RClass</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RCLASS_FEATURE_COUNT = RSTRUCTURAL_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link cern.devtools.depanalysis.repomodel.impl.RFieldImpl <em>RField</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.depanalysis.repomodel.impl.RFieldImpl
	 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRField()
	 * @generated
	 */
	int RFIELD = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RFIELD__NAME = RSTRUCTURAL__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RFIELD__UUID = RSTRUCTURAL__UUID;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RFIELD__OUTGOING = RSTRUCTURAL__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RFIELD__INCOMING = RSTRUCTURAL__INCOMING;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RFIELD__VERSIONS = RSTRUCTURAL__VERSIONS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RFIELD__MODIFIERS = RSTRUCTURAL__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RFIELD__TYPE = RSTRUCTURAL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RFIELD__CLASS = RSTRUCTURAL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RFIELD__SIGNATURE = RSTRUCTURAL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>RField</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RFIELD_FEATURE_COUNT = RSTRUCTURAL_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link cern.devtools.depanalysis.repomodel.impl.RMethodImpl <em>RMethod</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.depanalysis.repomodel.impl.RMethodImpl
	 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRMethod()
	 * @generated
	 */
	int RMETHOD = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RMETHOD__NAME = RSTRUCTURAL__NAME;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RMETHOD__UUID = RSTRUCTURAL__UUID;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RMETHOD__OUTGOING = RSTRUCTURAL__OUTGOING;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RMETHOD__INCOMING = RSTRUCTURAL__INCOMING;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RMETHOD__VERSIONS = RSTRUCTURAL__VERSIONS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RMETHOD__MODIFIERS = RSTRUCTURAL__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RMETHOD__RETURN_TYPE = RSTRUCTURAL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameter Types</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RMETHOD__PARAMETER_TYPES = RSTRUCTURAL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RMETHOD__CLASS = RSTRUCTURAL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Referenced Fields</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RMETHOD__REFERENCED_FIELDS = RSTRUCTURAL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Referenced Methods</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RMETHOD__REFERENCED_METHODS = RSTRUCTURAL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RMETHOD__SIGNATURE = RSTRUCTURAL_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>RMethod</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RMETHOD_FEATURE_COUNT = RSTRUCTURAL_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link cern.devtools.depanalysis.repomodel.impl.RDependencyImpl <em>RDependency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.depanalysis.repomodel.impl.RDependencyImpl
	 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRDependency()
	 * @generated
	 */
	int RDEPENDENCY = 9;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RDEPENDENCY__FROM = 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RDEPENDENCY__TO = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RDEPENDENCY__TYPE = 2;

	/**
	 * The number of structural features of the '<em>RDependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RDEPENDENCY_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link cern.devtools.depanalysis.repomodel.RModifier <em>RModifier</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.depanalysis.repomodel.RModifier
	 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRModifier()
	 * @generated
	 */
	int RMODIFIER = 10;

	/**
	 * The meta object id for the '{@link cern.devtools.depanalysis.repomodel.RDependencyType <em>RDependency Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cern.devtools.depanalysis.repomodel.RDependencyType
	 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRDependencyType()
	 * @generated
	 */
	int RDEPENDENCY_TYPE = 11;


	/**
	 * Returns the meta object for class '{@link cern.devtools.depanalysis.repomodel.RComponent <em>RComponent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RComponent</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RComponent
	 * @generated
	 */
	EClass getRComponent();

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.depanalysis.repomodel.RComponent#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RComponent#getName()
	 * @see #getRComponent()
	 * @generated
	 */
	EAttribute getRComponent_Name();

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.depanalysis.repomodel.RComponent#getUuid <em>Uuid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uuid</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RComponent#getUuid()
	 * @see #getRComponent()
	 * @generated
	 */
	EAttribute getRComponent_Uuid();

	/**
	 * Returns the meta object for the reference list '{@link cern.devtools.depanalysis.repomodel.RComponent#getOutgoing <em>Outgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoing</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RComponent#getOutgoing()
	 * @see #getRComponent()
	 * @generated
	 */
	EReference getRComponent_Outgoing();

	/**
	 * Returns the meta object for the reference list '{@link cern.devtools.depanalysis.repomodel.RComponent#getIncoming <em>Incoming</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RComponent#getIncoming()
	 * @see #getRComponent()
	 * @generated
	 */
	EReference getRComponent_Incoming();

	/**
	 * Returns the meta object for class '{@link cern.devtools.depanalysis.repomodel.RVersioned <em>RVersioned</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RVersioned</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RVersioned
	 * @generated
	 */
	EClass getRVersioned();

	/**
	 * Returns the meta object for the attribute list '{@link cern.devtools.depanalysis.repomodel.RVersioned#getVersions <em>Versions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Versions</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RVersioned#getVersions()
	 * @see #getRVersioned()
	 * @generated
	 */
	EAttribute getRVersioned_Versions();

	/**
	 * Returns the meta object for class '{@link cern.devtools.depanalysis.repomodel.RStructural <em>RStructural</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RStructural</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RStructural
	 * @generated
	 */
	EClass getRStructural();

	/**
	 * Returns the meta object for the attribute list '{@link cern.devtools.depanalysis.repomodel.RStructural#getModifiers <em>Modifiers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Modifiers</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RStructural#getModifiers()
	 * @see #getRStructural()
	 * @generated
	 */
	EAttribute getRStructural_Modifiers();

	/**
	 * Returns the meta object for class '{@link cern.devtools.depanalysis.repomodel.RRepository <em>RRepository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RRepository</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RRepository
	 * @generated
	 */
	EClass getRRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link cern.devtools.depanalysis.repomodel.RRepository#getProjects <em>Projects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Projects</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RRepository#getProjects()
	 * @see #getRRepository()
	 * @generated
	 */
	EReference getRRepository_Projects();

	/**
	 * Returns the meta object for class '{@link cern.devtools.depanalysis.repomodel.RProject <em>RProject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RProject</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RProject
	 * @generated
	 */
	EClass getRProject();

	/**
	 * Returns the meta object for the containment reference list '{@link cern.devtools.depanalysis.repomodel.RProject#getPackages <em>Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Packages</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RProject#getPackages()
	 * @see #getRProject()
	 * @generated
	 */
	EReference getRProject_Packages();

	/**
	 * Returns the meta object for the containment reference list '{@link cern.devtools.depanalysis.repomodel.RProject#getDependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dependencies</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RProject#getDependencies()
	 * @see #getRProject()
	 * @generated
	 */
	EReference getRProject_Dependencies();

	/**
	 * Returns the meta object for the container reference '{@link cern.devtools.depanalysis.repomodel.RProject#getRepository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Repository</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RProject#getRepository()
	 * @see #getRProject()
	 * @generated
	 */
	EReference getRProject_Repository();

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.depanalysis.repomodel.RProject#getContainingFolders <em>Containing Folders</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Containing Folders</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RProject#getContainingFolders()
	 * @see #getRProject()
	 * @generated
	 */
	EAttribute getRProject_ContainingFolders();

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.depanalysis.repomodel.RProject#getJarPath <em>Jar Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jar Path</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RProject#getJarPath()
	 * @see #getRProject()
	 * @generated
	 */
	EAttribute getRProject_JarPath();

	/**
	 * Returns the meta object for class '{@link cern.devtools.depanalysis.repomodel.RPackage <em>RPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RPackage</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RPackage
	 * @generated
	 */
	EClass getRPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link cern.devtools.depanalysis.repomodel.RPackage#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RPackage#getClasses()
	 * @see #getRPackage()
	 * @generated
	 */
	EReference getRPackage_Classes();

	/**
	 * Returns the meta object for the container reference '{@link cern.devtools.depanalysis.repomodel.RPackage#getProject <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Project</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RPackage#getProject()
	 * @see #getRPackage()
	 * @generated
	 */
	EReference getRPackage_Project();

	/**
	 * Returns the meta object for class '{@link cern.devtools.depanalysis.repomodel.RClass <em>RClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RClass</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RClass
	 * @generated
	 */
	EClass getRClass();

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.depanalysis.repomodel.RClass#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extends</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RClass#getExtends()
	 * @see #getRClass()
	 * @generated
	 */
	EAttribute getRClass_Extends();

	/**
	 * Returns the meta object for the attribute list '{@link cern.devtools.depanalysis.repomodel.RClass#getImplements <em>Implements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Implements</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RClass#getImplements()
	 * @see #getRClass()
	 * @generated
	 */
	EAttribute getRClass_Implements();

	/**
	 * Returns the meta object for the containment reference list '{@link cern.devtools.depanalysis.repomodel.RClass#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fields</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RClass#getFields()
	 * @see #getRClass()
	 * @generated
	 */
	EReference getRClass_Fields();

	/**
	 * Returns the meta object for the containment reference list '{@link cern.devtools.depanalysis.repomodel.RClass#getMethods <em>Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Methods</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RClass#getMethods()
	 * @see #getRClass()
	 * @generated
	 */
	EReference getRClass_Methods();

	/**
	 * Returns the meta object for the container reference '{@link cern.devtools.depanalysis.repomodel.RClass#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Package</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RClass#getPackage()
	 * @see #getRClass()
	 * @generated
	 */
	EReference getRClass_Package();

	/**
	 * Returns the meta object for the attribute list '{@link cern.devtools.depanalysis.repomodel.RClass#getReferencedClasses <em>Referenced Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Referenced Classes</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RClass#getReferencedClasses()
	 * @see #getRClass()
	 * @generated
	 */
	EAttribute getRClass_ReferencedClasses();

	/**
	 * Returns the meta object for class '{@link cern.devtools.depanalysis.repomodel.RField <em>RField</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RField</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RField
	 * @generated
	 */
	EClass getRField();

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.depanalysis.repomodel.RField#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RField#getType()
	 * @see #getRField()
	 * @generated
	 */
	EAttribute getRField_Type();

	/**
	 * Returns the meta object for the container reference '{@link cern.devtools.depanalysis.repomodel.RField#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Class</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RField#getClass_()
	 * @see #getRField()
	 * @generated
	 */
	EReference getRField_Class();

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.depanalysis.repomodel.RField#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signature</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RField#getSignature()
	 * @see #getRField()
	 * @generated
	 */
	EAttribute getRField_Signature();

	/**
	 * Returns the meta object for class '{@link cern.devtools.depanalysis.repomodel.RMethod <em>RMethod</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RMethod</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RMethod
	 * @generated
	 */
	EClass getRMethod();

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.depanalysis.repomodel.RMethod#getReturnType <em>Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Return Type</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RMethod#getReturnType()
	 * @see #getRMethod()
	 * @generated
	 */
	EAttribute getRMethod_ReturnType();

	/**
	 * Returns the meta object for the attribute list '{@link cern.devtools.depanalysis.repomodel.RMethod#getParameterTypes <em>Parameter Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Parameter Types</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RMethod#getParameterTypes()
	 * @see #getRMethod()
	 * @generated
	 */
	EAttribute getRMethod_ParameterTypes();

	/**
	 * Returns the meta object for the container reference '{@link cern.devtools.depanalysis.repomodel.RMethod#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Class</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RMethod#getClass_()
	 * @see #getRMethod()
	 * @generated
	 */
	EReference getRMethod_Class();

	/**
	 * Returns the meta object for the attribute list '{@link cern.devtools.depanalysis.repomodel.RMethod#getReferencedFields <em>Referenced Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Referenced Fields</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RMethod#getReferencedFields()
	 * @see #getRMethod()
	 * @generated
	 */
	EAttribute getRMethod_ReferencedFields();

	/**
	 * Returns the meta object for the attribute list '{@link cern.devtools.depanalysis.repomodel.RMethod#getReferencedMethods <em>Referenced Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Referenced Methods</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RMethod#getReferencedMethods()
	 * @see #getRMethod()
	 * @generated
	 */
	EAttribute getRMethod_ReferencedMethods();

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.depanalysis.repomodel.RMethod#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signature</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RMethod#getSignature()
	 * @see #getRMethod()
	 * @generated
	 */
	EAttribute getRMethod_Signature();

	/**
	 * Returns the meta object for class '{@link cern.devtools.depanalysis.repomodel.RDependency <em>RDependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RDependency</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RDependency
	 * @generated
	 */
	EClass getRDependency();

	/**
	 * Returns the meta object for the reference '{@link cern.devtools.depanalysis.repomodel.RDependency#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RDependency#getFrom()
	 * @see #getRDependency()
	 * @generated
	 */
	EReference getRDependency_From();

	/**
	 * Returns the meta object for the reference '{@link cern.devtools.depanalysis.repomodel.RDependency#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RDependency#getTo()
	 * @see #getRDependency()
	 * @generated
	 */
	EReference getRDependency_To();

	/**
	 * Returns the meta object for the attribute '{@link cern.devtools.depanalysis.repomodel.RDependency#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RDependency#getType()
	 * @see #getRDependency()
	 * @generated
	 */
	EAttribute getRDependency_Type();

	/**
	 * Returns the meta object for enum '{@link cern.devtools.depanalysis.repomodel.RModifier <em>RModifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>RModifier</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RModifier
	 * @generated
	 */
	EEnum getRModifier();

	/**
	 * Returns the meta object for enum '{@link cern.devtools.depanalysis.repomodel.RDependencyType <em>RDependency Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>RDependency Type</em>'.
	 * @see cern.devtools.depanalysis.repomodel.RDependencyType
	 * @generated
	 */
	EEnum getRDependencyType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RepomodelFactory getRepomodelFactory();

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
	interface Literals {
		/**
		 * The meta object literal for the '{@link cern.devtools.depanalysis.repomodel.impl.RComponentImpl <em>RComponent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.depanalysis.repomodel.impl.RComponentImpl
		 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRComponent()
		 * @generated
		 */
		EClass RCOMPONENT = eINSTANCE.getRComponent();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RCOMPONENT__NAME = eINSTANCE.getRComponent_Name();

		/**
		 * The meta object literal for the '<em><b>Uuid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RCOMPONENT__UUID = eINSTANCE.getRComponent_Uuid();

		/**
		 * The meta object literal for the '<em><b>Outgoing</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RCOMPONENT__OUTGOING = eINSTANCE.getRComponent_Outgoing();

		/**
		 * The meta object literal for the '<em><b>Incoming</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RCOMPONENT__INCOMING = eINSTANCE.getRComponent_Incoming();

		/**
		 * The meta object literal for the '{@link cern.devtools.depanalysis.repomodel.impl.RVersionedImpl <em>RVersioned</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.depanalysis.repomodel.impl.RVersionedImpl
		 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRVersioned()
		 * @generated
		 */
		EClass RVERSIONED = eINSTANCE.getRVersioned();

		/**
		 * The meta object literal for the '<em><b>Versions</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RVERSIONED__VERSIONS = eINSTANCE.getRVersioned_Versions();

		/**
		 * The meta object literal for the '{@link cern.devtools.depanalysis.repomodel.impl.RStructuralImpl <em>RStructural</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.depanalysis.repomodel.impl.RStructuralImpl
		 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRStructural()
		 * @generated
		 */
		EClass RSTRUCTURAL = eINSTANCE.getRStructural();

		/**
		 * The meta object literal for the '<em><b>Modifiers</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RSTRUCTURAL__MODIFIERS = eINSTANCE.getRStructural_Modifiers();

		/**
		 * The meta object literal for the '{@link cern.devtools.depanalysis.repomodel.impl.RRepositoryImpl <em>RRepository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.depanalysis.repomodel.impl.RRepositoryImpl
		 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRRepository()
		 * @generated
		 */
		EClass RREPOSITORY = eINSTANCE.getRRepository();

		/**
		 * The meta object literal for the '<em><b>Projects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RREPOSITORY__PROJECTS = eINSTANCE.getRRepository_Projects();

		/**
		 * The meta object literal for the '{@link cern.devtools.depanalysis.repomodel.impl.RProjectImpl <em>RProject</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.depanalysis.repomodel.impl.RProjectImpl
		 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRProject()
		 * @generated
		 */
		EClass RPROJECT = eINSTANCE.getRProject();

		/**
		 * The meta object literal for the '<em><b>Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RPROJECT__PACKAGES = eINSTANCE.getRProject_Packages();

		/**
		 * The meta object literal for the '<em><b>Dependencies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RPROJECT__DEPENDENCIES = eINSTANCE.getRProject_Dependencies();

		/**
		 * The meta object literal for the '<em><b>Repository</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RPROJECT__REPOSITORY = eINSTANCE.getRProject_Repository();

		/**
		 * The meta object literal for the '<em><b>Containing Folders</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RPROJECT__CONTAINING_FOLDERS = eINSTANCE.getRProject_ContainingFolders();

		/**
		 * The meta object literal for the '<em><b>Jar Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RPROJECT__JAR_PATH = eINSTANCE.getRProject_JarPath();

		/**
		 * The meta object literal for the '{@link cern.devtools.depanalysis.repomodel.impl.RPackageImpl <em>RPackage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.depanalysis.repomodel.impl.RPackageImpl
		 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRPackage()
		 * @generated
		 */
		EClass RPACKAGE = eINSTANCE.getRPackage();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RPACKAGE__CLASSES = eINSTANCE.getRPackage_Classes();

		/**
		 * The meta object literal for the '<em><b>Project</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RPACKAGE__PROJECT = eINSTANCE.getRPackage_Project();

		/**
		 * The meta object literal for the '{@link cern.devtools.depanalysis.repomodel.impl.RClassImpl <em>RClass</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.depanalysis.repomodel.impl.RClassImpl
		 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRClass()
		 * @generated
		 */
		EClass RCLASS = eINSTANCE.getRClass();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RCLASS__EXTENDS = eINSTANCE.getRClass_Extends();

		/**
		 * The meta object literal for the '<em><b>Implements</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RCLASS__IMPLEMENTS = eINSTANCE.getRClass_Implements();

		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RCLASS__FIELDS = eINSTANCE.getRClass_Fields();

		/**
		 * The meta object literal for the '<em><b>Methods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RCLASS__METHODS = eINSTANCE.getRClass_Methods();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RCLASS__PACKAGE = eINSTANCE.getRClass_Package();

		/**
		 * The meta object literal for the '<em><b>Referenced Classes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RCLASS__REFERENCED_CLASSES = eINSTANCE.getRClass_ReferencedClasses();

		/**
		 * The meta object literal for the '{@link cern.devtools.depanalysis.repomodel.impl.RFieldImpl <em>RField</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.depanalysis.repomodel.impl.RFieldImpl
		 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRField()
		 * @generated
		 */
		EClass RFIELD = eINSTANCE.getRField();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RFIELD__TYPE = eINSTANCE.getRField_Type();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RFIELD__CLASS = eINSTANCE.getRField_Class();

		/**
		 * The meta object literal for the '<em><b>Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RFIELD__SIGNATURE = eINSTANCE.getRField_Signature();

		/**
		 * The meta object literal for the '{@link cern.devtools.depanalysis.repomodel.impl.RMethodImpl <em>RMethod</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.depanalysis.repomodel.impl.RMethodImpl
		 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRMethod()
		 * @generated
		 */
		EClass RMETHOD = eINSTANCE.getRMethod();

		/**
		 * The meta object literal for the '<em><b>Return Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RMETHOD__RETURN_TYPE = eINSTANCE.getRMethod_ReturnType();

		/**
		 * The meta object literal for the '<em><b>Parameter Types</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RMETHOD__PARAMETER_TYPES = eINSTANCE.getRMethod_ParameterTypes();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RMETHOD__CLASS = eINSTANCE.getRMethod_Class();

		/**
		 * The meta object literal for the '<em><b>Referenced Fields</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RMETHOD__REFERENCED_FIELDS = eINSTANCE.getRMethod_ReferencedFields();

		/**
		 * The meta object literal for the '<em><b>Referenced Methods</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RMETHOD__REFERENCED_METHODS = eINSTANCE.getRMethod_ReferencedMethods();

		/**
		 * The meta object literal for the '<em><b>Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RMETHOD__SIGNATURE = eINSTANCE.getRMethod_Signature();

		/**
		 * The meta object literal for the '{@link cern.devtools.depanalysis.repomodel.impl.RDependencyImpl <em>RDependency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.depanalysis.repomodel.impl.RDependencyImpl
		 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRDependency()
		 * @generated
		 */
		EClass RDEPENDENCY = eINSTANCE.getRDependency();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RDEPENDENCY__FROM = eINSTANCE.getRDependency_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RDEPENDENCY__TO = eINSTANCE.getRDependency_To();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RDEPENDENCY__TYPE = eINSTANCE.getRDependency_Type();

		/**
		 * The meta object literal for the '{@link cern.devtools.depanalysis.repomodel.RModifier <em>RModifier</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.depanalysis.repomodel.RModifier
		 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRModifier()
		 * @generated
		 */
		EEnum RMODIFIER = eINSTANCE.getRModifier();

		/**
		 * The meta object literal for the '{@link cern.devtools.depanalysis.repomodel.RDependencyType <em>RDependency Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cern.devtools.depanalysis.repomodel.RDependencyType
		 * @see cern.devtools.depanalysis.repomodel.impl.RepomodelPackageImpl#getRDependencyType()
		 * @generated
		 */
		EEnum RDEPENDENCY_TYPE = eINSTANCE.getRDependencyType();

	}

} //RepomodelPackage
