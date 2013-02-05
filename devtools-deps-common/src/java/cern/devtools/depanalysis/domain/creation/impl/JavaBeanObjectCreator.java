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
package cern.devtools.depanalysis.domain.creation.impl;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

import cern.devtools.depanalysis.domain.ApiClass;
import cern.devtools.depanalysis.domain.CodeElement;
import cern.devtools.depanalysis.domain.Dependency;
import cern.devtools.depanalysis.domain.DependencyType;
import cern.devtools.depanalysis.domain.Field;
import cern.devtools.depanalysis.domain.Method;
import cern.devtools.depanalysis.domain.Modifiers;
import cern.devtools.depanalysis.domain.Product;
import cern.devtools.depanalysis.domain.creation.DomainObjectCreator;
import cern.devtools.depanalysis.domain.impl.ApiClassImpl;
import cern.devtools.depanalysis.domain.impl.DependencyImpl;
import cern.devtools.depanalysis.domain.impl.FieldImpl;
import cern.devtools.depanalysis.domain.impl.MethodImpl;
import cern.devtools.depanalysis.domain.impl.ProductImpl;
import cern.devtools.depanalysis.domain.impl.TransitiveDependencyImpl;

/**
 * Implementation of {@link DomainObjectCreator}; returns POJO objects.
 *
 * @author Donat Csikos
 */
public final class JavaBeanObjectCreator implements DomainObjectCreator {
	@Override
	public String creatorName() {
		return "javabean";
	}

	@Override
	public Product createProduct(String name) {
		return new ProductImpl(name);
	}

	@Override
	public Product createProduct(String name, String version, String containingFolders, String storeLocation) {
		return new ProductImpl(name, version, containingFolders, storeLocation);
	}

	@Override
	public Product createProduct(String name, List<String> versions, String containingFolders, String storeLocation) {
		return new ProductImpl(name, versions, containingFolders, storeLocation);
	}

	@Override
	public ApiClass createApiClass(String fqName, EnumSet<Modifiers> modifiers) {
		ApiClassImpl ac = new ApiClassImpl(fqName, modifiers);
		return ac;
	}

	@Override
	public ApiClass createApiClass(String fqName, String extendz, String implementz, EnumSet<Modifiers> modifiers) {
		ApiClassImpl ac = new ApiClassImpl(fqName, extendz, implementz, modifiers);
		return ac;
	}

	@Override
	public Field createField(String signature, EnumSet<Modifiers> modifiers) {
		return new FieldImpl(signature, modifiers);
	}

	@Override
	public Method createMethod(String signature, EnumSet<Modifiers> modifiers) {
		return new MethodImpl(signature, modifiers);
	}

	@Override
	public Dependency createDependency(DependencyType type, CodeElement from, CodeElement to) {
		return new DependencyImpl(type, from, to);
	}

	@Override
	public Dependency createDependency(DependencyType type, CodeElement from, CodeElement to,
			Collection<Dependency> transitiveFrom) {
		return new TransitiveDependencyImpl(type, from, to, transitiveFrom);
	}
}
