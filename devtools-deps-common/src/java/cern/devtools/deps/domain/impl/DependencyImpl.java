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

import java.io.Serializable;

import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.DependencyType;

public class DependencyImpl implements Dependency, Serializable {

	private static final long serialVersionUID = 1L;
	
	private final DependencyType type;
	private final CodeElement from;
	private final CodeElement to;

	public DependencyImpl(DependencyType type, CodeElement from, CodeElement to) {
		this.type = type;
		this.from = from;
		this.to = to;
		
	}
	
	@Override
	public DependencyType getType() {
		return type;
	}

	@Override
	public CodeElement getFrom() {
		return from;
	}

	@Override
	public CodeElement getTo() {
		return to;
	}
	
	@Override
	public String toString() {
		return "Dependency(type=" + getType() + ", from=" + getFrom() + ", to=" + getTo() + ")";
	}

}
