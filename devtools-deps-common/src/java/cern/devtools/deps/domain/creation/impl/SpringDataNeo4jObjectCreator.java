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
import cern.devtools.depanalysis.repomodel.RProject;
import cern.devtools.deps.domain.creation.DomainObjectCreator;

/**
 * Implementation of {@link DomainObjectCreator}; returns Neo4j graph nodes and relationships.
 *
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public class SpringDataNeo4jObjectCreator implements DomainObjectCreator {

	public RField createField(String name, String signature, String type,
			List<RModifier> modifiers) {
		return null;
	}

	@Override
	public String creatorName() {
		return "SPRING_DATA_NEO4J";
	}
//
//	@Override
//	public Product createProduct(String name) {
//		ProductNode gp = new ProductNode();
//		gp.setName(name);
//		gp.addVersions("<NOVERSION>");
//		return gp;
//	}
//
//	@Override
//	public Product createProduct(String name, String version, String containingFolders, String storeLocation) {
//		ProductNode gp = new ProductNode();
//		gp.setName(name);
//		gp.addVersions(version);
//		gp.setContainingFolders(containingFolders);
//		gp.setStoreLocation(storeLocation);
//		return gp;
//	}
//
//	@Override
//	public Product createProduct(String name, List<String> versions, String containingFolders, String storeLocation) {
//		ProductNode gp = new ProductNode();
//		gp.setName(name);
//		gp.addVersions(versions.toArray(new String[0]));
//		gp.setContainingFolders(containingFolders);
//		gp.setStoreLocation(storeLocation);
//		return gp;
//	}
//
//	@Override
//	public ApiClass createApiClass(String fqName, EnumSet<Modifiers> modifiers) {
//		ClassNode cn = new ClassNode(fqName, modifiers);
//		return cn;
//	}
//
//	@Override
//	public ApiClass createApiClass(String fqName, String extendz, String implementz, EnumSet<Modifiers> modifiers) {
//		ClassNode cn = new ClassNode(fqName, extendz, implementz, modifiers);
//		return cn;
//	}
//
//	@Override
//	public Field createField(String signature, EnumSet<Modifiers> modifiers) {
//		return new FieldNode(modifiers, signature);
//	}
//
//	@Override
//	public Method createMethod(String signature, EnumSet<Modifiers> modifiers) {
//		return new MethodNode(modifiers, signature);
//	}
//
//	@Override
//	public Dependency createDependency(DependencyType type, CodeElement from, CodeElement to) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Dependency createDependency(DependencyType type, CodeElement from, CodeElement to,
//			Collection<Dependency> transitiveFrom) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public RMethod createMethod(String name, String signature, String returnType, List<String> argumentTypes,
			List<RModifier> modifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RProject createProject(String name, String version, String containingFolders, String jarPath) {
		// TODO Auto-generated method stub
		return null;
	}
}
