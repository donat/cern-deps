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
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import cern.devtools.depanalysis.domain.CodeElement;
import cern.devtools.depanalysis.domain.Modifiers;

/**
 * Template class to extract common implementation details from the final classes. 
 * 
 * @author Donat Csikos
 *
 */
public abstract class AbstractCodeElement implements CodeElement, Serializable {
	
	/**
	 * Serialization identifier. More details at {@link Serializable}.
	 */
	protected static final long serialVersionUID = -154216915654643618L;
	
	/**
	 * Database id.
	 */
	protected long id;
	
	/**
	 * Field to store the display name string.
	 */
	protected String displayName;
	
	/**
	 * The version numbers, where the class instance is present.
	 */
	private List<String> versions;
	
	protected final EnumSet<Modifiers> modifiers;
	
	protected AbstractCodeElement(EnumSet<Modifiers> modifiers) { 
		this.modifiers = modifiers;
	}
	
	@Override
	public final long getId() {
		return id;
	}

	@Override
	public final void setId(long id) {
		this.id = id;
	}
	
	@Override
	public final List<String> getVersions() {
		return versions == null ? versions = new LinkedList<String>() : versions;
	}
	
	@Override
	public void addVersions(String... versions) {
		this.getVersions().addAll(Arrays.asList(versions));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		AbstractCodeElement other = (AbstractCodeElement) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
