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

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import cern.devtools.depanalysis.domain.CodeElement;
import cern.devtools.depanalysis.domain.Dependency;
import cern.devtools.depanalysis.domain.DependencyType;

@RelationshipEntity(type="DEPENDS_ON")
public class DependencyRelation implements Dependency {
	@SuppressWarnings("unused")
	@GraphId
	private Long id;
	
	@StartNode
	@Fetch
	@Indexed
	private AbstractNode from;

	@EndNode
	@Fetch
	@Indexed
	private AbstractNode to;

	DependencyType type;

	public DependencyRelation() {
	}

	public DependencyRelation(CodeElement from, CodeElement to, DependencyType type) {
		if (!(from instanceof AbstractNode) || !(to instanceof AbstractNode)) {
			throw new RuntimeException("Invalid domain object");
		}
		this.from = (AbstractNode) from;
		this.to = (AbstractNode) to;
		this.type = type;
	}

	public CodeElement getFrom() {
		return from;
	}

	public void setFrom(AbstractNode from) {
		this.from = from;
	}

	public CodeElement getTo() {
		return to;
	}

	public void setTo(AbstractNode to) {
		this.to = to;
	}

	public DependencyType getType() {
		return type;
	}

	public void setType(DependencyType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type.name() + ": " + getFrom().getDisplayName() + " -> " + getTo().getDisplayName();
	}

}
