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
package cern.devtools.deps.domain;

import java.util.Collection;

/**
 * Extended relation, which has a reference for the transitive dependencies.
 * 
 * @author Donat Csikos
 */
public interface TransitiveDependency extends Dependency {

	/**
	 * Returns a set of dependency, which depends on the source. For example if we have four components c1, c2, c3 and
	 * c4 with dependencies: (c1, c3), (c2, c3), (c3, c4), and we ask for the dependencies of c4, we should get the
	 * result as a {@link TransitiveDependency}, which has the properties: to=c4, from=c3, transitiveFrom={c1, c2}.
	 * 
	 * @return
	 */
	Collection<Dependency> getTransitiveFrom();
}
