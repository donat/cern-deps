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

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelatedTo;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Modifiers;

public class FieldNode extends AbstractNode implements Field {
	

	/**
	 * source/binary signature.
	 */
	@Indexed
	private String signature;
	
	/**
	 * Container class reference
	 */
	@RelatedTo(direction=Direction.INCOMING, elementClass=ClassNode.class, type="FIELDS")
	private ApiClass apiClass;
	

	public FieldNode() {
		super(EnumSet.noneOf(Modifiers.class));
	}
	
	public FieldNode(EnumSet<Modifiers> modifiers) {
		super(modifiers);
	}

	public FieldNode(EnumSet<Modifiers> modifiers, String signature) {
		this(modifiers);
		this.signature = signature;
	}

	@Override
	public String getDisplayName() {
		return getSignature();
	}

	@Override
	public String getSignature() {
		return signature;
	}

	@Override
	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public ApiClass getApiClass() {
		return apiClass;
	}

	@Override
	public void setApiClass(ApiClass apiClass) {
		this.apiClass = apiClass;

	}

	@Override
	public boolean isPrivate() {
		return Modifiers.is(Modifiers.PRIVATE, modifiers);
	}

	@Override
	public String toString() {
		return String.format("Field(id=%s, signature=%s, version=%s)", id, signature, version);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
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
		FieldNode other = (FieldNode) obj;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		return true;
	}
}
