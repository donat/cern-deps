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

import java.util.Collection;

import cern.devtools.depanalysis.domain.CodeElement;
import cern.devtools.depanalysis.domain.Dependency;
import cern.devtools.depanalysis.domain.DependencyType;
import cern.devtools.depanalysis.domain.TransitiveDependency;

public class TransitiveDependencyRelation extends DependencyRelation implements TransitiveDependency {

	private final DependencyRelation dr;
	private final Collection<Dependency> trans;

	public TransitiveDependencyRelation(DependencyRelation dr, Collection<Dependency> trans) {
		this.dr = dr;
		this.trans = trans;
	}

	@Override
	public DependencyType getType() {
		return dr.getType();
	}

	@Override
	public CodeElement getFrom() {
		return dr.getFrom();
	}

	@Override
	public CodeElement getTo() {
		return dr.getTo();
	}

	@Override
	public Collection<Dependency> getTransitiveFrom() {
		return trans;
	}

}
