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
package cern.devtools.depanalysis.domain.impl;

import java.io.Serializable;
import java.util.Collection;

import cern.accsoft.commons.util.StringUtils;
import cern.devtools.depanalysis.domain.CodeElement;
import cern.devtools.depanalysis.domain.Dependency;
import cern.devtools.depanalysis.domain.DependencyType;
import cern.devtools.depanalysis.domain.TransitiveDependency;

public class TransitiveDependencyImpl extends DependencyImpl implements TransitiveDependency, Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Collection<Dependency> transitiveFrom;

	public TransitiveDependencyImpl(DependencyType type, CodeElement from, CodeElement to, Collection<Dependency> transitiveFrom) {
		super(type, from, to);
		this.transitiveFrom = transitiveFrom;
	}

	@Override
	public Collection<Dependency> getTransitiveFrom() {
		return transitiveFrom;
	}
	
	@Override
	public String toString() {
		String trans = StringUtils.arrayToDelimitedString(getTransitiveFrom().toArray(), " ");
		return String.format("TransDependency(type=%s, from=%s, to=%s, trans=[%s])", getType(), getFrom(), getTo(), trans);
	}
}
