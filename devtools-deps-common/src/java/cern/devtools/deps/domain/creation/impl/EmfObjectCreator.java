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
package cern.devtools.deps.domain.creation.impl;

import java.util.List;

import cern.devtools.depanalysis.repomodel.RField;
import cern.devtools.depanalysis.repomodel.RMethod;
import cern.devtools.depanalysis.repomodel.RModifier;
import cern.devtools.depanalysis.repomodel.RepomodelFactory;
import cern.devtools.deps.domain.creation.DomainObjectCreator;

/**
 * Implementation of {@link DomainObjectCreator}; returns EMF implementation object.
 *
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public final class EmfObjectCreator implements DomainObjectCreator {

	@Override
	public RField createField(String name, String signature, String type,
			List<RModifier> modifiers) {
		RField rf = RepomodelFactory.eINSTANCE.createRField();
		rf.setName(name);
		rf.setSignature(signature);
		rf.setType(type);
		rf.getModifiers().addAll(modifiers);
		return rf;
		
	}
	
	public RMethod createMethod(String name, String signature, String returnType, List<String> argumentTypes,
			List<RModifier> modifiers) {
		RMethod rm = RepomodelFactory.eINSTANCE.createRMethod();
		rm.setName(name);
		rm.setReturnType(returnType);
		rm.setSignature(signature);
		rm.getParameterTypes().addAll(argumentTypes);
		rm.getModifiers().addAll(modifiers);
		return rm;
	}

	@Override
	public String creatorName() {
		return "EMF";
	}
//
//	@Override
//	public Product createProduct(String name) {
//		RProject p = RepomodelFactory.eINSTANCE.createRProject();
//		p.setName(name);
//		p.addVersions("<NOVERSION>");
//		return p;
//	}
//
//	@Override
//	public Product createProduct(String name, String version, String containingFolders, String storeLocation) {
//		RProject p = RepomodelFactory.eINSTANCE.createRProject();
//		p.setName(name);
//		p.addVersions(version);
//		p.setContainingFolders(containingFolders);
//		p.setProductPath(storeLocation);
//		return p;
//	}
//
//	@Override
//	public Product createProduct(String name, List<String> versions, String containingFolders, String storeLocation) {
//		RProject p = RepomodelFactory.eINSTANCE.createRProject();
//		p.setName(name);
//		p.addVersions(versions.toArray(new String[0]));
//		p.setContainingFolders(containingFolders);
//		p.setProductPath(storeLocation);
//		return p;
//	}
//
//	@Override
//	public ApiClass createApiClass(String fqName, EnumSet<Modifiers> modifiers) {
//		String[] names = separatePackageNameFromsimplename(fqName);
//		RClass ac = (RClass) RepomodelFactory.eINSTANCE.createRClass();
//		ac.setSimpleName(names[1]);
//		ac.setPackageName(names[0]);
//		ac.setFqName(fqName);
//		ac.setReferencedClasses(new BasicEList<String>());
//		ac.setPrivate(modifiers.contains(Modifiers.PRIVATE));
//		ac.setAnonymous(modifiers.contains(Modifiers.ANONYMOUS));
//		return ac;
//	}
//
//	@Override
//	public ApiClass createApiClass(String fqName, String extendz, String implementz, EnumSet<Modifiers> modifiers) {
//		String[] names = separatePackageNameFromsimplename(fqName);
//		RClass ac = (RClass) RepomodelFactory.eINSTANCE.createRClass();
//		ac.setSimpleName(names[1]);
//		ac.setPackageName(names[0]);
//		ac.setExtends(extendz);
//		ac.setImplements(implementz);
//		ac.setFqName(fqName);
//		ac.setReferencedClasses(new BasicEList<String>());
//		ac.setPrivate(modifiers.contains(Modifiers.PRIVATE));
//		ac.setAnonymous(modifiers.contains(Modifiers.ANONYMOUS));
//		return ac;
//	}
//
//	@Override
//	public Field createField(String signature, EnumSet<Modifiers> modifiers) {
//		RField f = (RField) RepomodelFactory.eINSTANCE.createRField();
//		f.setSignature(signature);
//		f.setPrivate(modifiers.contains(Modifiers.PRIVATE));
//		return f;
//	}
//
//	@Override
//	public Method createMethod(String signature, EnumSet<Modifiers> modifiers) {
//		RMethod m = (RMethod) RepomodelFactory.eINSTANCE.createRMethod();
//		m.setSignature(signature);
//		m.setReferencedFields(new BasicEList<String>());
//		m.setReferencedMethods(new BasicEList<String>());
//		m.setPrivate(modifiers.contains(Modifiers.PRIVATE));
//		m.setStatic(modifiers.contains(Modifiers.STATIC));
//		return m;
//	}
//
//	@Override
//	public Dependency createDependency(DependencyType type, CodeElement from, CodeElement to) {
//		RDependency d = (RDependency) RepomodelFactory.eINSTANCE.createRDependency();
//		d.setRFrom((RCodeElement) from);
//		d.setRTo((RCodeElement) to);
//		d.setDepType(type.value());
//		return d;
//	}
//
//	@Override
//	public Dependency createDependency(DependencyType type, CodeElement from, CodeElement to,
//			Collection<Dependency> transitiveFrom) {
//		RTransitiveDependency d = (RTransitiveDependency) RepomodelFactory.eINSTANCE.createRTransitiveDependency();
//		d.setRFrom((RCodeElement) from);
//		d.setRTo((RCodeElement) to);
//		d.setDepType(type.value());
//		d.getTransitiveFrom().addAll(transitiveFrom);
//		return d;
//	}
//
//	/**
//	 * Helper method, which sets the name and package name by parsing the argument. FIXME: this method should me moved
//	 * to the domain object interface.
//	 *
//	 * @param fqName
//	 *            the fully qualified name to parse
//	 * @return array; [0] package name or if not exists then empty string, [1] class name
//	 */
//	public static String[] separatePackageNameFromsimplename(String fqName) {
//		// Null check
//		if (fqName == null) {
//			return new String[] { "", "" };
//		}
//
//		String[] na = fqName.split("\\.");
//		// The simple name is the string after the last '.'.
//		String sn = na[na.length - 1];
//
//		// The package name is original part minus the simple name part.
//		String pn = "";
//		if (!sn.equals(fqName)) {
//			pn = fqName.substring(0, fqName.length() - sn.length() - 1);
//		}
//
//		return new String[] { pn, sn };
//	}
}
