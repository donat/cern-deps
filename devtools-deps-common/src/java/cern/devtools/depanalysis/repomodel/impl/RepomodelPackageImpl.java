/**
 */
package cern.devtools.depanalysis.repomodel.impl;

import cern.devtools.depanalysis.repomodel.RClass;
import cern.devtools.depanalysis.repomodel.RComponent;
import cern.devtools.depanalysis.repomodel.RDependency;
import cern.devtools.depanalysis.repomodel.RDependencyType;
import cern.devtools.depanalysis.repomodel.RField;
import cern.devtools.depanalysis.repomodel.RMethod;
import cern.devtools.depanalysis.repomodel.RModifier;
import cern.devtools.depanalysis.repomodel.RPackage;
import cern.devtools.depanalysis.repomodel.RProject;
import cern.devtools.depanalysis.repomodel.RRepository;
import cern.devtools.depanalysis.repomodel.RStructural;
import cern.devtools.depanalysis.repomodel.RVersioned;
import cern.devtools.depanalysis.repomodel.RepomodelFactory;
import cern.devtools.depanalysis.repomodel.RepomodelPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RepomodelPackageImpl extends EPackageImpl implements RepomodelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rVersionedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rStructuralEClass = null;

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
	private EClass rProjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rPackageEClass = null;

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
	private EClass rFieldEClass = null;

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
	private EClass rDependencyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum rModifierEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum rDependencyTypeEEnum = null;

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
	 * @see cern.devtools.depanalysis.repomodel.RepomodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RepomodelPackageImpl() {
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
		RepomodelPackageImpl theRepomodelPackage = (RepomodelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RepomodelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new RepomodelPackageImpl());

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRComponent() {
		return rComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRComponent_Name() {
		return (EAttribute)rComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRComponent_Uuid() {
		return (EAttribute)rComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRComponent_Outgoing() {
		return (EReference)rComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRComponent_Incoming() {
		return (EReference)rComponentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRVersioned() {
		return rVersionedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRVersioned_Versions() {
		return (EAttribute)rVersionedEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRStructural() {
		return rStructuralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRStructural_Modifiers() {
		return (EAttribute)rStructuralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRStructural_Signature() {
		return (EAttribute)rStructuralEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRRepository() {
		return rRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRRepository_Projects() {
		return (EReference)rRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRProject() {
		return rProjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRProject_Packages() {
		return (EReference)rProjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRProject_Dependencies() {
		return (EReference)rProjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRProject_Repository() {
		return (EReference)rProjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRPackage() {
		return rPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRPackage_Classes() {
		return (EReference)rPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRPackage_Project() {
		return (EReference)rPackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRClass() {
		return rClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRClass_Extends() {
		return (EAttribute)rClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRClass_Implements() {
		return (EAttribute)rClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRClass_Fields() {
		return (EReference)rClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRClass_Methods() {
		return (EReference)rClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRClass_Package() {
		return (EReference)rClassEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRField() {
		return rFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRField_Type() {
		return (EAttribute)rFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRField_Class() {
		return (EReference)rFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRMethod() {
		return rMethodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRMethod_ReturnType() {
		return (EAttribute)rMethodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRMethod_ParameterTypes() {
		return (EAttribute)rMethodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRMethod_Class() {
		return (EReference)rMethodEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRMethod_ReferencedFields() {
		return (EAttribute)rMethodEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRMethod_ReferencedMethods() {
		return (EAttribute)rMethodEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRDependency() {
		return rDependencyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRDependency_From() {
		return (EReference)rDependencyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRDependency_To() {
		return (EReference)rDependencyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRDependency_Type() {
		return (EAttribute)rDependencyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getRModifier() {
		return rModifierEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getRDependencyType() {
		return rDependencyTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
		rComponentEClass = createEClass(RCOMPONENT);
		createEAttribute(rComponentEClass, RCOMPONENT__NAME);
		createEAttribute(rComponentEClass, RCOMPONENT__UUID);
		createEReference(rComponentEClass, RCOMPONENT__OUTGOING);
		createEReference(rComponentEClass, RCOMPONENT__INCOMING);

		rVersionedEClass = createEClass(RVERSIONED);
		createEAttribute(rVersionedEClass, RVERSIONED__VERSIONS);

		rStructuralEClass = createEClass(RSTRUCTURAL);
		createEAttribute(rStructuralEClass, RSTRUCTURAL__MODIFIERS);
		createEAttribute(rStructuralEClass, RSTRUCTURAL__SIGNATURE);

		rRepositoryEClass = createEClass(RREPOSITORY);
		createEReference(rRepositoryEClass, RREPOSITORY__PROJECTS);

		rProjectEClass = createEClass(RPROJECT);
		createEReference(rProjectEClass, RPROJECT__PACKAGES);
		createEReference(rProjectEClass, RPROJECT__DEPENDENCIES);
		createEReference(rProjectEClass, RPROJECT__REPOSITORY);

		rPackageEClass = createEClass(RPACKAGE);
		createEReference(rPackageEClass, RPACKAGE__CLASSES);
		createEReference(rPackageEClass, RPACKAGE__PROJECT);

		rClassEClass = createEClass(RCLASS);
		createEAttribute(rClassEClass, RCLASS__EXTENDS);
		createEAttribute(rClassEClass, RCLASS__IMPLEMENTS);
		createEReference(rClassEClass, RCLASS__FIELDS);
		createEReference(rClassEClass, RCLASS__METHODS);
		createEReference(rClassEClass, RCLASS__PACKAGE);

		rFieldEClass = createEClass(RFIELD);
		createEAttribute(rFieldEClass, RFIELD__TYPE);
		createEReference(rFieldEClass, RFIELD__CLASS);

		rMethodEClass = createEClass(RMETHOD);
		createEAttribute(rMethodEClass, RMETHOD__RETURN_TYPE);
		createEAttribute(rMethodEClass, RMETHOD__PARAMETER_TYPES);
		createEReference(rMethodEClass, RMETHOD__CLASS);
		createEAttribute(rMethodEClass, RMETHOD__REFERENCED_FIELDS);
		createEAttribute(rMethodEClass, RMETHOD__REFERENCED_METHODS);

		rDependencyEClass = createEClass(RDEPENDENCY);
		createEReference(rDependencyEClass, RDEPENDENCY__FROM);
		createEReference(rDependencyEClass, RDEPENDENCY__TO);
		createEAttribute(rDependencyEClass, RDEPENDENCY__TYPE);

		// Create enums
		rModifierEEnum = createEEnum(RMODIFIER);
		rDependencyTypeEEnum = createEEnum(RDEPENDENCY_TYPE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		rVersionedEClass.getESuperTypes().add(this.getRComponent());
		rStructuralEClass.getESuperTypes().add(this.getRVersioned());
		rRepositoryEClass.getESuperTypes().add(this.getRComponent());
		rProjectEClass.getESuperTypes().add(this.getRVersioned());
		rPackageEClass.getESuperTypes().add(this.getRVersioned());
		rClassEClass.getESuperTypes().add(this.getRStructural());
		rFieldEClass.getESuperTypes().add(this.getRStructural());
		rMethodEClass.getESuperTypes().add(this.getRStructural());

		// Initialize classes and features; add operations and parameters
		initEClass(rComponentEClass, RComponent.class, "RComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRComponent_Name(), ecorePackage.getEString(), "name", null, 0, 1, RComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRComponent_Uuid(), ecorePackage.getEString(), "uuid", null, 0, 1, RComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRComponent_Outgoing(), this.getRDependency(), this.getRDependency_From(), "outgoing", null, 0, -1, RComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRComponent_Incoming(), this.getRDependency(), this.getRDependency_To(), "incoming", null, 0, -1, RComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rVersionedEClass, RVersioned.class, "RVersioned", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRVersioned_Versions(), ecorePackage.getEString(), "versions", null, 0, -1, RVersioned.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rStructuralEClass, RStructural.class, "RStructural", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRStructural_Modifiers(), this.getRModifier(), "modifiers", null, 0, -1, RStructural.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRStructural_Signature(), ecorePackage.getEString(), "signature", null, 0, 1, RStructural.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rRepositoryEClass, RRepository.class, "RRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRRepository_Projects(), this.getRProject(), this.getRProject_Repository(), "projects", null, 0, -1, RRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rProjectEClass, RProject.class, "RProject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRProject_Packages(), this.getRPackage(), this.getRPackage_Project(), "packages", null, 0, -1, RProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRProject_Dependencies(), this.getRDependency(), null, "dependencies", null, 0, -1, RProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRProject_Repository(), this.getRRepository(), this.getRRepository_Projects(), "repository", null, 1, 1, RProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rPackageEClass, RPackage.class, "RPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRPackage_Classes(), this.getRClass(), this.getRClass_Package(), "classes", null, 0, -1, RPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRPackage_Project(), this.getRProject(), this.getRProject_Packages(), "project", null, 1, 1, RPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rClassEClass, RClass.class, "RClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRClass_Extends(), ecorePackage.getEString(), "extends", null, 0, 1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRClass_Implements(), ecorePackage.getEString(), "implements", null, 0, -1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRClass_Fields(), this.getRField(), this.getRField_Class(), "fields", null, 0, -1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRClass_Methods(), this.getRMethod(), this.getRMethod_Class(), "methods", null, 0, -1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRClass_Package(), this.getRPackage(), this.getRPackage_Classes(), "package", null, 1, 1, RClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(rClassEClass, ecorePackage.getEString(), "fqName", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(rFieldEClass, RField.class, "RField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRField_Type(), ecorePackage.getEString(), "type", null, 0, 1, RField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRField_Class(), this.getRClass(), this.getRClass_Fields(), "class", null, 1, 1, RField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(rFieldEClass, ecorePackage.getEString(), "fqName", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(rMethodEClass, RMethod.class, "RMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRMethod_ReturnType(), ecorePackage.getEString(), "returnType", null, 0, 1, RMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRMethod_ParameterTypes(), ecorePackage.getEString(), "parameterTypes", null, 0, -1, RMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRMethod_Class(), this.getRClass(), this.getRClass_Methods(), "class", null, 1, 1, RMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRMethod_ReferencedFields(), ecorePackage.getEString(), "referencedFields", null, 0, -1, RMethod.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRMethod_ReferencedMethods(), ecorePackage.getEString(), "referencedMethods", null, 0, -1, RMethod.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(rMethodEClass, ecorePackage.getEString(), "fqName", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(rDependencyEClass, RDependency.class, "RDependency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRDependency_From(), this.getRComponent(), this.getRComponent_Outgoing(), "from", null, 1, 1, RDependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRDependency_To(), this.getRComponent(), this.getRComponent_Incoming(), "to", null, 1, 1, RDependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRDependency_Type(), this.getRDependencyType(), "type", null, 0, 1, RDependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(rModifierEEnum, RModifier.class, "RModifier");
		addEEnumLiteral(rModifierEEnum, RModifier.PUBLIC);
		addEEnumLiteral(rModifierEEnum, RModifier.PROTECTED);
		addEEnumLiteral(rModifierEEnum, RModifier.PRIVATE);
		addEEnumLiteral(rModifierEEnum, RModifier.ABSTRACT);
		addEEnumLiteral(rModifierEEnum, RModifier.INTERFACE);
		addEEnumLiteral(rModifierEEnum, RModifier.FINAL);
		addEEnumLiteral(rModifierEEnum, RModifier.TRANSIENT);
		addEEnumLiteral(rModifierEEnum, RModifier.VOLATILE);
		addEEnumLiteral(rModifierEEnum, RModifier.NATIVE);
		addEEnumLiteral(rModifierEEnum, RModifier.STATIC);
		addEEnumLiteral(rModifierEEnum, RModifier.SYNCHRONIZED);
		addEEnumLiteral(rModifierEEnum, RModifier.ANONYMOUS);

		initEEnum(rDependencyTypeEEnum, RDependencyType.class, "RDependencyType");
		addEEnumLiteral(rDependencyTypeEEnum, RDependencyType.PROJECT_REFERENCES_PROJECT);
		addEEnumLiteral(rDependencyTypeEEnum, RDependencyType.CLASS_USES_CLASS);
		addEEnumLiteral(rDependencyTypeEEnum, RDependencyType.CLASS_EXTENDS_CLASS);
		addEEnumLiteral(rDependencyTypeEEnum, RDependencyType.FIELD_TYPE_CLASS);
		addEEnumLiteral(rDependencyTypeEEnum, RDependencyType.CLASS_IMPLEMENTS_CLASS);
		addEEnumLiteral(rDependencyTypeEEnum, RDependencyType.METHOD_RETURNTYPE_CLASS);
		addEEnumLiteral(rDependencyTypeEEnum, RDependencyType.METHOD_PARAMTYPE_CLASS);
		addEEnumLiteral(rDependencyTypeEEnum, RDependencyType.METHOD_CALL_METHOD);
		addEEnumLiteral(rDependencyTypeEEnum, RDependencyType.METHOD_REFERENCE_FIELD);
		addEEnumLiteral(rDependencyTypeEEnum, RDependencyType.METHOD_OVERRIDE_METHOD);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (getRMethod_ReferencedFields(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });
	}

} //RepomodelPackageImpl
