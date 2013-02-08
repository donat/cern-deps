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
package cern.devtools.deps.domain.neo4j;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelatedTo;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.Product;

public class ProductNode extends AbstractNode implements Product {

	@Indexed
	private String name;
	private String containingFolders;
	private String storeLocation;

	@RelatedTo(direction = Direction.OUTGOING, elementClass = ClassNode.class, type = "CLASSES")
	private Set<ApiClass> classes;

	public ProductNode() {
		super(EnumSet.noneOf(Modifiers.class));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContainingFolders() {
		return containingFolders;
	}

	public void setContainingFolders(String containingFolders) {
		this.containingFolders = containingFolders;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}

	@Override
	public String getDisplayName() {
		return name;
	}

	@Override
	public Set<ApiClass> getClasses() {
		if (classes == null)  {
			classes = new HashSet<ApiClass>();
		}
		return classes;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer(String.format("ProductNode(id=%s, name=%s, version=%s)", id, name, version));
		buf.append("\n");
		for (ApiClass ac : getClasses()) {
			buf.append(ac.toString());
			buf.append("\n");
			
			for (Method m : ac.getMethods()) {
				buf.append(m.toString());
				buf.append("\n");
			}
			for (Field f : ac.getFields()) {
				buf.append(f.toString());
				buf.append("\n");
			}
		}
		
		return buf.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((containingFolders == null) ? 0 : containingFolders.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((storeLocation == null) ? 0 : storeLocation.hashCode());
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
		ProductNode other = (ProductNode) obj;
		if (containingFolders == null) {
			if (other.containingFolders != null)
				return false;
		} else if (!containingFolders.equals(other.containingFolders))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (storeLocation == null) {
			if (other.storeLocation != null)
				return false;
		} else if (!storeLocation.equals(other.storeLocation))
			return false;
		return true;
	}
}
