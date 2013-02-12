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
import java.util.LinkedList;
import java.util.List;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.Product;

/**
 * Domain class describing a java product
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public class ProductImpl extends AbstractCodeElement implements Product {

	private static final long serialVersionUID = -154216905654643619L;

	/**
	 * product name
	 */
	private final String name;

	/**
	 * containing folders inside the repo root
	 */
	private final String containingFolders;

	/**
	 * the classes defined in the product
	 */
	private List<ApiClass> apiClasses;

	/**
	 * The location of the jar file
	 */
	private final String storeLocation;

	/**
	 * Constructor
	 * 
	 * @param name name of the product
	 * @param version version number
	 * @param containingFolders source folder hierarchy
	 * @param storeLocation the location of the jar file
	 */
	public ProductImpl(String name, String version, String containingFolders, String storeLocation) {
		super(EnumSet.noneOf(Modifiers.class));
		this.name = name;
		this.storeLocation = storeLocation;
		this.addVersions(version);
		this.containingFolders = containingFolders;
	}
	
	/**
	 * Constructor
	 * 
	 * @param name name of the product
	 * @param version version numbers
	 * @param containingFolders source folder hierarchy
	 * @param storeLocation the location of the jar file
	 */
	public ProductImpl(String name, List<String> versions, String containingFolders, String storeLocation) {
		super(EnumSet.noneOf(Modifiers.class));
		this.name = name;
		this.storeLocation = storeLocation;
		super.addVersions(versions.toArray(new String[0]));
		this.containingFolders = containingFolders;
	}

	/**
	 * Constructor
	 * 
	 * @param name name of the product
	 * @param version version number
	 */
	public ProductImpl(String name, String version) {
		this(name, version, "", "");
	}

	public ProductImpl(String name) {
		this(name, "");
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getContainingFolders() {
		return containingFolders;
	}

	@Override
	public List<ApiClass> getClasses() {
		return apiClasses == null ? apiClasses = new LinkedList<ApiClass>() : apiClasses;
	}

	@Override
	public String getDisplayName() {
		return name;
	}

	@Override
	public String getStoreLocation() {
		return storeLocation;
	}
	
	@Override
	public String toString() {
		return String.format("ProductImpl(name=%s, versions=%s)", name, getVersions().toString());
	}
}
