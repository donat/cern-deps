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
package cern.devtools.deps.domain.creation;

import java.util.List;

import cern.devtools.depanalysis.repomodel.RField;
import cern.devtools.depanalysis.repomodel.RMethod;
import cern.devtools.depanalysis.repomodel.RModifier;
import cern.devtools.depanalysis.repomodel.RProject;

/**
 * Interface describing, how the domain objects should be instantiated.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 * 
 */
public interface DomainObjectCreator {

	RProject createProject(String name);
	
	RProject createProject(String name, String version, String containingFolders, String jarPath);
	
	RField createField(String name, String signature, String type, List<RModifier> modifiers);

	RMethod createMethod(String name, String signature, String returnType, List<String> argumentTypes,
			List<RModifier> modifiers);

	/**
	 * Returs the metainformation about the creator: the name representation of the implementation.
	 * 
	 * @return
	 */
	public String creatorName();
	//
	// /**
	// * Creates {@link Product} instances.
	// *
	// * @param name
	// * Name of the product.
	// * @return The instance.
	// */
	// public Product createProduct(String name);
	//
	// /**
	// * Creates {@link Product} instances.
	// *
	// * @param name
	// * Name of the product.
	// * @param version
	// * Initial version of the product. The {@link Product#getVersions()} will have one element.
	// * @param containingFolders
	// * The folders where the product could be found.
	// * @param storeLocation
	// * The location of the jar file.
	// * @return The instance.
	// */
	// public Product createProduct(String name, String version, String containingFolders, String storeLocation);
	//
	// /**
	// * Creates {@link Product} instances.
	// *
	// * @param name
	// * Name of the product.
	// * @param versions
	// * The versions of the product.
	// * @param containingFolders
	// * The folders where the product could be found.
	// * @param storeLocation
	// * The location of the jar file.
	// * @return The instance.
	// */
	// public Product createProduct(String name, List<String> versions, String containingFolders, String storeLocation);
	//
	// /**
	// * Creates {@link ApiClass} instance.
	// *
	// * @param fqName
	// * The fully qualified name of the class.
	// * @return The instance.
	// */
	// public ApiClass createApiClass(String fqName, EnumSet<Modifiers> modifiers);
	//
	// /**
	// * Creates {@link ApiClass} instance.
	// *
	// * @param fqName
	// * Fully qualified name in source format.
	// * @param extendz
	// * The fully qualified name of the superclass. The {@link java.lang.Object} should not be indicated here.
	// * @param implementz
	// * The list of the implemented interfaces separated by ' ' character.
	// * @return The instance.
	// */
	// public ApiClass createApiClass(String fqName, String extendz, String implementz, EnumSet<Modifiers> modifiers);
	//
	// /**
	// * Creates {@link Field} instance.
	// *
	// * @param Signature
	// * The source formatted signature of the field.
	// * @return The instance.
	// */
	//
	// public Field createField(String signature, EnumSet<Modifiers> modifiers);
	//
	// /**
	// * Creates {@link Method} instance.
	// *
	// * @param signature
	// * Signature The source formatted signature of the method.
	// * @param static Flags if the method is a static one.
	// * @return The instance.
	// */
	// public Method createMethod(String signature, EnumSet<Modifiers> modifiers);
	//
	// /**
	// * Creates a simple (non-transitive) dependency.
	// *
	// * @param type
	// * Type of the dependency.
	// * @param from
	// * The outgoing part of the dependency.
	// * @param to
	// * The incoming part of the dependency.
	// * @return The created object.
	// */
	// public Dependency createDependency(DependencyType type, CodeElement from, CodeElement to);
	//
	// /**
	// * Creates a transitive dependency.
	// *
	// * @param type
	// * Type of the dependency.
	// * @param from
	// * The outgoing part of the dependency.
	// * @param to
	// * The incoming part of the dependency.
	// * @param transitiveFrom
	// * The set of dependencies which has an outgoing dependency pointing to the 'from' element on the
	// * parameter list.
	// * @return The created object.
	// */
	// public Dependency createDependency(DependencyType type, CodeElement from, CodeElement to,
	// Collection<Dependency> transitiveFrom);

}
