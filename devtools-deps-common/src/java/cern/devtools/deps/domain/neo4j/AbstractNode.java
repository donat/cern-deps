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

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.Modifiers;

@NodeEntity
public abstract class AbstractNode implements CodeElement {
	/**
	 * Database id.
	 */
	@GraphId
	protected Long id;

	/**
	 * Right now we want to one single version per product.
	 */
	protected String version;

	protected int modifiers;

	public AbstractNode(EnumSet<Modifiers> modifiers) {
		for (Modifiers m : modifiers) {
			this.modifiers |= m.getValue();
		}
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<String> getVersions() {
		return Arrays.asList(version);
		// if (versionNodes == null) {
		// versionNodes = new HashSet<VersionNode>();
		// }
		//
		// List<String> result = new LinkedList<String>();
		// for (VersionNode vn : versionNodes) {
		// result.add(vn.getVersion());
		// }
		//
		// return result;
	}

	public void addVersions(String... versions) {
		if (versions.length > 0) {
			this.version = versions[0];
		}

		// if (versionNodes == null) {
		// versionNodes = new HashSet<VersionNode>();
		// }
		// for (String v : versions) {
		// versionNodes.add(new VersionNode(v));
		// }
	}


	// public Set<VersionNode> getVersionNodes() {
	// return versionNodes;
	// }
	//
	// public void setVersionNodes(Set<VersionNode> versionNodes) {
	// this.versionNodes = versionNodes;
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AbstractNode other = (AbstractNode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
