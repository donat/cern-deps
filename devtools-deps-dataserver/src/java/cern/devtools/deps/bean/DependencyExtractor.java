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
package cern.devtools.deps.bean;

import java.util.List;

import cern.devtools.deps.bean.DepBeanException;


/**
 * Interface, which defines the execution of the dependency analysis.<p>
 * 
 * By calling the {@link #executeAnalysis(List)} method, the implementation should discover and store the internal
 * structure of all the provided plugins and then discover the dependencies between the stored items.<p>
 * 
 * @author Donat Csikos
 */
public interface DependencyExtractor {

	/**
	 * Entry point of initiating dependency analysis on the list of artifact in the argument.
	 * 
	 * @param lad The artifacts to analyse.
	 */
	public void executeAnalysis(List<? extends ArtifactDescriptor> lad) throws DepBeanException;
}
