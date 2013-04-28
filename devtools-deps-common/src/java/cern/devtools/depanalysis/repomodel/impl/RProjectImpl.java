/**
 */
package cern.devtools.depanalysis.repomodel.impl;

import cern.devtools.depanalysis.repomodel.RDependency;
import cern.devtools.depanalysis.repomodel.RPackage;
import cern.devtools.depanalysis.repomodel.RProject;
import cern.devtools.depanalysis.repomodel.RRepository;
import cern.devtools.depanalysis.repomodel.RepomodelPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RProject</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RProjectImpl#getPackages <em>Packages</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RProjectImpl#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RProjectImpl#getRepository <em>Repository</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RProjectImpl#getContainingFolders <em>Containing Folders</em>}</li>
 *   <li>{@link cern.devtools.depanalysis.repomodel.impl.RProjectImpl#getJarPath <em>Jar Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RProjectImpl extends RVersionedImpl implements RProject {
	/**
	 * The cached value of the '{@link #getPackages() <em>Packages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackages()
	 * @generated
	 * @ordered
	 */
	protected EList<RPackage> packages;

	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<RDependency> dependencies;

	/**
	 * The default value of the '{@link #getContainingFolders() <em>Containing Folders</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainingFolders()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTAINING_FOLDERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContainingFolders() <em>Containing Folders</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainingFolders()
	 * @generated
	 * @ordered
	 */
	protected String containingFolders = CONTAINING_FOLDERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getJarPath() <em>Jar Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJarPath()
	 * @generated
	 * @ordered
	 */
	protected static final String JAR_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJarPath() <em>Jar Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJarPath()
	 * @generated
	 * @ordered
	 */
	protected String jarPath = JAR_PATH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RProjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RepomodelPackage.Literals.RPROJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<RPackage> getPackages() {
		if (packages == null) {
			packages = new EObjectContainmentWithInverseEList.Resolving<RPackage>(RPackage.class, this, RepomodelPackage.RPROJECT__PACKAGES, RepomodelPackage.RPACKAGE__PROJECT);
		}
		return packages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<RDependency> getDependencies() {
		if (dependencies == null) {
			dependencies = new EObjectContainmentEList.Resolving<RDependency>(RDependency.class, this, RepomodelPackage.RPROJECT__DEPENDENCIES);
		}
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RRepository getRepository() {
		if (eContainerFeatureID() != RepomodelPackage.RPROJECT__REPOSITORY) return null;
		return (RRepository)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RRepository basicGetRepository() {
		if (eContainerFeatureID() != RepomodelPackage.RPROJECT__REPOSITORY) return null;
		return (RRepository)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRepository(RRepository newRepository, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRepository, RepomodelPackage.RPROJECT__REPOSITORY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepository(RRepository newRepository) {
		if (newRepository != eInternalContainer() || (eContainerFeatureID() != RepomodelPackage.RPROJECT__REPOSITORY && newRepository != null)) {
			if (EcoreUtil.isAncestor(this, newRepository))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRepository != null)
				msgs = ((InternalEObject)newRepository).eInverseAdd(this, RepomodelPackage.RREPOSITORY__PROJECTS, RRepository.class, msgs);
			msgs = basicSetRepository(newRepository, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RPROJECT__REPOSITORY, newRepository, newRepository));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContainingFolders() {
		return containingFolders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainingFolders(String newContainingFolders) {
		String oldContainingFolders = containingFolders;
		containingFolders = newContainingFolders;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RPROJECT__CONTAINING_FOLDERS, oldContainingFolders, containingFolders));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJarPath() {
		return jarPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJarPath(String newJarPath) {
		String oldJarPath = jarPath;
		jarPath = newJarPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RepomodelPackage.RPROJECT__JAR_PATH, oldJarPath, jarPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RepomodelPackage.RPROJECT__PACKAGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPackages()).basicAdd(otherEnd, msgs);
			case RepomodelPackage.RPROJECT__REPOSITORY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRepository((RRepository)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RepomodelPackage.RPROJECT__PACKAGES:
				return ((InternalEList<?>)getPackages()).basicRemove(otherEnd, msgs);
			case RepomodelPackage.RPROJECT__DEPENDENCIES:
				return ((InternalEList<?>)getDependencies()).basicRemove(otherEnd, msgs);
			case RepomodelPackage.RPROJECT__REPOSITORY:
				return basicSetRepository(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case RepomodelPackage.RPROJECT__REPOSITORY:
				return eInternalContainer().eInverseRemove(this, RepomodelPackage.RREPOSITORY__PROJECTS, RRepository.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RepomodelPackage.RPROJECT__PACKAGES:
				return getPackages();
			case RepomodelPackage.RPROJECT__DEPENDENCIES:
				return getDependencies();
			case RepomodelPackage.RPROJECT__REPOSITORY:
				if (resolve) return getRepository();
				return basicGetRepository();
			case RepomodelPackage.RPROJECT__CONTAINING_FOLDERS:
				return getContainingFolders();
			case RepomodelPackage.RPROJECT__JAR_PATH:
				return getJarPath();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RepomodelPackage.RPROJECT__PACKAGES:
				getPackages().clear();
				getPackages().addAll((Collection<? extends RPackage>)newValue);
				return;
			case RepomodelPackage.RPROJECT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection<? extends RDependency>)newValue);
				return;
			case RepomodelPackage.RPROJECT__REPOSITORY:
				setRepository((RRepository)newValue);
				return;
			case RepomodelPackage.RPROJECT__CONTAINING_FOLDERS:
				setContainingFolders((String)newValue);
				return;
			case RepomodelPackage.RPROJECT__JAR_PATH:
				setJarPath((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RepomodelPackage.RPROJECT__PACKAGES:
				getPackages().clear();
				return;
			case RepomodelPackage.RPROJECT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case RepomodelPackage.RPROJECT__REPOSITORY:
				setRepository((RRepository)null);
				return;
			case RepomodelPackage.RPROJECT__CONTAINING_FOLDERS:
				setContainingFolders(CONTAINING_FOLDERS_EDEFAULT);
				return;
			case RepomodelPackage.RPROJECT__JAR_PATH:
				setJarPath(JAR_PATH_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RepomodelPackage.RPROJECT__PACKAGES:
				return packages != null && !packages.isEmpty();
			case RepomodelPackage.RPROJECT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case RepomodelPackage.RPROJECT__REPOSITORY:
				return basicGetRepository() != null;
			case RepomodelPackage.RPROJECT__CONTAINING_FOLDERS:
				return CONTAINING_FOLDERS_EDEFAULT == null ? containingFolders != null : !CONTAINING_FOLDERS_EDEFAULT.equals(containingFolders);
			case RepomodelPackage.RPROJECT__JAR_PATH:
				return JAR_PATH_EDEFAULT == null ? jarPath != null : !JAR_PATH_EDEFAULT.equals(jarPath);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (containingFolders: ");
		result.append(containingFolders);
		result.append(", jarPath: ");
		result.append(jarPath);
		result.append(')');
		return result.toString();
	}

} //RProjectImpl
