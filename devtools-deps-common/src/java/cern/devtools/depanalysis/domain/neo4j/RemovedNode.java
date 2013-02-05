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

import java.util.EnumSet;

import cern.devtools.depanalysis.domain.Modifiers;

public class RemovedNode extends AbstractNode {

	private String signature;

	private String productName;

	private String lastVersion;

	public RemovedNode() {
		super(EnumSet.noneOf(Modifiers.class));
	}

	public RemovedNode(String signature, String productName, String lastVersion) {
		super(EnumSet.noneOf(Modifiers.class));
		this.signature = signature;
		this.productName = productName;
		this.lastVersion = lastVersion;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getLastVersion() {
		return lastVersion;
	}

	public void setLastVersion(String lastVersion) {
		this.lastVersion = lastVersion;
	}

	@Override
	public String getDisplayName() {
		return "removed element";
	}
}
