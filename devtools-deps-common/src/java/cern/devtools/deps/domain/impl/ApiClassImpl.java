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
package cern.devtools.deps.domain.impl;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.Product;

/**
 * Domain object representing one single java class file.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public class ApiClassImpl extends AbstractCodeElement implements ApiClass {

	private static final long serialVersionUID = 8681564009035312041L;

	/**
	 * Simple name of the class without the package declaration.
	 */
	private String simpleName;

	/**
	 * Containing package name.
	 */
	private String packageName;

	/**
	 * Fully qualified name.
	 */
	private String fqName;
	/**
	 * FQ name of the superclass.
	 */
	private String extendz;

	/**
	 * FQ name of the implemented interfaces divided by a space character.
	 */
	private String implementz;

	/**
	 * Container product.
	 */
	private Product product;

	/**
	 * Defined methods in the class.
	 */
	private List<Method> methods;

	/**
	 * Defined fields in the class.
	 */
	private List<Field> fields;

	/**
	 * Names of the referenced classes.
	 */
	private List<String> referencedClassse;

	public ApiClassImpl(String fqName, EnumSet<Modifiers> modifiers) {
		super(modifiers);
		String[] name = separatePackageNameFromsimplename(fqName);
		this.packageName = name[0];
		this.simpleName = name[1];
	}

	public ApiClassImpl(String fqName, String extendz, String implementz, EnumSet<Modifiers> modifiers) {
		this(fqName, modifiers);
		this.extendz = extendz;
		this.implementz = implementz;
	}

	public String getFqName() {		
		if (fqName == null) {
			if ((packageName != null) && (!"".equals(packageName))) {
				fqName = packageName + "." + simpleName;
			} else {
				fqName = simpleName;
			}
		}
		return fqName;

	}

	@Override
	public String getSimpleName() {
		return simpleName;
	}

	@Override
	public String getPackageName() {
		return packageName;
	}

	@Override
	public String getExtends() {
		return extendz;
	}

	@Override
	public String getImplements() {
		return implementz;
	}

	@Override
	public Product getProduct() {
		return product;
	}

	@Override
	public void setProduct(Product product) {
		
		this.product = product;
	}

	@Override
	public List<Method> getMethods() {
		return methods == null ? methods = new LinkedList<Method>() : methods;
	}

	@Override
	public List<Field> getFields() {
		return fields == null ? fields = new LinkedList<Field>() : fields;
	}

	@Override
	public String getDisplayName() {
		return getFqName();
	}

	@Override
	public String getSourcePath() {
		String path = getFqName();
		if (path.contains("$")) {
			path = path.split("\\$")[0];
		}
		path = path.replace('.', '/') + ".java";
		return path;
	}

	/**
	 * Helper method, which sets the name and package name by parsing the argument.
	 * 
	 * @param fqName the fully qualified name to parse
	 * @return Two element array: {[0] => package_name, [1] => class_name}
	 */
	private static String[] separatePackageNameFromsimplename(String fqName) {
		
		// Null check
		if (fqName == null) {
			return new String[] { "", "" };
		}

		String[] na = fqName.split("\\.");
		// The simple name is the string after the last '.'.
		String sn = na[na.length - 1];

		// The package name is original part minus the simple name part.
		String pn = "";
		if (!sn.equals(fqName)) {
			pn = fqName.substring(0, fqName.length() - sn.length() - 1);
		}

		return new String[] { pn, sn };
	}

	@Override
	public boolean isPrivate() {
		return modifiers.contains(Modifiers.PRIVATE);
	}

	@Override
	public boolean isAnonymous() {
		return modifiers.contains(Modifiers.ANONYMOUS);
	}

	@Override
	public List<String> getReferencedClasses() {
		return referencedClassse == null ? referencedClassse = new LinkedList<String>() : referencedClassse;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((extendz == null) ? 0 : extendz.hashCode());
		result = prime * result + ((fqName == null) ? 0 : fqName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((implementz == null) ? 0 : implementz.hashCode());
		result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
		result = prime * result + ((simpleName == null) ? 0 : simpleName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiClassImpl other = (ApiClassImpl) obj;
		if (extendz == null) {
			if (other.extendz != null)
				return false;
		} else if (!extendz.equals(other.extendz))
			return false;
		if (fqName == null) {
			if (other.fqName != null)
				return false;
		} else if (!fqName.equals(other.fqName))
			return false;
		if (id != other.id)
			return false;
		if (implementz == null) {
			if (other.implementz != null)
				return false;
		} else if (!implementz.equals(other.implementz))
			return false;
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;
		if (simpleName == null) {
			if (other.simpleName != null)
				return false;
		} else if (!simpleName.equals(other.simpleName))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(ApiClassImpl) " + getFqName();
	}
}
