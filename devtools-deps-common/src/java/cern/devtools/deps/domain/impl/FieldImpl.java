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

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Modifiers;

/**
 * Domain object representing a field in the class.
 * 
 * @author Donat Csikos
 * 
 */
public class FieldImpl extends AbstractCodeElement implements Field {

	private static final long serialVersionUID = -4568301437874588293L;
	
	/**
	 * source/binary signature.
	 */
	private String signature;
	
	/**
	 * Container class reference
	 */
	private ApiClass apiClass;

	/**
	 * Constructor
	 * 
	 * @param signature
	 *            source signature of the method
	 */
	public FieldImpl(String signature, EnumSet<Modifiers> modifiers) {
		super(modifiers);
		this.signature = signature;
	}

	@Override
	public String getSignature() {
		return signature;
	}

	@Override
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return java class containing the method
	 */
	public ApiClass getApiClass() {
		return apiClass;
	}

	public void setApiClass(ApiClass apiClass) {
		this.apiClass = apiClass;
	}
	
	@Override
	public String getDisplayName() {
		// We want to display it as-is.
		return getSignature();
	}
	
	@Override
	public boolean isPrivate() {
		return modifiers.contains(Modifiers.PRIVATE);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
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
		FieldImpl other = (FieldImpl) obj;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Field [signature=" + signature + "]";
	}
}
