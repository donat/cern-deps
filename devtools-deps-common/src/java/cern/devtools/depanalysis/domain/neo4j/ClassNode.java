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
package cern.devtools.depanalysis.domain.neo4j;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelatedTo;

import cern.devtools.depanalysis.domain.ApiClass;
import cern.devtools.depanalysis.domain.Field;
import cern.devtools.depanalysis.domain.Method;
import cern.devtools.depanalysis.domain.Modifiers;
import cern.devtools.depanalysis.domain.Product;

public class ClassNode extends AbstractNode implements ApiClass {

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
	@Indexed
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
	 * Defined methods in the class.
	 */
	@RelatedTo(elementClass=MethodNode.class, direction=Direction.OUTGOING, type="METHODS")
	private Set<Method> methods;
	
	/**
	 * Defined fields in the class.
	 */
	@RelatedTo(elementClass=FieldNode.class, direction=Direction.OUTGOING, type="FIELD")
	private Set<Field> fields;
	
	/**
	 * Container product.
	 */
	@RelatedTo(elementClass=ProductNode.class, direction=Direction.INCOMING, type="CLASSES")
	private Product product;

	/**
	 * Names of the referenced classes.
	 */
	@Transient
	private List<String> referencedClasss;
	
	public ClassNode() {
		super(EnumSet.noneOf(Modifiers.class));
	}

	public ClassNode(String fqName, EnumSet<Modifiers> modifiers) {
		super(modifiers);
		String[] name = separatePackageNameFromsimplename(fqName);
		this.packageName = name[0];
		this.simpleName = name[1];
		this.fqName = fqName;
	}

	public ClassNode(String fqName, String extendz, String implementz, EnumSet<Modifiers> modifiers) {
		this(fqName, modifiers);
		this.extendz = extendz;
		this.implementz = implementz;
	}

	public String getFqName() {
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
		return Modifiers.is(Modifiers.PRIVATE, modifiers);
	}

	@Override
	public boolean isAnonymous() {
		return Modifiers.is(Modifiers.ANONYMOUS, modifiers);
	}

	@Override
	public List<String> getReferencedClasses() {
		return referencedClasss == null ? referencedClasss = new LinkedList<String>() : referencedClasss;
	}

	@Override
	public Set<Method> getMethods() {
		return methods == null ? methods = new HashSet<Method>() : methods;
	}

	@Override
	public Set<Field> getFields() {
		return fields == null ? fields = new HashSet<Field>() : fields;
	}
	
	@Override
	public String toString() {
		return String.format("Class(id=%s, fqName=%s, version=%s)", id, fqName, version);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fqName == null) ? 0 : fqName.hashCode());
		result = prime * result + ((implementz == null) ? 0 : implementz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassNode other = (ClassNode) obj;
		if (fqName == null) {
			if (other.fqName != null)
				return false;
		} else if (!fqName.equals(other.fqName))
			return false;
		if (implementz == null) {
			if (other.implementz != null)
				return false;
		} else if (!implementz.equals(other.implementz))
			return false;
		return true;
	}
}
