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

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import cern.devtools.depanalysis.domain.ApiClass;
import cern.devtools.depanalysis.domain.Method;
import cern.devtools.depanalysis.domain.Modifiers;

/**
 * Domain object representing a method definition in the class.
 * 
 * @author Donat Csikos
 */
public class MethodImpl extends AbstractCodeElement implements Method {

	private static final long serialVersionUID = 5746166973421876765L;

	/**
	 * source/binary signature.
	 */
	private String signature;

	/**
	 * containter class reference
	 */
	private ApiClass apiClass;

	/**
	 * String representation of the externally referenced fields (retrieved from the constant pool).
	 */
	private List<String> referencedFields;
	/**
	 * String representation of the externally referenced methods (retrieved from the constant pool).
	 */
	private List<String> referencedMethods;

	/**
	 * Constructor
	 * 
	 * @param signature Source signature of the method.
	 * @param isStatic Flags if the method is a static one.
	 */
	public MethodImpl(String signature, EnumSet<Modifiers> modifiers) {
		super(modifiers);
		this.signature = signature;
	}

	/**
	 * @return source signature of the method
	 */
	@Override
	public String getSignature() {
		return signature;
	}

	@Override
	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public boolean isStatic() {
		return modifiers.contains(Modifiers.STATIC);
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
	public String getDisplayName() {
		// get the name without the class declaration
		String[] names = signature.split("\\#");
		String sig2 = names[1];
		
		// Cut the part till the ( and add to the result.
		String[] tmp = sig2.split("\\(");
		String result = tmp[0] + "(";

		// Store the arguments and the return value
		tmp = tmp[1].split("\\)");
		String arguments = tmp[0];
		String returnType = tmp[1];

		// sort the arguments.
		tmp = arguments.split("\\,");
		for (int i = 0; i < tmp.length; ++i) {
			String arg = tmp[i];
			String[] cla = arg.split("\\.");
			result += cla[cla.length - 1];
			if (i + 1 < tmp.length) {
				result += ",";
			}
		}

		// Close bracket.
		result += "):";

		// sort the return type
		returnType = returnType.replace(":", "");
		tmp = returnType.split("\\.");
		result += tmp[tmp.length - 1];

		return result;
	}

	@Override
	public boolean isPrivate() {
		return modifiers.contains(Modifiers.PRIVATE);
	}

	@Override
	public List<String> getReferencedFields() {
		return referencedFields == null ? referencedFields = new LinkedList<String>() : referencedFields;
	}

	@Override
	public List<String> getReferencedMethods() {
		return referencedMethods == null ? referencedMethods = new LinkedList<String>() : referencedMethods;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		MethodImpl other = (MethodImpl) obj;
		if (id != other.id)
			return false;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(MethodImpl) " + signature;
	}
}
