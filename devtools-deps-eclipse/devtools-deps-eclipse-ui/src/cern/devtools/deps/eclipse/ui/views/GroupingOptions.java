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
package cern.devtools.deps.eclipse.ui.views;

/**
 * The available groupings for {@link DependencyView#treeViewer}.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 * 
 */
public enum GroupingOptions {
	/**
	 * Organise the hierarchy structures to the following levels: Product &lt; Version &lt; Class &lt; Method.
	 */
	GROUP_BY_PRODUCT {
		public GroupingOptions next() {
			return GROUP_BY_DEPENDENCY_TYPE;
		}
	},

	/**
	 * Organise the hierarchy structures to the following levels: Type of dependency &lt; {Method, Class}  &lt; Transitive dependency.
	 */
	GROUP_BY_DEPENDENCY_TYPE {
		@Override
		public GroupingOptions next() {
			return GROUP_BY_PRODUCT;
		}
	};

	/**
	 * Returns the order of the organising options. Used for determining a strategy, what happens if a next grouping
	 * option action is executed.
	 * 
	 * @return The next used grouping.
	 */
	public abstract GroupingOptions next();
}
